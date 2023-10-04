package com.pormob.aq;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.pormob.aq.api.MessageHelper;
import com.pormob.aq.api.UserHelper;
import com.pormob.aq.base.BaseActivity;
import com.pormob.aq.models.Contact;
import com.pormob.aq.models.ListContactAdpater;
import com.pormob.aq.models.User;

import java.util.ArrayList;

public class MessageActivity extends BaseActivity implements OnSuccessListener {
    private Toolbar toolbar;
    private ActionBar actionBar;
    private FloatingActionButton floatingActionButtonEdit;
    private ListView listView;
    private Activity activity;
    private User currentUser;
    private User otherUser;
    private ArrayList<User> userOtherArrayList = new ArrayList<>();
    private ArrayList<User> userArrayList = new ArrayList<>();
    private boolean search = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        this.activity = this;
        this.configureToolbar();
        UserHelper.getUser(getCurrentUser().getUid()).addOnSuccessListener((OnSuccessListener<? super DocumentSnapshot>) activity);
        this.configure();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_message;
    }

    protected void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_message_toolbar);
        setSupportActionBar(toolbar);
        this.actionBar = getSupportActionBar();
        this.actionBar.setDisplayHomeAsUpEnabled(true);
        this.actionBar.setTitle("Message");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureListView() {
        this.listView = findViewById(R.id.list_view);
        if (this.search)
            MessageHelper.getArrayContact(getCurrentUser().getUid()).addOnSuccessListener(this);
        else {
            ListContactAdpater listContactAdpater = new ListContactAdpater(this, userArrayList);
            this.listView.setAdapter(listContactAdpater);
            this.search = true;
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.v("MSG", "click " + i);
                    Intent layout = new Intent(activity, Sendactivity.class);
                    layout.putExtra("otherUid", userArrayList.get(i).getUid());
                    startActivity(layout);
                }
            });
        }
    }

    private void configure() {
        this.floatingActionButtonEdit = findViewById(R.id.activity_new_floating_action_button);
        this.floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("New contact");
                final EditText input = new EditText(activity);
                input.setHint("Put the uid");
                input.setHintTextColor(R.color.textColor);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String otherUid = input.getText().toString();
                        UserHelper.getUser(otherUid).addOnSuccessListener((OnSuccessListener<? super DocumentSnapshot>) activity);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @Nullable
    protected FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    protected Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() != null);
    }

    @Override
    public void onSuccess(Object o) {
        Log.v("MSG", "----------------------------");
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) o;
        User user = documentSnapshot.toObject(User.class);
        Contact contact = documentSnapshot.toObject(Contact.class);
        if (user != null) {
            Log.v("MSG", "user != null");
            Log.v("MSG", user.getUid() + " " + user.getUsername());
            if (user.getUid() != null && !user.getUid().equals(getCurrentUser().getUid())) {
                Log.v("MSG", "user.getUid() != null && user.getUid() != getCurrentUser().getUid()");
                otherUser = user;
                if (userArrayList.isEmpty()) {
                    this.userArrayList.add(user);
                    Log.v("MSG", "createContact pour currentUser");
                    MessageHelper.createContact(getCurrentUser().getUid(), userArrayList);
                    if (userOtherArrayList.isEmpty()) {
                        this.userOtherArrayList.add(currentUser);
                        MessageHelper.createContact(user.getUid(), userOtherArrayList);
                    } else {
                        this.userOtherArrayList.add(currentUser);
                        MessageHelper.updateContact(user.getUid(), userOtherArrayList);
                    }
                } else {
                    this.userArrayList.add(user);
                    Log.v("MSG", "updateContact pour currentUser");
                    MessageHelper.updateContact(getCurrentUser().getUid(), userArrayList);
                    if (userOtherArrayList.isEmpty()) {
                        this.userOtherArrayList.add(currentUser);
                        MessageHelper.createContact(user.getUid(), userOtherArrayList);
                    } else {
                        this.userOtherArrayList.add(currentUser);
                        MessageHelper.updateContact(user.getUid(), userOtherArrayList);
                    }
                }
            } else if (user.getUid() != null) {
                currentUser = user;
            }
        }
        if (otherUser != null)
            Log.v("MSG", otherUser.getUsername());
        if (contact != null) {
            Log.v("MSG", "contact != null");
            if (contact.getUserArrayList() != null) {
                Log.v("MSG", "contact.getUserArrayList() != null");
                if (contact.getUserArrayList().contains(otherUser)) {
                    Log.v("MSG", "contact.getUserArrayList().contains(otherUser)");
                }
                this.search = false;
                this.userArrayList = contact.getUserArrayList();
            }
        }
        this.configureListView();
    }
}