package com.pormob.affichetexte;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView monTexte = (TextView) findViewById(R.id.textView2);
        String nameApp = getString(R.string.app_name);
        monTexte.setText(nameApp);
        TextView monTexte2 = (TextView) findViewById(R.id.textView3);
        String x = nameApp + "xxx";
        monTexte2.setText(x);
    }
}