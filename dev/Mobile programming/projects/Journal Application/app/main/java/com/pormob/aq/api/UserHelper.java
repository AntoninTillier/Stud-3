package com.pormob.aq.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pormob.aq.models.User;

public class UserHelper {
    private static final String COLLECTION_NAME = "users";

    // --- COLLECTION REFERENCE ---
    public static CollectionReference getUsersCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    // --- CREATE ---
    public static Task<Void> createUser(String uid, String username, int flags, String bytes) {
        User userToCreate = new User(uid, username, flags, bytes);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }
    // --- GET ---
    public static Task<DocumentSnapshot> getUser(String uid) {
        return UserHelper.getUsersCollection().document(uid).get();
    }
    // --- UPDATE ---
    public static Task<Void> updateUsername(String username, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateBytes(String bytes, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("bytes", bytes);
    }

    // --- DELETE ---
    public static Task<Void> deleteUser(String uid) {
        return UserHelper.getUsersCollection().document(uid).delete();
    }
}