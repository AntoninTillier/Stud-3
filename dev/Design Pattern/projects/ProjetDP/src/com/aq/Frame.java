package com.aq;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aq.dao.AbstractDAOFactory;
import com.aq.dao.DAO;
import com.aq.dao.DAOFactory;
import com.aq.listener.*;
import com.aq.menu.PanelMenu;
import com.aq.observateur.Message;
import com.aq.observateur.MessagePublisher;
import com.aq.observateur.MessageSubscriberOne;
import com.aq.observateur.MessageSubscriberTwo;

import doryan.windowsnotificationapi.fr.Notification;

import com.aq.game.PanelGame;
import com.aq.game.individu.Joueur;
import com.aq.game.individu.TypeIndividu;
import com.aq.game.individu.UsineHumain;
import com.aq.game.labyrinthe.Labyrinthe;

public class Frame extends JFrame {
    protected static PanelMenu panelMenu = new PanelMenu();
    public static PanelGame panelGame = new PanelGame();
    public static keyListener kl = new keyListener();
    public static mouseListener ml = new mouseListener();
    protected static UsineHumain usineHumain = new UsineHumain();
    public static Joueur joueur = (Joueur) usineHumain.creerIndividu(TypeIndividu.JOUEUR);
    public static PiouPiou pioupiou = new PiouPiou();
    public static Balle balle = new Balle();
    public static Labyrinthe labyrinthe = new Labyrinthe();
    public static boolean propos = false;
    public static boolean classement = false;
    public static String[] nameClassement = new String[10];
    public static String[] niveauxClassement = new String[10];
    public static int i = 0;
    public User user;

    public Frame() {
        this.getContentPane().add(panelMenu);
        this.getContentPane().validate();
        int h = 500;
        int l = 750;
        this.setUndecorated(true);
        this.setSize(l, h);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);
        this.addKeyListener(kl);
        this.setVisible(true);
        menu();
    }

    public void menu() {
        this.load();
        String name = "";
        boolean f = false;
        while (!f) {
            this.getContentPane().repaint();
            long start = System.currentTimeMillis();
            if (ml.x > 300 && ml.x < 450 && ml.y > 285 && ml.y < 325 && ml.clic && !classement && !propos) {
                ml.clic = false;
                name = JOptionPane.showInputDialog(null, "Veuillez entrer votre pseudo", "Pseudo", JOptionPane.QUESTION_MESSAGE);
                if (name != null && name.length() > 0) {
                    if (this.logOrSign(name)) {
                        f = true;
                    } else {
                        if (this.createUser(name)) {
                            this.logOrSign(name);
                            f = true;
                        }
                    }
                }
            }
            if (ml.x > 0 && ml.x < 150 && ml.y > 420 && ml.y < 480 && ml.clic && !propos) {
                ml.clic = false;
                propos = true;
            }
            if (ml.x > 0 && ml.x < 150 && ml.y > 420 && ml.y < 480 && ml.clic && propos) {
                ml.clic = false;
                propos = false;
            }
            if (ml.x > 550 && ml.x < 740 && ml.y > 20 && ml.y < 80 && ml.clic && !classement) {
                ml.clic = false;
                classement = true;
            }
            if (ml.x > 550 && ml.x < 740 && ml.y > 20 && ml.y < 80 && ml.clic && classement) {
                ml.clic = false;
                classement = false;
            }
            long fin = System.currentTimeMillis() + 15;
            try {
                Thread.sleep(fin - start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.getContentPane().remove(panelMenu);
        this.getContentPane().add(panelGame);
        this.getContentPane().validate();
        int h = 700;
        int l = 1270;
        this.setSize(l, h);
        this.setLocationRelativeTo(null);
        this.game();
    }

    public void game() {
        joueur.setNiveaux(user.getNiveaux());
        pioupiou.setDmg(4 * Frame.joueur.getNiveaux() + 4);
        boolean end = true;
        while (end) {
            this.getContentPane().repaint();
            long start = System.currentTimeMillis();
            labyrinthe.getLab().get(i).ouverture();
            if (labyrinthe.getLab().get(12).getM().isEmpty()) {
                end = false;
            }
            if (joueur.getPv() <= 20) {
                end = false;
            }
            joueur.seDeplacer();
            joueur.interagirIndividu();
            joueur.prendreObjet();
            joueur.ouvrirPorteF();
            joueur.choiObjet();
            joueur.enleverObjet();
            long fin = System.currentTimeMillis() + 15;
            try {
                Thread.sleep(fin - start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (joueur.getPv() > 20) {
            MessageSubscriberOne s1 = new MessageSubscriberOne();
            MessagePublisher p = new MessagePublisher();
            p.enregistrerObservateur(s1);
            Message message = new Message("Vous venez de passez un niveaux");
            p.notifierObservateur(message);
            try {
                Notification.sendNotification("Labyrinthe", message.getMessageContent(), MessageType.NONE);
            } catch (MalformedURLException | AWTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            user.setNiveaux(user.getNiveaux() + 1);
            this.updateUser(user);
            i = 0;
            labyrinthe = new Labyrinthe();
            game();
        } else {
            MessageSubscriberTwo s2 = new MessageSubscriberTwo();
            MessagePublisher p = new MessagePublisher();
            p.enregistrerObservateur(s2);
            Message message2 = new Message("Vous etes mort");
            p.notifierObservateur(message2);
            try {
                Notification.sendNotification("Labyrinthe", message2.getMessageContent(), MessageType.NONE);
            } catch (MalformedURLException | AWTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i = 0;
            joueur.setPv(50);
            labyrinthe = new Labyrinthe();
            this.getContentPane().remove(panelGame);
            this.getContentPane().add(panelMenu);
            this.getContentPane().validate();
            int h = 500;
            int l = 750;
            this.setSize(l, h);
            this.setLocationRelativeTo(null);
            this.getContentPane().repaint();
            menu();
        }
    }

    @SuppressWarnings("unchecked")
    public boolean logOrSign(String name) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        user = userDAO.find(name);
        return user.getName() != null ? true : false;
    }

    @SuppressWarnings("unchecked")
    public boolean createUser(String name) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        User tempUser = new User(0, name, 0);
        return userDAO.create(tempUser);
    }

    @SuppressWarnings("unchecked")
    public boolean updateUser(User user) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        return userDAO.update(user);
    }

    @SuppressWarnings("unchecked")
    public void load() {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        for (int i = 0; i < userDAO.bestUser().length; i++) {
            String res = userDAO.bestUser()[i];
            String[] split = res.split(";");
            nameClassement[i] = split[0];
            niveauxClassement[i] = split[1];
        }
    }
}