package com.pormob.aq.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.RequestManager;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.pormob.aq.R;
import com.pormob.aq.models.Message;

public class SendAdapter extends FirestoreRecyclerAdapter<Message, SendViewHolder> {

    public interface Listener {
        void onDataChanged();
    }

    private final RequestManager glide;
    private SendAdapter.Listener callback;
    private String idCurrentUser;

    public SendAdapter(@NonNull FirestoreRecyclerOptions<Message> options, RequestManager glide, SendAdapter.Listener callback, String idCurrentUser) {
        super(options);
        this.glide = glide;
        this.callback = callback;
        this.idCurrentUser = idCurrentUser;
    }


    @NonNull
    @Override
    public SendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_send_item, parent, false));
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        this.callback.onDataChanged();
    }

    @Override
    protected void onBindViewHolder(@NonNull SendViewHolder holder, int position, @NonNull Message model) {
        holder.updateWithMessage(model, this.idCurrentUser, this.glide);
    }
}