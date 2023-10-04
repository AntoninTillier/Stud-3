package com.aq.game.labyrinthe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.aq.Frame;
import com.aq.game.individu.Individu;
import com.aq.game.individu.Monstre;
import com.aq.game.objet.Objet;

public class Piece {
    int num;
    private Porte pN = null;
    private Porte pS = null;
    private Porte pO = null;
    private Porte pE = null;
    private Porte pB = null;
    boolean passageSecret;
    private ArrayList<Objet> objets = new ArrayList<Objet>();
    private ArrayList<Monstre> m = new ArrayList<Monstre>();
    private ArrayList<Individu> individu = new ArrayList<Individu>();
    BufferedImage image;

    public Piece(boolean passageSecret) {
        this.passageSecret = passageSecret;
    }

    public void ouverture() {
        for (Monstre e : m) {
            e.seDeplacer();
            e.collisionMur();
            e.collisionIndividu();
            if (m.size() > 1) {
                for (int i = 0; i < m.size() - 1; i++) {
                    e.colisiondeMonstre(m.get(i + 1));
                }
            }
            e.attaque();
        }
        if (getpN() != null)
            if (!getpN().isFermer() && Frame.joueur.getPosX() > (Frame.panelGame.getWidth() - 675) && Frame.joueur.getPosX() < (Frame.panelGame.getWidth() - 575) && Frame.joueur.getPosY() > 35 && Frame.joueur.getPosY() < 80) {
                Frame.joueur.setPosY(605);
                Frame.i -= 5;
            }
        if (getpS() != null)
            if (!getpS().isFermer() && Frame.joueur.getPosX() > (Frame.panelGame.getWidth() - 675) && Frame.joueur.getPosX() < (Frame.panelGame.getWidth() - 575) && Frame.joueur.getPosY() > (Frame.panelGame.getHeight() - 80) && Frame.joueur.getPosY() < Frame.panelGame.getHeight() - 35) {
                Frame.joueur.setPosY(90);
                Frame.i += 5;
            }
        if (getpO() != null)
            if (!getpO().isFermer() && Frame.joueur.getPosX() > 35 && Frame.joueur.getPosX() < 80 && Frame.joueur.getPosY() > (Frame.panelGame.getHeight() - 400) && Frame.joueur.getPosY() < Frame.panelGame.getHeight() - 300) {
                Frame.joueur.setPosX(1140);
                Frame.i--;
            }
        if (getpE() != null)
            if (!getpE().isFermer() && Frame.joueur.getPosX() > Frame.panelGame.getWidth() - 80 && Frame.joueur.getPosX() < Frame.panelGame.getWidth() - 65 && Frame.joueur.getPosY() > (Frame.panelGame.getHeight() - 400) && Frame.joueur.getPosY() < Frame.panelGame.getHeight() - 300) {
                Frame.joueur.setPosX(90);
                Frame.i++;
            }
        if (getpB() != null)
            if (!getpB().isFermer() && Frame.joueur.getPosX() > (Frame.panelGame.getWidth() - 100) / 2 && Frame.joueur.getPosX() < ((Frame.panelGame.getWidth() - 100) / 2) + 100 && Frame.joueur.getPosY() > (Frame.panelGame.getHeight() - 100) / 2 && Frame.joueur.getPosY() < ((Frame.panelGame.getHeight() - 100) / 2) + 35) {
                Frame.joueur.setPosX(90);
                Frame.i = 25;
            }
    }

    public void paint(Graphics g) {
        if (num != 25) {
            try {
                image = ImageIO.read(new File("img/map.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 0, 0, Frame.panelGame.getWidth(), Frame.panelGame.getHeight(), null);
            g.fillRect(0, 0, Frame.panelGame.getWidth(), 50);
            g.fillRect(0, 650, Frame.panelGame.getWidth(), 50);
            g.fillRect(0, 0, 50, Frame.panelGame.getHeight());
            g.fillRect(1220, 0, 50, Frame.panelGame.getHeight());
        } else {
            g.setColor(new Color(255, 215, 0));
            g.fillRect(0, 0, Frame.panelGame.getWidth(), Frame.panelGame.getHeight());
            g.setColor(new Color(238, 232, 170));
            g.fillRect(0, 0, Frame.panelGame.getWidth(), 50);
            g.fillRect(0, 650, Frame.panelGame.getWidth(), 50);
            g.fillRect(0, 0, 50, Frame.panelGame.getHeight());
            g.fillRect(1220, 0, 50, Frame.panelGame.getHeight());
        }
        for (int i = 0; i < m.size(); i++) {
            Monstre e = m.get(i);
            e.paint(g);
        }
        for (int i = 0; i < individu.size(); i++) {
            Individu ind = individu.get(i);
            ind.paint(g);
        }

        if (num != 12) {
            if (getpN() != null) getpN().paint_porte(g);
            if (getpS() != null) getpS().paint_porte(g);
            if (getpO() != null) getpO().paint_porte(g);
            if (getpE() != null) getpE().paint_porte(g);
            if (getpB() != null) getpB().paint_porte(g);
            for (Objet o : getObjets())
                o.paint(g);
        } else {
            if (getM().size() == 0) {
                if (getpB() != null) getpB().paint_porte(g);
                for (Objet o : getObjets())
                    o.paint(g);
            }
        }
    }

    public ArrayList<Individu> getIndividu() {
        return individu;
    }

    public void setIndividu(ArrayList<Individu> individu) {
        this.individu = individu;
    }

    public ArrayList<Monstre> getM() {
        return m;
    }

    public void setM(ArrayList<Monstre> m) {
        this.m = m;
    }

    public ArrayList<Objet> getObjets() {
        return objets;
    }

    public void setObjets(ArrayList<Objet> objets) {
        this.objets = objets;
    }

    public Porte getpN() {
        return pN;
    }

    public void setpN(Porte pN) {
        this.pN = pN;
    }

    public Porte getpS() {
        return pS;
    }

    public void setpS(Porte pS) {
        this.pS = pS;
    }

    public Porte getpO() {
        return pO;
    }

    public void setpO(Porte pO) {
        this.pO = pO;
    }

    public Porte getpE() {
        return pE;
    }

    public void setpE(Porte pE) {
        this.pE = pE;
    }

    public Porte getpB() {
        return pB;
    }

    public void setpB(Porte pB) {
        this.pB = pB;
    }
}