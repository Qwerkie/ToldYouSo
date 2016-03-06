package com.app.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Arguments extends Activity {
    View rootView;
    String sendit;
    ArrayList<String> messageArray = new ArrayList<String>();

    ListView argumentList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arguments);
        String[] myStringArray = {"A", "B", "C", "D", "E", "F"};
        ArrayAdapter<String> myAdapter =
                new ArrayAdapter<String>(
                        this,
                        R.layout.activity_new_argument,
                        R.id.TextBox,
                        myStringArray);
        ListView myList = (ListView) findViewById(R.id.ArgumentList);

        AdapterView.OnItemClickListener
                mMessageClickedHandler =
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Log.d("hello", sendit);
                    }
                };
        myList.setAdapter(myAdapter);
    }
}