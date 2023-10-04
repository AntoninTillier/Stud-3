package com.pormob.aq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.pormob.aq.api.UserHelper;
import com.pormob.aq.base.BaseActivity;
import com.pormob.aq.fragments.NewFragment;
import com.pormob.aq.fragments.ParamsFragment;
import com.pormob.aq.fragments.ProfileFragment;

import java.util.Arrays;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment fragmentNews;
    private Fragment fragmentProfile;
    private Fragment fragmentParams;
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_PARAMS = 2;
    private static final int RC_SIGN_IN = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Configure all views
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.toolbar.setTitle(R.string.title_activity_main);
        this.navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setTitle(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
        // Show First Fragment
        this.showFirstFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update UI when activity is resuming
        this.navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setTitle(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
    }


    @Override
    public int getFragmentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Show fragment after user clicked on a menu item
        switch (id) {
            case R.id.activity_main_drawer_news:
                this.showFragment(FRAGMENT_NEWS);
                break;
            case R.id.activity_main_drawer_profile:
                if (this.isCurrentUserLogged()) {
                    this.showFragment(FRAGMENT_PROFILE);
                } else {
                    this.startSignInActivity();
                }
                break;
            case R.id.activity_main_drawer_settings:
                this.showFragment(FRAGMENT_PARAMS);
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------
    // Configure Toolbar
    private void configureToolBar() {
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_main);
    }

    // Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // ---------------------
    // FRAGMENTS
    // ---------------------
    // Show first fragment when activity is created
    private void showFirstFragment() {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null) {
            this.toolbar.setTitle(R.string.title_activity_main);
            // Show News Fragment
            this.showFragment(FRAGMENT_NEWS);
            // Mark as selected the menu item corresponding to NewFragment
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    // Show fragment according an Identifier
    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_NEWS:
                this.showNewsFragment();
                break;
            case FRAGMENT_PROFILE:
                this.showProfileFragment();
                break;
            case FRAGMENT_PARAMS:
                this.showParamsFragment();
                break;
            default:
                break;
        }
    }

    // ---
    // Create each fragment page and show it
    private void showNewsFragment() {
        this.toolbar.setTitle(R.string.title_activity_main);
        if (this.fragmentNews == null) this.fragmentNews = NewFragment.newInstance();
        this.startTransactionFragment(this.fragmentNews);
    }

    private void showParamsFragment() {
        this.toolbar.setTitle(R.string.toolbar_title_parametre_activity);
        if (this.fragmentParams == null) this.fragmentParams = ParamsFragment.newInstance();
        this.startTransactionFragment(this.fragmentParams);
    }

    private void showProfileFragment() {
        this.toolbar.setTitle(R.string.toolbar_title_login_activity);
        if (this.fragmentProfile == null) this.fragmentProfile = ProfileFragment.newInstance();
        this.startTransactionFragment(this.fragmentProfile);
    }

    // ---
    // Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

    // Launch Sign-In Activity
    private void startSignInActivity() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.AppTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build() // EMAIL
                                        /*new AuthUI.IdpConfig.GoogleBuilder().build()*/))// GOOGLE remove try on emulateur
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.ic_launcher_foreground)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("MSG", "MainActivity  requestCode = " + requestCode + ", resultCode = " + resultCode);
        if (requestCode == RC_SIGN_IN) {
            this.onNavigationItemSelected(this.navigationView.getMenu().getItem(0));
            this.navigationView.getMenu().getItem(0).setChecked(true);
            // Handle SignIn Activity response on activity result
            this.handleResponseAfterSignIn(requestCode, resultCode, data);
        }

    }

    // Show Snack Bar with a message
    private void showSnackBar(String message) {
        View view = findViewById(R.id.activity_main_drawer_layout);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    // Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                this.createUserInFirestore();
                showSnackBar(getString(R.string.connection_succeed));
            } else { // ERRORS
                if (response == null) {
                    showSnackBar(getString(R.string.error_authentication_canceled));
                } else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackBar(getString(R.string.error_no_internet));
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackBar(getString(R.string.error_unknown_error));
                }
            }
        }
    }

    private void createUserInFirestore() {
        if (this.getCurrentUser() != null) {
            String username = this.getCurrentUser().getDisplayName();
            String uid = this.getCurrentUser().getUid();
            UserHelper.getUser(this.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("MSG", "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d("MSG", "No such document");
                            UserHelper.createUser(uid, username, 1, null);
                        }
                    } else {
                        Log.d("MSG", "get failed with ", task.getException());
                    }
                }
            });
        }
    }
}