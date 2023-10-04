package com.pormob.aq.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.pormob.aq.models.Commentaire;
import com.pormob.aq.models.User;

public class ArticleCommentsHelper {
    private static final String COLLECTION_NAME = "public";
    private static final String COLLECTION_COMMENTS = "comments";

    public static Query getAllCommentsForChat() {
        return ArticleHelper.getArticleCollection()
                .document("Article1")
                .collection(COLLECTION_NAME).document("container").collection(COLLECTION_COMMENTS)
                .orderBy("dateCreated");
    }

    public static Task<DocumentReference> createComments(String textMessage, User userSender) {
        // 1 - Create the Message object
        Commentaire commentaire = new Commentaire(textMessage, userSender);
        // 2 - Store Message to Firestore
        return ArticleHelper.getArticleCollection()
                .document("Article1")
                .collection(COLLECTION_NAME).document("container").collection(COLLECTION_COMMENTS)
                .add(commentaire);
    }
}