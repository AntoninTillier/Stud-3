package com.pormob.aq.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.pormob.aq.ArticleActivity;
import com.pormob.aq.MessageActivity;
import com.pormob.aq.R;
import com.pormob.aq.api.ArticleContainerHelper;
import com.pormob.aq.models.Article;
import com.pormob.aq.models.ListAdapter;

public class NewFragment extends Fragment implements OnSuccessListener {
    private View view;
    private FloatingActionButton floatingActionButton;
    private ListView listView;
    private Article article;

    public static NewFragment newInstance() {
        return (new NewFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_news, container, false);
        ArticleContainerHelper.getArticleContainer().addOnSuccessListener(this);
        return this.view;
    }

    private void updateUIWhenCreating() {
        this.listView = view.findViewById(R.id.fragment_new_list_view);
        String[] maintile = {article.getTitre()};
        String[] auteur = {article.getAuteur()};
        ListAdapter listAdapter = new ListAdapter(getActivity(), maintile, auteur);
        this.listView.setAdapter(listAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent layout1 = new Intent(getActivity(), ArticleActivity.class);
                startActivity(layout1);
            }
        });

        this.floatingActionButton = this.view.findViewById(R.id.activity_new_floating_action_button);
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCurrentUserLogged()) {
                    Intent intent = new Intent(view.getContext(), MessageActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.popup_message_floating_button)
                            .setNeutralButton(R.string.popup_message_choice_ok, null)
                            .show();
                }
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
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) o;
        Article a = documentSnapshot.toObject(Article.class);
        article = a;
        this.updateUIWhenCreating();
    }
}