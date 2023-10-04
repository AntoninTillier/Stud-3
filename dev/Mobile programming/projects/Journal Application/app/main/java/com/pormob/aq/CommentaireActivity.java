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
import com.pormob.aq.api.ArticleCommentsHelper;
import com.pormob.aq.api.ArticleContainerHelper;
import com.pormob.aq.api.UserHelper;
import com.pormob.aq.base.BaseActivity;
import com.pormob.aq.holder.CommentaireAdapter;
import com.pormob.aq.models.Commentaire;
import com.pormob.aq.models.User;

public class CommentaireActivity extends BaseActivity implements CommentaireAdapter.Listener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private TextInputEditText textInputEditText;
    private Button button;
    private CommentaireAdapter commentaireAdapter;
    private User user;
    private int nbCommentaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaire);
        Intent i = this.getIntent();
        if (i.hasExtra("nbCommentaire")) {
            this.nbCommentaire = i.getIntExtra("nbCommentaire", 0);
        }
        this.recyclerView = findViewById(R.id.activity_mentor_chat_recycler_view);
        this.textInputEditText = findViewById(R.id.activity_commentaire_edit_text);
        this.button = findViewById(R.id.activity_commentaire_send_button);
        this.configureRecyclerView();
        this.configureToolbar();
        this.getCurrentUserFromFirestore();
        this.send();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_commentaire;
    }

    protected void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_commentaire_toolbar);
        setSupportActionBar(toolbar);
        this.actionBar = getSupportActionBar();
        this.actionBar.setDisplayHomeAsUpEnabled(true);
        this.actionBar.setTitle("Comments");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
                    ArticleCommentsHelper.createComments(textInputEditText.getText().toString(), user);
                    textInputEditText.setText("");
                    ArticleContainerHelper.updateCommentaire(nbCommentaire + 1);
                }
            }
        });
    }

    private void configureRecyclerView() {
        this.commentaireAdapter = new CommentaireAdapter(generateOptionsForAdapter(ArticleCommentsHelper.getAllCommentsForChat()), Glide.with(this), this);
        this.commentaireAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                recyclerView.smoothScrollToPosition(commentaireAdapter.getItemCount()); // Scroll to bottom on new messages
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.commentaireAdapter);
    }

    private FirestoreRecyclerOptions<Commentaire> generateOptionsForAdapter(Query query) {
        return new FirestoreRecyclerOptions.Builder<Commentaire>()
                .setQuery(query, Commentaire.class)
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
    public void onDataChanged() {

    }
}
