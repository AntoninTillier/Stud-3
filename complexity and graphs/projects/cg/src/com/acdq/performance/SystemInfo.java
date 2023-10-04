package com.acdq.performance;

import java.io.File;
import java.text.NumberFormat;

public class SystemInfo {
    private Runtime runtime = Runtime.getRuntime();

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("|-----------------------------------------------------------------------------|");
        sb.append(this.getOsInfo());
        sb.append("|-----------------------------------------------------------------------------|");
        sb.append(this.getMemInfo());
        sb.append("|-----------------------------------------------------------------------------|");
        sb.append(this.getDiskInfo());
        sb.append("|-----------------------------------------------------------------------------|");
        return sb.toString();
    }

    public String getMemInfo() {
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        sb.append("\n");
        sb.append("|\t Mémoire libre (Mégabyte): ");
        sb.append(format.format(freeMemory / Math.pow(10, 6)));
        sb.append("\n");
        sb.append("|\t Mémoire allouée (Mégabyte): ");
        sb.append(format.format(allocatedMemory / Math.pow(10, 6)));
        sb.append("\n");
        sb.append("|\t Mémoire maximale (Mégabyte): ");
        sb.append(format.format(maxMemory / Math.pow(10, 6)));
        sb.append("\n");
        sb.append("|\t Mémoire libre totale (Mégabyte): ");
        sb.append(format.format((freeMemory + (maxMemory - allocatedMemory) / Math.pow(10, 6))));
        sb.append("\n");
        return sb.toString();

    }

    public String getOsInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("|\t OS: ");
        sb.append(this.OSname());
        sb.append("\n");
        sb.append("|\t Version: ");
        sb.append(this.OSversion());
        sb.append("\n");
        sb.append("|\t OS Arch : ");
        sb.append(this.OsArch());
        sb.append("\n");
        sb.append("|\t Version de java : ");
        sb.append(this.JavaVersion());
        sb.append("\n");
        sb.append("|\t JVM version : ");
        sb.append(this.JVMVersion());
        sb.append("\n");
        sb.append("|\t Processeurs disponibles (cores): ");
        sb.append(runtime.availableProcessors());
        sb.append("\n");
        return sb.toString();
    }

    public String getDiskInfo() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        StringBuilder sb = new StringBuilder();
        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("\n");
            sb.append("|\t Fichier systeme racine : ");
            sb.append(root.getAbsolutePath());
            sb.append("\n");
            sb.append("|\t Espace totale (Gigabyte): ");
            sb.append(root.getTotalSpace() / Math.pow(10, 9));
            sb.append("\n");
            sb.append("|\t Espace utilisable totale (Gigabyte): ");
            sb.append(root.getFreeSpace() / Math.pow(10, 9));
            sb.append("\n");
            sb.append("|\t Espace utilisable maintenant (Gigabyte): ");
            sb.append(root.getUsableSpace() / Math.pow(10, 9));
            sb.append("\n");
        }
        return sb.toString();
    }

    private String OSname() {
        return System.getProperty("os.name");
    }

    private String OSversion() {
        return System.getProperty("os.version");
    }

    private String OsArch() {
        return System.getProperty("os.arch");
    }

    private String JavaVersion() {
        return System.getProperty("java.version");
    }

    private String JVMVersion() {
        return System.getProperty("java.vm.version");
    }

    public long totalMem() {
        return Runtime.getRuntime().totalMemory();
    }

    public long usedMem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}