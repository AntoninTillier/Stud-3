package com.pormob.aq;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.pormob.aq.api.MessageHelper;
import com.pormob.aq.api.UserHelper;
import com.pormob.aq.base.BaseActivity;
import com.pormob.aq.holder.SendAdapter;
import com.pormob.aq.models.Message;
import com.pormob.aq.models.User;

public class Sendactivity extends BaseActivity implements SendAdapter.Listener {
    private Toolbar toolbar;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private TextInputEditText textInputEditText;
    private Button button;
    private SendAdapter messageAdapter;
    private User user;
    private String otherUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendactivity);
        this.getCurrentUserFromFirestore();
        Intent i = this.getIntent();
        if (i.hasExtra("otherUid")) {
            this.otherUid = i.getStringExtra("otherUid");
        }
        this.recyclerView = findViewById(R.id.activity_mentor_chat_recycler_view);
        this.textInputEditText = findViewById(R.id.activity_commentaire_edit_text);
        this.button = findViewById(R.id.activity_commentaire_send_button);
        this.configureRecyclerView();
        this.configureToolbar();
        this.send();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_sendactivity;
    }


    protected void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_commentaire_toolbar);
        setSupportActionBar(toolbar);
        this.actionBar = getSupportActionBar();
        this.actionBar.setDisplayHomeAsUpEnabled(true);
        this.actionBar.setTitle("TEST");
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

    private void send() {
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(textInputEditText.getText()) && user != null) {
                    MessageHelper.createChatUserCurrent(user, otherUid, textInputEditText.getText().toString());
                    MessageHelper.createChatUserOther(user, otherUid, textInputEditText.getText().toString());
                    textInputEditText.setText("");
                }
            }
        });
    }

    private void configureRecyclerView() {
        this.messageAdapter = new SendAdapter(generateOptionsForAdapter(MessageHelper.getAllMessageForUserToOtherUser(this.getCurrentUser().getUid(), this.otherUid)), Glide.with(this), this, this.getCurrentUser().getUid());
        this.messageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()); // Scroll to bottom on new messages
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.messageAdapter);
    }

    private FirestoreRecyclerOptions<Message> generateOptionsForAdapter(Query query) {
        return new FirestoreRecyclerOptions.Builder<Message>()
                .setQuery(query, Message.class)
                .setLifecycleOwner(this)
                .build();
    }

    private void getCurrentUserFromFirestore() {
        UserHelper.getUser(getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
            }
        });
    }

    @Override
    public void onDataChanged() { }
}