package com.example.attaufiqi.aplikasimodul2kel15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHandler MyDatabase;
    private ArrayList<String> ListData;

    // static variable
    private static final int DATABASE_VERSION = 1;


    @Override
    public void onBackPressed() { back();}

    private void back(){
        Intent reg = new Intent( this, MainActivity.class);
        startActivity(reg);
        finish();
    }

}