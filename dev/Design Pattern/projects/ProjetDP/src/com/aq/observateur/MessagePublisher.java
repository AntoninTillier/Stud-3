package com.aq.observateur;

import java.util.ArrayList;
import java.util.List;

public class MessagePublisher implements Sujet {
    private List<Observateur> observateurs = new ArrayList<>();

    @Override
    public void enregistrerObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void notifierObservateur(Message m) {
        for (Observateur o : observateurs) {
            o.actualiser(m);
        }
    }
}