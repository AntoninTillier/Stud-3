package com.aq.observateur;

public interface Sujet {

    public void enregistrerObservateur(Observateur o);

    public void supprimerObservateur(Observateur o);

    public void notifierObservateur(Message m);
}