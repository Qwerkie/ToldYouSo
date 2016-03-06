package com.app.myapp;

/**
 * Created by Ryan on 3/6/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.net.URL;
import java.net.URLConnection;
import com.app.myapp.R;

public class Loginserv extends Activity {

    EditText edtuserid,edtpass;
    Button skipit;
    Button btnlogin;
    ProgressBar pbbar;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        edtuserid = (EditText) findViewById(R.id.edtuserid);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        skipit = (Button) findViewById(R.id.skipit);

        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);

        skipit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Loginserv.this, Arguments.class);
                startActivity(i);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin  doLogin = new DoLogin();
                doLogin.execute("");

            }
        });

    }

    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();



        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(Loginserv.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                Intent i = new Intent(Loginserv.this, Arguments.class);
                startActivity(i);
                finish();
            }

        }

        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try
                {
                    URL url = new URL("http://129.21.123.101:8080/itoldyouso/itoldyouso");
                    URLConnection con = url.openConnection();
                    String sendit = "login:" + userid + ":" + password;

                    Log.d("inputString", sendit);

                    con.setDoOutput(true);
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(sendit);
                    out.close();


                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String returnString = "";


                    while ((returnString = in.readLine()) != null) {

                    }
                    in.close();


                    runOnUiThread(new Runnable() {
                        public void run() {

                            z = "Login successfull";    //tells if login was successfully found on table
                            isSuccess=true;

                        }
                    });
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Bleh";
                }
            }
            return z;
        }
    }


}