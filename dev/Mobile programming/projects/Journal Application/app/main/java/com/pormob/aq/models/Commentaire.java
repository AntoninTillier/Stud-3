package com.pormob.aq.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Commentaire {
    private String commentaire;
    private Date dateCreated;
    private User userSender;
    private String urlImage;

    public Commentaire() { }

    public Commentaire(String commentaire, User userSender) {
        this.commentaire = commentaire;
        this.userSender = userSender;
    }

    public Commentaire(String commentaire, String urlImage, User userSender) {
        this.commentaire = commentaire;
        this.urlImage = urlImage;
        this.userSender = userSender;
    }

    public String getCommentaire() { return commentaire; }

    @ServerTimestamp public Date getDateCreated() { return dateCreated; }

    public User getUserSender() { return userSender; }

    public String getUrlImage() { return urlImage; }

    public void setMessage(String commentaire) { this.commentaire = commentaire; }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public void setUserSender(User userSender) { this.userSender = userSender; }

    public void setUrlImage(String urlImage) { this.urlImage = urlImage; }
}