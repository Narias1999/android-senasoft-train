package com.example.nicolas.senasofttrain2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import base.Base;
import db.DaoSession;
import db.Usuarios;
import db.UsuariosDao;

public class LogIn extends AppCompatActivity {

    private DaoSession db;
    private UsuariosDao usuarios;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initVariables();
    }
    public void initVariables() {
        db = ((Base) getApplication()).getDaoSession();
        usuarios = db.getUsuariosDao();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }
    public void goToRegister(View view) {
        Intent intento = new Intent(LogIn.this, Register.class);
        startActivity(intento);
    }
    public Boolean validation() {
        String username = this.username.getText().toString(),
                password =this.password.getText().toString();
        return usuarios.queryBuilder().
                where(UsuariosDao.Properties.Usu_nick.eq(username),
                        UsuariosDao.Properties.Usu_password.eq(password)
                ).count() == 1;
    }
    public void handleLogin(View view) {

        if (validation()) {
            goToList();
        } else Toast.makeText(this, "Credenciales Incorrectos :(", Toast.LENGTH_SHORT).show();

    }

    public void goToList () {
        Intent route = new Intent(LogIn.this, ListUsers.class);
        route.putExtra("nombre", username.getText().toString());
        startActivity(route);
    }
}
