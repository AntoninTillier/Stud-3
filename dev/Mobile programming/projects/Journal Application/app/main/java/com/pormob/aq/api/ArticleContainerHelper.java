package com.pormob.aq.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class ArticleContainerHelper {
    private static final String COLLECTION_NAME = "public";

    public static Task<DocumentSnapshot> getArticleContainer() {
        return ArticleHelper.getArticleCollection().document("Article1").collection(COLLECTION_NAME).document("container").get();
    }

    public static Task<Void> updatePartage(int nbPartage) {
        return ArticleHelper.getArticleCollection().document("Article1")
                .collection(COLLECTION_NAME).document("container").update("nbPartage", nbPartage);
    }

    public static Task<Void> updateCommentaire(int nbCommentaire) {
        return ArticleHelper.getArticleCollection().document("Article1")
                .collection(COLLECTION_NAME).document("container").update("nbCommentaire", nbCommentaire);
    }
}