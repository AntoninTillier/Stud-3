package com.aq;

public class User {
    private int id;
    private String name;
    private int niveaux;

    public User() { }

    public User(int id, String name, int niveau) {
        this.setId(id);
        this.setName(name);
        this.setNiveaux(niveau);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(int niveaux) {
        this.niveaux = niveaux;
    }
}