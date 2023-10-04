package com.pormob.aq.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pormob.aq.models.Contact;
import com.pormob.aq.models.Message;
import com.pormob.aq.models.User;

import java.util.ArrayList;

public class MessageHelper {
    private static final String COLLECTION_NAME = "messages";

    public static CollectionReference getAllMessages() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public static Task<Void> createContact(String uid, ArrayList<User> users) {
        Contact contact = new Contact(users);
        return MessageHelper.getAllMessages().document(uid).set(contact);
    }

    public static Task<Void> updateContact(String uid, ArrayList<User> users) {
        Contact contact = new Contact(users);
        return MessageHelper.getAllMessages().document(uid).update("userArrayList", contact);
    }

    public static Task<DocumentSnapshot> getArrayContact(String uid) {
        return MessageHelper.getAllMessages().document(uid).get();
    }

    public static Query getAllMessageForUserToOtherUser(String currentUID, String otherUID) {
        return MessageHelper.getAllMessages()
                .document(currentUID)
                .collection(otherUID)
                .orderBy("dateCreated");
    }

    public static Task<DocumentReference> createChatUserCurrent(User sendUser, String otherUid, String messsage) {
        Message message = new Message(messsage, sendUser);
        return MessageHelper.getAllMessages().document(sendUser.getUid()).collection(otherUid).add(message);
    }

    public static Task<DocumentReference> createChatUserOther(User sendUser, String otherUid, String messsage) {
        Message message = new Message(messsage, sendUser);
        return MessageHelper.getAllMessages().document(otherUid).collection(sendUser.getUid()).add(message);
    }
}