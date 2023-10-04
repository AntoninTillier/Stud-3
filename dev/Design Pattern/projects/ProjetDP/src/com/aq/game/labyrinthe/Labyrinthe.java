package com.aq.game.labyrinthe;

import java.util.ArrayList;

import com.aq.game.individu.Cuisinier;
import com.aq.game.individu.Medecin;
import com.aq.game.individu.Monstre;
import com.aq.game.objet.Cle;
import com.aq.game.objet.Medicament;
import com.aq.game.objet.Nourriture;

public class Labyrinthe {
    ArrayList<Piece> lab = new ArrayList<Piece>();
    int totalMonstre;

    public Labyrinthe() {
        this.creeLab();
    }

    public void creeLab() {
        int a = 0;
        while (a <= 25) {
            lab.add(new Piece((a == 25) ? true : false));
            a++;
        }
        a = 0;
        for (Piece p : lab) {
            p.num = a;
            if (a == 0) {
                p.setpS(new Porte(false, 's'));
                p.setpE(new Porte(false, 'e'));
            }
            if (a == 4) {
                p.setpS(new Porte(false, 's'));
                p.setpO(new Porte(false, 'o'));
            }
            if (a == 20) {
                p.setpN(new Porte(false, 'n'));
                p.setpE(new Porte(false, 'e'));
            }
            if (a == 24) {
                p.setpN(new Porte(false, 'n'));
                p.setpO(new Porte(false, 'o'));
            }
            if (a == 12)
                p.setpB(new Porte(true, 'b'));
            if (a == 5 || a == 10 || a == 15) {
                p.setpN(new Porte(false, 'n'));
                p.setpS(new Porte(false, 's'));
                p.setpE(new Porte(false, 'e'));
            }
            if (a == 9 || a == 14 || a == 19) {
                p.setpN(new Porte(false, 'n'));
                p.setpS(new Porte(false, 's'));
                p.setpO(new Porte(false, 'o'));
            }
            if (a >= 1 && a <= 3 || a >= 21 && a <= 23) {
                if (a >= 21 && a <= 23)
                    p.setpN(new Porte(false, 'n'));
                else
                    p.setpS(new Porte(false, 's'));
                p.setpO(new Porte(false, 'o'));
                p.setpE(new Porte(false, 'e'));
            }
            if (a == 6 || a == 7 || a == 8 || a == 11 || a == 13 || a == 16 || a == 17 || a == 18) {
                if (a != 17)
                    p.setpN(new Porte(false, 'n'));
                else
                    p.setpN(new Porte(true, 'n'));
                if (a != 7)
                    p.setpS(new Porte(false, 's'));
                else
                    p.setpS(new Porte(true, 's'));
                if (a != 13)
                    p.setpO(new Porte(false, 'o'));
                else
                    p.setpO(new Porte(true, 'o'));
                if (a != 11)
                    p.setpE(new Porte(false, 'e'));
                else
                    p.setpE(new Porte(true, 'e'));
            }
            a++;
        }
        a = 0;
        int c = 2;
        for (Piece p : lab) {
            if (a == 25) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                p.getM().add(new Monstre(x, y));
            }
            if (a % 5 == 1 && c != 5) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                p.getObjets().add(new Cle(c, x, y));
                c++;
            }
            if (a == 12) {
                p.getObjets().add(new Cle(10, 100, 100));
                p.getObjets().add(new Nourriture(1, 1150, 600));
                p.getObjets().add(new Medicament(50, 100, 600));
                int z = 0;
                while (z < 3) {
                    int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                    int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                    p.getM().add(new Monstre(x, y));
                    z++;
                }
            }
            int b = (int) (Math.random() * a);
            if (a != 12 && a != 25 && b > 0 && b < 10) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                p.getM().add(new Monstre(x, y));
                if ((a + 1) % 5 == 2 && c == 5) {
                    p.getObjets().add(new Medicament(50, x, y));
                    c++;
                }
                if (totalMonstre == 1)
                    p.getObjets().add(new Cle(1, 100, 100));
                totalMonstre++;
                if (a % 9 == 6) {
                    int xx = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                    int yy = 100 + (int) (Math.random() * (600 - 100) + 1);
                    p.getM().add(new Monstre(xx, yy));
                    totalMonstre++;
                }
            }
            a++;
        }
        if (totalMonstre >= 16) {
            int rand = (int) (Math.random() * 25);
            if (rand == 12)
                rand++;
            int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
            int y = 100 + (int) (Math.random() * (600 - 100) + 1);
            lab.get(rand).getObjets().add(new Nourriture(1, x, y));
        }
        a = 0;
        while (a < 2) {
            int rand = (int) (Math.random() * 25);
            if (rand == 12)
                rand++;
            int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
            int y = 100 + (int) (Math.random() * (600 - 100) + 1);
            if (a == 0)
                lab.get(rand).getIndividu().add(new Medecin(x, y));
            if (a == 1)
                lab.get(rand).getIndividu().add(new Cuisinier(x, y));
            a++;
        }
    }

    public ArrayList<Piece> getLab() {
        return lab;
    }

    public int getTotalMonstre() {
        return totalMonstre;
    }
}