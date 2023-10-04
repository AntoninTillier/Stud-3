package com.pormob.aq.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.RequestManager;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.pormob.aq.R;
import com.pormob.aq.models.Commentaire;

public class CommentaireAdapter extends FirestoreRecyclerAdapter<Commentaire, CommentaireViewHolder> {

    public interface Listener {
        void onDataChanged();
    }

    private final RequestManager glide;
    private Listener callback;

    public CommentaireAdapter(@NonNull FirestoreRecyclerOptions<Commentaire> options, RequestManager glide, Listener callback) {
        super(options);
        this.glide = glide;
        this.callback = callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull CommentaireViewHolder holder, int position, @NonNull Commentaire model) {
        holder.updateWithCommentaire(model, this.glide);
    }

    @NonNull
    @Override
    public CommentaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentaireViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_commentaire_item, parent, false));
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        this.callback.onDataChanged();
    }
}