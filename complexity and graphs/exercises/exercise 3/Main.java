package TP3;

public class Main {
    public static void main(String[] args) {
        RandomGraph rg = new RandomGraph(15, 0.3);
        rg.export("rg");
        System.out.println("----------------------------");
        RandomGraph rgg = new RandomGraph(15, 32);
        rgg.export("rgg");
        System.out.println("----------------------------");
    }
}