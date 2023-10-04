package TP1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Graph {
    private LinkedList<Noeud> noeuds = new LinkedList<Noeud>();
    private HashMap<Integer, Noeud> hmap = new HashMap<Integer, Noeud>();

    public Graph() { }

    public Graph(int k) {
        for (int i = 1; i < k + 1; i++) {
            this.noeuds.add(new Noeud(i));
            this.hmap.put(i, new Noeud(i));
        }
    }

    public Graph(String file) {
        try (Reader reader = Files.newBufferedReader(Paths.get(file));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String res = csvRecord.get(0);
                String[] split = res.split(";");
                int src = Integer.parseInt(split[0]);
                int cbl = Integer.parseInt(split[1]);
                if (!hmap.containsKey(src)) {
                    this.addNoeud(src);
                }
                if (!hmap.containsKey(cbl)) {
                    this.addNoeud(cbl);
                }
                this.addArc(src, cbl);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void export(String name) {
        String buff = "Source;Target\n";
        String sep = ";";
        for (int i : this.hmap.keySet()) {
            for (Arc a : this.hmap.get(i).getSucc()) {
                buff += a.getCible().getId() + sep + a.getSource().getId() + "\n";
            }
        }
        File outputFile = new File(this.getClass() + "-" + name + ".csv");
        FileWriter out;
        try {
            out = new FileWriter(outputFile);
            out.write(buff);
            out.close();
            System.out.println("exportation réussie");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("exportation n'a pu aboutir");
        }
    }

    public void addNoeud(int n) {
        for (Noeud noeud : noeuds) {
            if (noeud.getId() == n) {
                return;
            }
        }
        this.noeuds.add(new Noeud(n));
        this.hmap.put(n, new Noeud(n));
    }

    public Noeud getNoeud(int n) {
        /*
         * for (Noeud noeud : noeuds) { if(noeud.getId() == n) { return noeud; } }
         * return null;
         */
        if (this.hmap.containsKey(n))
            return this.hmap.get(n);
        return null;
    }

    public void addArc(int x, int y) {
        if (this.getNoeud(x) != null && this.getNoeud(y) != null) {
            if (!this.getNoeud(x).hasSuccesseur(y)) {
                Arc a = new Arc(this.getNoeud(x), this.getNoeud(y));
                this.getNoeud(x).getSucc().add(a);
            }
        }
    }

    public void addArc(int x, int y, double poids) {
        if (this.getNoeud(x) != null && this.getNoeud(y) != null) {
            if (!this.getNoeud(x).hasSuccesseur(y)) {
                Arc a = new Arc(this.getNoeud(x), this.getNoeud(y), poids);
                this.getNoeud(x).getSucc().add(a);
            }
        }
    }

    public void parcours() {
        for (int i : hmap.keySet()) {
            hmap.get(i).setMark(false);
        }
        for (int i : hmap.keySet()) {
            if (!hmap.get(i).getMark()) {
                profR(hmap.get(i), 1);
            }
        }
    }

    public void profR(Noeud n, int k) {
        n.setMark(true);
        System.out.println(n.getId());
        for (int i = 0; i < n.getSucc().size(); i++) {
            if (!n.getSucc().get(i).getCible().getMark()) {
                for (int j = 0; j < k; j++)
                    System.out.print("--");
                profR(getNoeud(n.getSucc().get(i).getCible().getId()), k + 1);
            }
        }
    }

    public void profI() {
        for (int i : hmap.keySet()) {
            hmap.get(i).setMark(false);
        }
        for (int i : hmap.keySet()) {
            if (!hmap.get(i).getMark()) {
                profI(hmap.get(i));
            }
        }
    }

    public void profI(Noeud n) {
        Stack<Noeud> s = new Stack<Noeud>();
        n.setMark(true);
        s.push(n);
        System.out.println(n.getId());
        while (!s.isEmpty()) {
            n = s.peek();
            boolean b = false;
            for (int i = 0; i < n.getSucc().size(); i++) {
                if (!n.getSucc().get(i).getCible().getMark())
                    b = true;
            }
            if (!b) {
                s.pop();
            } else {
                for (int j = 0; j < n.getSucc().size(); j++) {
                    if (!getNoeud(n.getSucc().get(j).getCible().getId()).getMark()) {
                        this.getNoeud(n.getSucc().get(j).getCible().getId()).setMark(true);
                        Noeud no = getNoeud(n.getSucc().get(j).getCible().getId());
                        s.push(no);
                        System.out.println("--" + no.getId());
                        break;
                    }
                }
            }
        }
    }

    public void largeur() {
        for (int i : hmap.keySet()) {
            hmap.get(i).setMark(false);
        }
        for (int i : hmap.keySet()) {
            if (!hmap.get(i).getMark()) {
                largeur(hmap.get(i));
            }
        }
    }

    public void largeur(Noeud n) {
        LinkedList<Noeud> l = new LinkedList<Noeud>();
        n.setMark(true);
        l.addFirst(n);
        System.out.println(n.getId());
        while (!l.isEmpty()) {
            n = l.getLast();
            l.removeLast();
            for (int i = 0; i < n.getSucc().size(); i++) {
                if (!getNoeud(n.getSucc().get(i).getCible().getId()).getMark()) {
                    this.getNoeud(n.getSucc().get(i).getCible().getId()).setMark(true);
                    Noeud no = this.getNoeud(n.getSucc().get(i).getCible().getId());
                    l.addFirst(no);
                    System.out.print("Succ size " + n.getSucc().size() + " - [ ");
                    for (Noeud nn : l) {
                        if (l.getLast().equals(nn)) {
                            System.out.print(nn.getId());
                        } else {
                            System.out.print(nn.getId() + ", ");
                        }
                    }
                    System.out.print(" ] ");
                    System.out.println(n.getId() + "--" + no.getId());
                }
            }
        }
    }

    public HashMap<Integer, Noeud> getHmap() {
        return this.hmap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < hmap.size() + 1; i++) {
            sb.append("Noeud = ");
            sb.append(this.getNoeud(i).toString() + "\n");
            if (!this.getNoeud(i).getSucc().isEmpty()) {
                sb.append("   " + "Arc :");
                for (Arc arc : this.getNoeud(i).getSucc()) {
                    sb.append(" " + arc.toString());
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}