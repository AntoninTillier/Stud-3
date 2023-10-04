package com.acdq.performance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Fichier {
    private StringBuilder res;
    private String name;

    public Fichier(StringBuilder s, String name) {
        this.res = s;
        this.name = name;
        this.addFichier();
    }

    private void addFichier() {
        try {
            if (!new File(this.name + " graph " + new Date(System.currentTimeMillis()) + ".txt").exists()) {
                new File(this.name + " graph " + new Date(System.currentTimeMillis()) + ".txt").createNewFile();
            }
            File fichier = new File(this.name + " graph " + new Date(System.currentTimeMillis()) + ".txt");
            try {
                FileWriter writer = new FileWriter(fichier);
                try {
                    writer.write(res.toString());
                } finally {
                    writer.close();
                }
            } catch (Exception e) {
            }
        } catch (IOException e) {
        }
    }
}