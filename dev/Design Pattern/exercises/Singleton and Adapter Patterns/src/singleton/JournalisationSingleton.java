package singleton;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalisationSingleton {
    private static JournalisationSingleton jS = new JournalisationSingleton();
    private String log;
    private PrintWriter writer;

    private JournalisationSingleton() {
        this.log = new String();
        try {
            this.writer = new PrintWriter("log.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static JournalisationSingleton getInstance() {
        return jS;
    }

    public void journaliser(String log) {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
        this.log += "[" + dateFormat.format(d) + "] " + log + "\n";
        writer.println(this.log);

    }

    public String terminerJournal() {
        this.writer.close();
        return this.log;
    }
}