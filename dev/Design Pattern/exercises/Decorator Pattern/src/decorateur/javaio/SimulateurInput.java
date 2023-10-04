package decorateur.javaio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimulateurInput {
    public static void main(String[] args) throws IOException {
        int c;
        InputStream in = new FileInputStream("fichierDecorateurInput.txt");
        in = new TransformeInputStream(in);
        while ((c = in.read()) >= 0) {
            System.out.print((char) c);
        }
        in.close();
    }
}