package com.pormob.aq.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.pormob.aq.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class ParamsFragment extends Fragment implements EasyPermissions.PermissionCallbacks {
    private View view;
    private Switch switchGalleries;
    private Switch switchCamera;
    private Switch switchLocation;
    private TextView textView;
    private Button other;
    private List<Address> addresses = null;
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_PERM = 124;

    public static ParamsFragment newInstance() {
        return (new ParamsFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_params, container, false);
        this.onCreateRelationSwitch();
        this.onCreateLocation();
        return this.view;
    }

    private void onCreateRelationSwitch() {
        this.switchGalleries = this.view.findViewById(R.id.switchGalleries);
        this.switchCamera = this.view.findViewById(R.id.switchCamera);
        this.switchLocation = this.view.findViewById(R.id.switchLocation);
        this.textView = this.view.findViewById(R.id.locationName);
        this.other = this.view.findViewById(R.id.other_button);
        this.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        this.checkPermission();
        this.switchGalleries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchGalleries.isChecked()) {
                    permissionGalleries();
                } else {
                    dismissPermissionGalleries();
                }
            }
        });
        this.switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCamera.isChecked()) {
                    permissionCamera();
                } else {
                    dismissPermissionCamera();
                }
            }
        });
        this.switchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchLocation.isChecked()) {
                    permissionLocation();
                } else {
                    dismissPermissionLocation();
                }
            }
        });
    }

    private void onCreateLocation() {
        if (EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.v("MSG", "ACCESS_FINE_LOCATION true");
            LocationManager locationManager = (LocationManager) this.view.getContext().getSystemService(this.view.getContext().LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this.view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, new LocationListener() {
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
                @Override
                public void onProviderEnabled(String provider) {
                }
                @Override
                public void onProviderDisabled(String provider) {
                }
                @Override
                public void onLocationChanged(Location location) {
                    Geocoder geocoder = new Geocoder(view.getContext(), Locale.getDefault());
                    Log.v("MSG", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addresses == null || addresses.size() == 0) {
                        Log.v("MSG", "erreur aucune adresse !");
                    } else {
                        Address adresse = addresses.get(0);
                        ArrayList<String> addressFragments = new ArrayList<String>();
                        for (int i = 0; i <= adresse.getMaxAddressLineIndex(); i++) {
                            addressFragments.add(adresse.getAddressLine(i));
                        }
                        textView.setText(TextUtils.join(System.getProperty("line.separator"), addressFragments));
                        Log.v("MSG", TextUtils.join(System.getProperty("line.separator"), addressFragments));
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_IMAGE_PERMS)
    private void permissionGalleries() {
        if (!EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this, getString(R.string.popup_title_permission_files_access), RC_IMAGE_PERMS, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void dismissPermissionGalleries() {
        this.onRequestPermissionsResult(RC_IMAGE_PERMS, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new int[]{-1});
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    private void permissionCamera() {
        if (!EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.CAMERA)) {
            EasyPermissions.requestPermissions(this, getString(R.string.popup_title_permission_files_access), RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    private void dismissPermissionCamera() {
        this.onRequestPermissionsResult(RC_CAMERA_PERM, new String[]{Manifest.permission.CAMERA}, new int[]{-1});
    }

    @AfterPermissionGranted(RC_LOCATION_PERM)
    private void permissionLocation() {
        if (!EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            EasyPermissions.requestPermissions(this, getString(R.string.popup_title_permission_files_access), RC_LOCATION_PERM, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void dismissPermissionLocation() {
        this.onRequestPermissionsResult(RC_LOCATION_PERM, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, new int[]{-1});
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        this.checkPermission();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        this.checkPermission();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            showAppSettingDialog();
        }
    }

    private void showAppSettingDialog() {
        new AppSettingsDialog.Builder(this).build().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("MSG", "ParamsFragment  requestCode = " + Integer.toString(requestCode) + ", resultCode = " + Integer.toString(resultCode));
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Log.v("MSG", "AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE");
            this.checkPermission();
        }
    }

    private void checkPermission() {
        if (EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            this.switchGalleries.setChecked(true);
            this.switchGalleries.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
            this.switchGalleries.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
        } else {
            this.switchGalleries.setChecked(false);
            this.switchGalleries.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorError)));
            this.switchGalleries.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorError)));
        }
        if (EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.CAMERA)) {
            this.switchCamera.setChecked(true);
            this.switchCamera.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
            this.switchCamera.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
        } else {
            this.switchCamera.setChecked(false);
            this.switchCamera.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorError)));
            this.switchCamera.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorError)));
        }
        if (EasyPermissions.hasPermissions(this.view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            this.switchLocation.setChecked(true);
            this.switchLocation.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
            this.switchLocation.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
            this.onCreateLocation();
        } else {
            this.switchLocation.setChecked(false);
            this.switchLocation.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorError)));
            this.switchLocation.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorError)));
        }
    }
}