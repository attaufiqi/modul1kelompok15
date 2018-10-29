package com.example.attaufiqi.aplikasimodul2kel15;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ManageActivity extends AppCompatActivity {

    private User usermodel;
    private EditText etUsernameManage, etPasswordManage;
    private Button btnUpdate, btnDelete;
    DatabaseHandler chek = LoginActivity.getPresenter();
    User user = LoginActivity.getUser();
    String name = user.getUsername();
    String pass = user.getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        update();
        delete();
        deletePreference();
        etUsernameManage.setText(name);
        etPasswordManage.setText(pass);
        initUser();
    }

    private void initView() {
        etUsernameManage = findViewById(R.id.etUsernameManage);
        etPasswordManage = findViewById(R.id.etPasswordManage);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void initUser() {
        name = etUsernameManage.getText().toString();
        pass = etPasswordManage.getText().toString();

        user.setUsername(name);
        user.setPassword(pass);
    }

    private void update() {
        btnUpdate.setOnClickListener(v -> {
            initUser();
            chek.updateMahasiswa(user);
            Intent reg = new Intent(this, MainActivity.class);
            startActivity(reg);
            finish();
        });
    }

    private void delete() {
        btnDelete.setOnClickListener(view -> showAlertDialog());
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Yakin mau dihapus?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePreference();
                        Intent login = new Intent(ManageActivity.this, LoginActivity.class);
                        startActivity(login);
                        chek.deleteModel(user);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deletePreference(){
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }

    @Override
    public void onBackPressed() { back();}

    private void back(){
        Intent reg = new Intent( this, MainActivity.class);
        startActivity(reg);
        finish();
    }
}
