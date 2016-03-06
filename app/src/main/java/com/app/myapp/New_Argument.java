package com.app.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class New_Argument extends Activity {

    Button btnAgree;
    String sendit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_argument);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL url = null;
                try {
                    url = new URL("http://129.21.120.201:8080/itoldyouso/itoldyouso");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                URLConnection con = null;
                try {
                    con = url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (con == null) //sends an error message when it cant connect to the server
                {
                    sendit = "Error in no connection with server";
                } else {
                    String messageID = "abc";
                    sendit = messageID + "' : 1'" + "'";

                    con.setDoOutput(true);
                    OutputStreamWriter out = null;
                    try {
                        out = new OutputStreamWriter(con.getOutputStream());
                        out.write(sendit);
                        out.close();
                        out.write(sendit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Log.d("inputString", sendit);
    }
}
