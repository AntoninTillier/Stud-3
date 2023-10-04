package com.pormob.aq.models;

import java.util.ArrayList;

public class Contact {

    private ArrayList<User> userArrayList;

    public Contact() { }

    public Contact(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}