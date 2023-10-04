package com.pormob.aq.api;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ArticleHelper {
    private static final String COLLECTION_NAME = "articles";

    // --- COLLECTION REFERENCE ---
    public static CollectionReference getArticleCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
}