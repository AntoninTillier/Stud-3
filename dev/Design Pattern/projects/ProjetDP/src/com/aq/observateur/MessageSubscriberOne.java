package com.aq.observateur;

public class MessageSubscriberOne implements Observateur {

    @Override
    public void actualiser(Message m) {
        System.out.println(m.getMessageContent());
    }
}