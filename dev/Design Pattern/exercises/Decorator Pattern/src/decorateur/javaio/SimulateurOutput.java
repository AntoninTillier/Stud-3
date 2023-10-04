package decorateur.javaio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SimulateurOutput {
    public static void main(String[] args) throws IOException {
        Writer out = new TransformeWriter(new FileWriter("fichierDecorateurOutput.txt"));
        out.write("Test decorateur output Stream");
        out.write("Un autre test decorateur output Stream");
        out.close();
    }
}