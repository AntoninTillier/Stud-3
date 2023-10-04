package com.pormob.aq.models;

public class Article {
    private String titre;
    private String auteur;
    private String valeurArticle;
    private int nbCommentaire;
    private int nbPartage;

    public Article() { }

    public Article(String titre, String auteur, String valeurArticle, int nbCommentaire, int nbPartage) {
        this.titre = titre;
        this.auteur = auteur;
        this.valeurArticle = valeurArticle;
        this.nbCommentaire = nbCommentaire;
        this.nbPartage = nbPartage;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getValeurArticle() {
        return valeurArticle;
    }

    public int getNbCommentaire() {
        return nbCommentaire;
    }

    public int getNbPartage() {
        return nbPartage;
    }
}