package com.aq;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aq.game.individu.Monstre;

public class PiouPiou {
    int pNum = Frame.i;
    private int PiouPiouX;
    private int PiouPiouY;
    private int dmg = 4 * Frame.joueur.getNiveaux() + 4;
    int bufftire = 0;
    int cadence = 20;
    double vitesse = 0.09;
    double angle = getAngle(Frame.ml.x, Frame.ml.y, Frame.joueur.getPosX(), Frame.joueur.getPosY()) + Math.PI / 2;
    protected ArrayList<PiouPiou> pioupiou = new ArrayList<PiouPiou>();

    public PiouPiou() { }

    public PiouPiou(int PiouPiouX, int PiouPiouY) {
        this.PiouPiouX = PiouPiouX;
        this.PiouPiouY = PiouPiouY;
    }

    public double getAngle(double mlx, double mly, double x, double y) {
        if (x > mlx) {
            if (y > mly) {
                return Math.atan((y - mly) / (x - mlx)) + Math.PI / 2;
            } else if (y < mly) {
                return Math.atan((x - mlx) / (mly - y));
            } else {
                return Math.PI / 2;
            }
        } else if (x < mlx) {
            if (y > mly) {
                return Math.atan((mlx - x) / (y - mly)) + Math.PI;
            } else if (y < mly) {
                return Math.atan((mly - y) / (mlx - x)) + 3 * Math.PI / 2;
            } else {
                return 3 * Math.PI / 2;
            }
        } else {
            if (y > mly) {
                return Math.PI;
            } else if (y < mly) {
                return 0;
            } else {
                return 0;
            }
        }
    }

    public void animation() {
        if (!Frame.ml.tire)
            bufftire = 0;
        if (Frame.ml.tire) {
            bufftire++;
            if (bufftire % cadence == 0) {
                pioupiou.add(new PiouPiou(Frame.joueur.getPosX(), Frame.joueur.getPosY()));
            }
        }
        for (int i = 0; i < pioupiou.size(); i++) {
            PiouPiou p = pioupiou.get(i);
            if (p.PiouPiouX < 50 || p.PiouPiouX > Frame.panelGame.getWidth() - 50) {
                pioupiou.remove(p);
            }
            if (p.PiouPiouY < 50 || p.PiouPiouY > Frame.panelGame.getHeight() - 50) {
                pioupiou.remove(p);
            }
            if (pNum != Frame.i) {
                pioupiou.removeAll(pioupiou);
                pNum = Frame.i;
            }
            for (int j = 0; j < Frame.labyrinthe.getLab().get(Frame.i).getM().size(); j++) {
                Monstre e = Frame.labyrinthe.getLab().get(Frame.i).getM().get(j);
                if (Math.pow((p.PiouPiouX - e.getPosX()), 2) + Math.pow((-p.PiouPiouY + e.getPosY()), 2) <= (20 * e.getPv())) {
                    String s = e.getClass().toString();
                    e.setPv(e.getPv() - dmg);
                    if (e.getPv() <= 20) {
                        Frame.labyrinthe.getLab().get(Frame.i).getM().remove(e);
                    }
                    pioupiou.remove(p);
                }
            }
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < pioupiou.size(); i++) {
            PiouPiou p = pioupiou.get(i);
            g.fillOval(p.PiouPiouX - 10, p.PiouPiouY - 10, 20, 20);
            p.PiouPiouX += (int) (vitesse * Math.toDegrees(Math.cos(p.angle)));
            p.PiouPiouY += (int) (vitesse * Math.toDegrees(Math.sin(p.angle)));
        }
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}