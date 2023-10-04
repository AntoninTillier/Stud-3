package com.pormob.helloblois;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // on crée un nouveau TextView, qui est un widget permettant
        // d'afficher du texte
        TextView tv = new TextView(this);
        // configurer le texte à faire afficher par notre widget
        String mot = "Hello Blois!";
        tv.setText(mot);
        /*
            Log.i ==> info
            Log.w ==> warning
            Log.v ==> verbose
            Log.d ==> debug
         */
        Log.v("MON_TAG", "LogCat Hello Blois!");
        // remplacer tout le contenu de notre activité par le TextView
        setContentView(tv);
    }
}