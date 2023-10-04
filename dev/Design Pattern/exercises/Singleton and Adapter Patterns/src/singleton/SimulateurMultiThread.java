package singleton;

public class SimulateurMultiThread {


    public static void main(String[] args) {
        Runnable runnable = new RunnableMultiThreadCanard();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.start();
        Thread thread4 = new Thread(runnable);
        thread4.start();
        Thread thread5 = new Thread(runnable);
        thread5.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //JournalisationSingleton.getInstance().terminerJournal();
    }
}