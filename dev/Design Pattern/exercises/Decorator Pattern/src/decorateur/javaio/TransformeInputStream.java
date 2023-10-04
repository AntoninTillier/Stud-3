package decorateur.javaio;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TransformeInputStream extends FilterInputStream {

    protected TransformeInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        char charManipule = Character.toLowerCase((char) c);
        if (charManipule == ',' || charManipule == '|') {
            charManipule = ';';
        }
        return (c == -1 ? c : charManipule);
    }
}