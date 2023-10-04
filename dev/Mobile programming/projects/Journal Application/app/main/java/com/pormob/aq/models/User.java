package com.pormob.aq.models;

import androidx.annotation.Nullable;

public class User {
    private String uid;
    private String username;
    private int flags;
    private String bytes;

    public User() { }

    public User(String uid, String username, int flags, String bytes) {
        this.uid = uid;
        this.username = username;
        this.flags = flags;
        this.bytes = bytes;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    @Nullable
    public String getBytes() {
        return bytes;
    }

    public int getFlags() {
        return flags;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }
}