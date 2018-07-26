package com.example.nicolas.senasofttrain2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import base.Base;
import db.DaoSession;
import db.Usuarios;
import db.UsuariosDao;

import static db.UsuariosDao.Properties.Usu_nick;

public class Register extends AppCompatActivity {

    EditText txtUsername, txtEmail, txtDate, txtPassword, txtName;

    private DatePickerDialog datePickerUi;
    private SimpleDateFormat dateFormat;
    private Usuarios userInstance;
    private DaoSession db;
    private UsuariosDao usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initVariables();
    }

    public void initVariables() {
        txtUsername = getById(R.id.username);
        txtEmail = getById(R.id.email);
        txtDate = getById(R.id.birthDate);
        txtPassword = getById(R.id.password);
        txtName = getById(R.id.name);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        db = ((Base) getApplication()).getDaoSession();
        usuarios = db.getUsuariosDao();
    }

    public boolean validateCamps () {
        if(TextUtils.isEmpty(txtName.getText())) {
            txtName.setError("Campo requerido");
            return  false;
        }
        if(TextUtils.isEmpty(txtUsername.getText())) {
            txtUsername.setError("Campo requerido");
            return  false;
        }
        if(TextUtils.isEmpty(txtDate.getText())) {
            txtDate.setError("Campo requerido");
            return  false;
        }
        if(TextUtils.isEmpty(txtEmail.getText())) {
            txtEmail.setError("Campo requerido");
            return  false;
        }
        if(TextUtils.isEmpty(txtPassword.getText())) {
            txtPassword.setError("Campo requerido");
            return  false;
        }
        return  true;
    }

    public EditText getById (int id) {
        return (EditText)findViewById(id);
    }

    public void goToLogIn(View view) {
        Intent intento = new Intent(Register.this, LogIn.class);
        startActivity(intento);
    }

    public String getText (EditText textBox) {
        return textBox.getText().toString();
    }

    public void handleRegister(View view) {
        boolean valid = validateCamps();
        if (valid) {
            String name = getText(txtName), nick = getText(txtUsername), date = getText(txtDate),
                    password = getText(txtPassword), email = getText(txtEmail);

            if(usuarios.queryBuilder().where(Usu_nick.eq(nick)).count() != 1) {
                userInstance = new Usuarios(null, name, nick, date, email, password);
                Toast.makeText(this, nick, Toast.LENGTH_SHORT).show();
                db.insert(userInstance);
            }  else Toast.makeText(this, "Usuario ya creado", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleBirthDate(View view) {
        final Calendar myCalendar = Calendar.getInstance();
        datePickerUi = new DatePickerDialog(Register.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dateFormat.format(myCalendar.getTime()).toString());
                    }
        }, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerUi.show();
    }
}
