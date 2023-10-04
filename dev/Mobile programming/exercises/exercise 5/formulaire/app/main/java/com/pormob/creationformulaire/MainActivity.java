package com.pormob.creationformulaire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        final EditText username = findViewById(R.id.username);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().equals("") && !email.getText().equals("")
                        && !phone.getText().equals("") && !password.getText().equals("")) {
                    String res = username.getText() + ";" + email.getText() + ";" + phone.getText() + ";" + password.getText() + "\n";
                    saveRegister("registerFile.txt", res);
                }
                username.setText("");
                email.setText("");
                phone.setText("");
                password.setText("");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readRegister("registerFile.txt");
            }
        });
    }

    public void saveRegister(String name, String data) {
        try {
            FileOutputStream fos = openFileOutput(name, Context.MODE_APPEND);
            // Open the writer
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            // Write
            outputStreamWriter.write(data);
            // Close streams
            outputStreamWriter.close();
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found " + e, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void readRegister(String name) {
        try {
            //open the file and retrieve the inputStream
            InputStream inputStream = openFileInput(name);
            if (inputStream != null) {
                // open a reader on the inputStream
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                // String to use to store read lines
                String str;
                StringBuilder buf = new StringBuilder();
                ArrayList<String> username = new ArrayList<String>();
                ArrayList<String> email = new ArrayList<String>();
                ArrayList<String> phone = new ArrayList<String>();
                ArrayList<String> password = new ArrayList<String>();
                String separator = ";";
                // Read the file
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\r\n");
                    String[] arrExploded = explodeStringUsingCoreJava(str, separator);
                    for (int i = 0; i < arrExploded.length; i++) {
                        if (i == 0) {
                            username.add(arrExploded[i]);
                        } else if (i == 1) {
                            email.add(arrExploded[i]);
                        } else if (i == 2) {
                            phone.add(arrExploded[i]);
                        } else {
                            password.add(arrExploded[i]);
                        }
                    }
                }
                // Close the reader
                reader.close();
                // Close the inputStream
                inputStream.close();
                // Affect the text to the textView
                Log.v("msg", buf.toString());
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Account list");
                builder1.setMessage("ID Enregistrement : " + "\n" +
                        "Username : " + "\n" +
                        "Email : " + "\n" +
                        "Phone : " + "\n" +
                        "Password : ");
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
        }
    }

    public String[] explodeStringUsingCoreJava(String stringToExplode, String separator) {
        return stringToExplode.split(separator);
    }
}