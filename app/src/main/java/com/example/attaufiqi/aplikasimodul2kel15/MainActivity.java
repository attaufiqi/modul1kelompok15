package com.example.attaufiqi.aplikasimodul2kel15;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private User usermodel;
    private TextView tvNameMain;
    private Button btnLogoutMain;
    //private Button btnAboutMain;
    private Button btnEmail;
    private Button btnAbout;
    private Button btnManage;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPreference();
        logout();
        email();
        about();
        manage();
    }

    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    private void initView() {
        tvNameMain = findViewById(R.id.tvNameMain);
        btnLogoutMain = findViewById(R.id.btnLogoutMain);
        btnEmail = findViewById(R.id.btnEmail);
        btnAbout = findViewById(R.id.btnAbout);
        btnManage = findViewById(R.id.btnManage);
        exit = findViewById(R.id.btnLogoutMain);
    }

    private void email() {
        btnEmail.setOnClickListener(v ->{
            Intent reg = new Intent( this, EmailActivity.class);
            startActivity(reg);
            finish();
        });
    }

    private void about() {
        btnAbout.setOnClickListener(v ->{
            Intent reg = new Intent( this, AboutActivity.class);
            startActivity(reg);
            finish();
        });
    }

    private void manage() {
        btnManage.setOnClickListener(v ->{
            Intent reg = new Intent( this, ManagaeActivity.class);
            startActivity(reg);
            finish();
        });
    }

    private void initPreference() {
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        tvNameMain.setText(username);
    }

    private void deletePreference(){
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }

    private void logout() {
        exit.setOnClickListener(view -> showAlertDialog());
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePreference();
                        Intent login = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private class ManagaeActivity {
    }
}