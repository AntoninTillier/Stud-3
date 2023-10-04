package com.pormob.aq.fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.pormob.aq.R;
import com.pormob.aq.api.UserHelper;
import com.pormob.aq.models.User;

import java.io.ByteArrayOutputStream;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    private ImageView imageViewProfile;
    private EditText textInputEditTextUsername;
    private TextView textViewEmail;
    private ProgressBar progressBar;
    private View view;
    private Button signOut;
    private Button deleteAccount;
    private Button updateUsername;
    private Button copyUID;
    private Uri uriImageSelected;
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    private static final int UPDATE_USERNAME = 30;
    private static final String[] PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CHOOSE_PHOTO = 200;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_PERM = 124;
    private static final int SHARE = 100;

    public static ProfileFragment newInstance() {
        return (new ProfileFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toolbar toolbar = this.getActivity().findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle(R.string.toolbar_title_login_activity);
        this.view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.signOut = this.view.findViewById(R.id.profile_activity_button_sign_out);
        this.signOut.setOnClickListener(view -> onClickSignOutButton());
        this.deleteAccount = this.view.findViewById(R.id.profile_activity_button_delete);
        this.deleteAccount.setOnClickListener(view -> onClickDeleteButton());
        this.copyUID = this.view.findViewById(R.id.profile_activity_button_copy);
        this.copyUID.setOnClickListener(v -> onClickCopyUID());
        this.updateUsername = this.view.findViewById(R.id.profile_activity_button_update);
        this.updateUsername.setOnClickListener(v -> onClickUpdateButton());
        this.imageViewProfile = this.view.findViewById(R.id.profile_activity_imageview_profile);
        this.imageViewProfile.setOnClickListener(v -> onClickImageView());
        this.updateUIWhenCreating();
        return view;
    }

    // Update UI when activity is creating
    private void updateUIWhenCreating() {
        this.textInputEditTextUsername = this.view.findViewById(R.id.profile_activity_edit_text_username);
        this.textViewEmail = this.view.findViewById(R.id.profile_activity_text_view_email);
        this.progressBar = this.view.findViewById(R.id.profile_activity_progress_bar);
        if (this.getCurrentUser() != null) {
            //Get email & username from Firebase
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
            //Update views with data
            this.textViewEmail.setText(email);
            UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    User currentUser = documentSnapshot.toObject(User.class);
                    String username = TextUtils.isEmpty(currentUser.getUsername()) ? getString(R.string.info_no_username_found) : currentUser.getUsername();
                    textInputEditTextUsername.setText(username);
                    if (currentUser.getBytes() != null) {
                        String tempBytes = currentUser.getBytes();
                        byte[] bytes1 = Base64.decode(tempBytes, 1);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
                        Glide.with(getActivity())
                                .load(bitmap)
                                .apply(RequestOptions.circleCropTransform())
                                .into(imageViewProfile);
                    }
                }
            });
        }
    }

    // Ask permission when accessing to this listener
    @AfterPermissionGranted(RC_IMAGE_PERMS)
    private void onClickImageView() {
        if (!EasyPermissions.hasPermissions(this.getActivity(), PERMS)) {
            EasyPermissions.requestPermissions(this.getActivity(), getString(R.string.popup_title_permission_files_access), RC_IMAGE_PERMS, PERMS);
            return;
        }
        // Launch an "Selection Image" Activity
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RC_CHOOSE_PHOTO);
    }

    // Handle activity response (after user has chosen or not a picture)
    private void handleResponse(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) { //SUCCESS
                this.uriImageSelected = data.getData();
                String filePath = null;
                if (this.uriImageSelected != null && "content".equals(this.uriImageSelected.getScheme())) {
                    Cursor cursor = this.getActivity().getContentResolver().query(this.uriImageSelected, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
                    cursor.moveToFirst();
                    filePath = cursor.getString(0);
                    cursor.close();
                } else {
                    filePath = this.uriImageSelected.getPath();
                }
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos);
                byte[] bytes = baos.toByteArray();
                String tempBytes = Base64.encodeToString(bytes, 1);
                Log.v("MSG", String.valueOf(tempBytes.length()));
                Glide.with(this)
                        .load(bitmap)
                        .apply(RequestOptions.circleCropTransform())
                        .into(this.imageViewProfile);
                if (this.getCurrentUser() != null) {
                    UserHelper.updateBytes(tempBytes, this.getCurrentUser().getUid());
                }
            } else {
                Toast.makeText(this.getActivity(), getString(R.string.toast_title_no_image_chosen), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("MSG", "ProfilFramgent  requestCode = " + Integer.toString(requestCode) + ", resultCode = " + Integer.toString(resultCode));
        if (requestCode == RC_CHOOSE_PHOTO) {
            this.handleResponse(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void onClickSignOutButton() {
        this.signOutUserFromFirebase();
    }

    public void onClickDeleteButton() {
        new AlertDialog.Builder(this.getActivity())
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUserFromFirebase();
                    }
                })
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show();
    }

    public void onClickUpdateButton() {
        this.updateUsernameInFirebase();
    }

    public void onClickCopyUID() {
        String uid = getCurrentUser().getUid();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, uid);
        sendIntent.setType("text/html");
        Intent shareIntent = Intent.createChooser(sendIntent, "Share");
        startActivityForResult(shareIntent, SHARE);
    }

    // Create http requests
    private void signOutUserFromFirebase() {
        AuthUI.getInstance()
                .signOut(this.getActivity())
                .addOnSuccessListener(this.getActivity(), this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    private void deleteUserFromFirebase() {
        if (this.getCurrentUser() != null) {
            UserHelper.deleteUser(this.getCurrentUser().getUid());
            AuthUI.getInstance()
                    .delete(this.getActivity())
                    .addOnSuccessListener(this.getActivity(), this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    private void updateUsernameInFirebase() {
        this.progressBar.setVisibility(View.VISIBLE);
        String username = this.textInputEditTextUsername.getText().toString();
        if (this.getCurrentUser() != null) {
            if (!username.isEmpty() && !username.equals(getString(R.string.info_no_username_found))) {
                UserHelper.updateUsername(username, this.getCurrentUser().getUid()).addOnSuccessListener(this.getActivity(), this.updateUIAfterRESTRequestsCompleted(UPDATE_USERNAME));
            }
        }
    }


    // Create OnCompleteListener called after tasks ended
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin) {
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                switch (origin) {
                    case UPDATE_USERNAME:
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
                    case SIGN_OUT_TASK:
                        getBack(SIGN_OUT_TASK);
                        break;
                    case DELETE_USER_TASK:
                        getBack(DELETE_USER_TASK);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void getBack(int out) {
        if (out == SIGN_OUT_TASK) {
            Snackbar.make(this.view, R.string.deconnexion, Snackbar.LENGTH_LONG).show();
            this.getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, NewFragment.newInstance()).commit();
            Toolbar toolbar = this.getActivity().findViewById(R.id.activity_main_toolbar);
            toolbar.setTitle(R.string.title_activity_main);
            NavigationView navigationView = this.getActivity().findViewById(R.id.activity_main_nav_view);
            navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setTitle(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        if (out == DELETE_USER_TASK) {
            Snackbar.make(this.view, R.string.supression, Snackbar.LENGTH_LONG).show();
            this.getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, NewFragment.newInstance()).commit();
            Toolbar toolbar = this.getActivity().findViewById(R.id.activity_main_toolbar);
            toolbar.setTitle(R.string.title_activity_main);
            NavigationView navigationView = this.getActivity().findViewById(R.id.activity_main_nav_view);
            navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setTitle(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    protected FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    protected Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() != null);
    }
}