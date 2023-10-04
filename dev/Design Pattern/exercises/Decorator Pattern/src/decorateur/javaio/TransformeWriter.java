package decorateur.javaio;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformeWriter extends FilterWriter {

    protected TransformeWriter(Writer out) {
        super(out);
    }

    public void write(String str) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss:sss");
        String strDate = sdf.format(date);
        super.write("[" + strDate + "]:" + str + "\n");
    }
}