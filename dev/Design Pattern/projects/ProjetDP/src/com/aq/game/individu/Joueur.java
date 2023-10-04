package com.aq.game.individu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aq.Frame;
import com.aq.game.individu.comportement.collision.CollisionIndividu;
import com.aq.game.individu.comportement.collision.CollisionMur;
import com.aq.game.individu.comportement.collision.ComportementCollision;
import com.aq.game.individu.comportement.deplacement.*;
import com.aq.game.labyrinthe.Porte;
import com.aq.game.objet.Objet;

public class Joueur extends Individu {
    ArrayList<Objet> inventaire = new ArrayList<Objet>();
    ArrayList<Objet> deplaceObjet = new ArrayList<Objet>();
    boolean objet = false;
    int count = 0;
    protected double dirX = 0;
    protected double dirY = 0;
    private int niveaux = 0;
    private int pv = 50;

    public Joueur() {
        comportementDeplacement = (ComportementDeplacement) new DeplacementJoueur();
        this.setPosX(100 + (int) (Math.random() * ((1100 - 100) + 1)));
        this.setPosY(100 + (int) (Math.random() * (600 - 100) + 1));
    }

    public void seSoigner(Objet o, int vie) {
        if (getPv() < 50)
            setPv(getPv() + vie);
        if (getPv() > 50)
            setPv(50);
        inventaire.remove(o);
    }

    public void seNourrir(Objet o, int upDmg) {
        Frame.pioupiou.setDmg(Frame.pioupiou.getDmg() + upDmg);
        inventaire.remove(o);
    }

    public void enleverObjet() {
        for (int i = 0; i < inventaire.size(); i++) {
            Objet o = inventaire.get(i);
            if (Frame.ml.x > o.getPosX() && Frame.ml.x < o.getPosX() + 35 && Frame.ml.y > o.getPosY()
                    && Frame.ml.y < o.getPosY() + 35 && Frame.ml.clic && !objet) {
                deplaceObjet.add(o);
                inventaire.remove(o);
                objet = true;
            }
        }
        for (int i = 0; i < deplaceObjet.size(); i++) {
            Objet o = deplaceObjet.get(i);
            if (Frame.ml.clic) {
                Frame.ml.tire = false;
                o.setPosX(Frame.ml.x);
                o.setPosY(Frame.ml.y);
                count++;
            } else if (count < 45) {
                o.setPosX(100 + (int) (Math.random() * ((1100 - 100) + 1)));
                o.setPosY(100 + (int) (Math.random() * (600 - 100) + 1));
                Frame.labyrinthe.getLab().get(Frame.i).getObjets().add(o);
                deplaceObjet.remove(o);
                count = 0;
                objet = false;
            } else {
                if (o.getPosX() > 60 && o.getPosX() < 1210 && o.getPosY() > 60 && o.getPosY() < 640) {
                    Frame.labyrinthe.getLab().get(Frame.i).getObjets().add(o);
                    deplaceObjet.remove(o);
                    count = 0;
                    objet = false;
                } else {
                    o.setPosX(100 + (int) (Math.random() * ((1100 - 100) + 1)));
                    o.setPosY(100 + (int) (Math.random() * (600 - 100) + 1));
                    Frame.labyrinthe.getLab().get(Frame.i).getObjets().add(o);
                    deplaceObjet.remove(o);
                    count = 0;
                    objet = false;
                }
            }
        }
    }

    public void interagirIndividu() {
        for (int i = 0; i < Frame.labyrinthe.getLab().get(Frame.i).getIndividu().size(); i++) {
            Individu ind = Frame.labyrinthe.getLab().get(Frame.i).getIndividu().get(i);
            String s = ind.getClass().toString();
            double a = Frame.joueur.getPosX() - ind.getPosX();
            double b = Frame.joueur.getPosY() - ind.getPosY();
            double l = Math.sqrt(a * a + b * b);
            a = a / l;
            b = b / l;
            if (l < 50) {
                if (s.equals("class com.aq.game.individu.Medecin") && inventaire.size() < 6 && ind.objet.size() != 0) {
                    inventaire.add(ind.objet.get(0));
                    ind.objet.remove(ind.objet.get(0));
                }
                if (s.equals("class com.aq.game.individu.Cuisinier") && inventaire.size() < 6 && ind.objet.size() != 0) {
                    inventaire.add(ind.objet.get(0));
                    ind.objet.remove(ind.objet.get(0));
                }
            }
        }
    }

    public void prendreObjet() {
        if (inventaire.size() < 6) {
            for (int j = 0; j < Frame.labyrinthe.getLab().get(Frame.i).getObjets().size(); j++) {
                Objet o = Frame.labyrinthe.getLab().get(Frame.i).getObjets().get(j);
                double a = Frame.joueur.getPosX() - o.getPosX();
                double b = Frame.joueur.getPosY() - o.getPosY();
                double l = Math.sqrt(a * a + b * b);
                a = a / l;
                b = b / l;
                if (l < 50) {
                    if (Frame.ml.x > o.getPosX() && Frame.ml.x < o.getPosX() + 35 && Frame.ml.y > o.getPosY()
                            && Frame.ml.y < o.getPosY() + 35) {
                        inventaire.add(o);
                        Frame.labyrinthe.getLab().get(Frame.i).getObjets().remove(o);
                    }
                }
            }
        }
    }

    public void ouvrirPorteF() {
        for (int i = 0; i < inventaire.size(); i++) {
            String s = inventaire.get(i).getClass().toString();
            if (s.equals("class com.aq.game.objet.Cle")) {
                Porte p = null;
                if (Frame.labyrinthe.getLab().get(Frame.i).getpN() != null && Frame.labyrinthe.getLab().get(Frame.i).getpN().isFermer())
                    p = Frame.labyrinthe.getLab().get(Frame.i).getpN();
                if (Frame.labyrinthe.getLab().get(Frame.i).getpS() != null && Frame.labyrinthe.getLab().get(Frame.i).getpS().isFermer())
                    p = Frame.labyrinthe.getLab().get(Frame.i).getpS();
                if (Frame.labyrinthe.getLab().get(Frame.i).getpO() != null && Frame.labyrinthe.getLab().get(Frame.i).getpO().isFermer())
                    p = Frame.labyrinthe.getLab().get(Frame.i).getpO();
                if (Frame.labyrinthe.getLab().get(Frame.i).getpE() != null && Frame.labyrinthe.getLab().get(Frame.i).getpE().isFermer())
                    p = Frame.labyrinthe.getLab().get(Frame.i).getpE();
                if (Frame.labyrinthe.getLab().get(Frame.i).getpB() != null && Frame.labyrinthe.getLab().get(Frame.i).getpB().isFermer())
                    p = Frame.labyrinthe.getLab().get(Frame.i).getpB();
                if (p != null) {
                    double a = Frame.joueur.getPosX() - p.getPosX();
                    double b = Frame.joueur.getPosY() - p.getPosY();
                    double l = Math.sqrt(a * a + b * b);
                    a = a / l;
                    b = b / l;
                    if (l < 90) {
                        if (p.getNum() == inventaire.get(i).getNum()) {
                            p.setFermer(false);
                            inventaire.remove(inventaire.get(i));
                        }
                    }
                }
                if (Frame.labyrinthe.getLab().get(Frame.i).getpB() != null && p == Frame.labyrinthe.getLab().get(Frame.i).getpB()
                        && Frame.labyrinthe.getLab().get(Frame.i).getpB().isFermer()) {
                    double a = Frame.joueur.getPosX() - p.getPosX();
                    double b = Frame.joueur.getPosY() - p.getPosY();
                    double l = Math.sqrt(a * a + b * b);
                    a = a / l;
                    b = b / l;
                    if (l < 90) {
                        if (p.getNum() == inventaire.get(i).getNum()) {
                            p.setFermer(false);
                            inventaire.remove(inventaire.get(i));
                        }
                    }
                }
            }
        }
    }

    public void choiObjet() {
        for (int i = 0; i < inventaire.size(); i++) {
            Objet o = inventaire.get(i);
            if (Frame.ml.x > o.getPosX() && Frame.ml.x < o.getPosX() + 35 && Frame.ml.y > o.getPosY() && Frame.ml.y < o.getPosY() + 35) {
                count++;
                if (count == 66) {
                    String s = o.getClass().toString();
                    if (s.equals("class com.aq.game.objet.Nourriture"))
                        seNourrir(o, o.getUpDmg());
                    if (s.equals("class com.aq.game.objet.Medicament"))
                        seSoigner(o, o.getPdv());
                    count = 0;
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Frame.pioupiou.animation();
        Frame.pioupiou.paint(g);
        g.setColor(new Color(181, 23, 0));
        g.fillOval(this.getPosX() + ((50 - getPv()) / 2) - 25, this.getPosY() + ((50 - getPv()) / 2) - 25, getPv(), getPv());
        for (int i = 0; i < inventaire.size(); i++) {
            Objet o = inventaire.get(i);
            o.setPosX(10 + 35 * i);
            o.setPosY(10);
            o.paint(g);
        }
        if (inventaire.size() == 6) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Max", 225, 30);
        }
        for (int i = 0; i < deplaceObjet.size(); i++) {
            Objet o = deplaceObjet.get(i);
            o.paint(g);
        }
    }

    @Override
    public void collisionMur() {
        ComportementCollision comportementCollision = (ComportementCollision) new CollisionMur();
        comportementCollision.collision();
    }

    @Override
    public void collisionIndividu() {
        ComportementCollision comportementCollision = (ComportementCollision) new CollisionIndividu();
        comportementCollision.collision();
    }

    public double getDirX() {
        return this.dirX;
    }

    public void setDirX(Double dirX) {
        this.dirX = dirX;
    }

    public double getDirY() {
        return this.dirY;
    }

    public void setDirY(Double dirY) {
        this.dirY = dirY;
    }

    public int getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(int niveaux) {
        this.niveaux = niveaux;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }
}