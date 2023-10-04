package com.pormob.aq.holder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.pormob.aq.R;
import com.pormob.aq.models.Commentaire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentaireViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.root_view)
    RelativeLayout rootView;
    @BindView(R.id.item_profile_container)
    LinearLayout profileContainer;
    @BindView(R.id.item_profile_container_profile_image)
    ImageView imageViewProfile;
    @BindView(R.id.item_commentaire_container)
    RelativeLayout commentaireContainer;
    @BindView(R.id.container_image_sent_cardview)
    CardView cardViewImageSent;
    @BindView(R.id.item_commentaire_container_image_sent_cardview_image)
    ImageView imageViewSent;
    @BindView(R.id.item_commentaire_container_text_commentaire_container)
    LinearLayout textCommentaireContainer;
    @BindView(R.id.activity_mentor_chat_item_message_container_text_message_container_text_view)
    TextView textViewCommentaire;
    @BindView(R.id.activity_mentor_chat_item_message_container_text_view_date)
    TextView textViewDate;

    public CommentaireViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithCommentaire(Commentaire commentaire, RequestManager glide) {
        this.textViewCommentaire.setText(commentaire.getCommentaire());
        this.textViewCommentaire.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        if (commentaire.getDateCreated() != null) {
            this.textViewDate.setText(this.convertDateToHour(commentaire.getDateCreated()));
        }
        if (commentaire.getUserSender().getBytes() != null) {
            String tempBytes = commentaire.getUserSender().getBytes();
            byte[] bytes1 = Base64.decode(tempBytes, 1);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
            glide.load(bitmap).apply(RequestOptions.circleCropTransform()).into(this.imageViewProfile);
        }
    }

    private String convertDateToHour(Date date) {
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        return dfTime.format(date);
    }
}