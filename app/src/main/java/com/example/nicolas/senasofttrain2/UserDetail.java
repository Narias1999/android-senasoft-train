package com.example.nicolas.senasofttrain2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UserDetail extends AppCompatActivity {

    TextView txtUsername, txtEmail, txtName, txtBirthDate;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getViews();
        setValues();
    }

    public TextView el(int id) {
        return (TextView) findViewById(id);
    }

    public void getViews() {
        txtBirthDate = el(R.id.dtlBirthDate);
        txtName = el(R.id.dtlName);
        txtEmail = el(R.id.dtlEmail);
        txtUsername = el(R.id.dtlUsername);
        iv = (ImageView) findViewById(R.id.dtlImage);
    }

    public void sv(TextView tv, String extraName) {
        tv.setText(getIntent().getStringExtra(extraName));
    }

    public void setValues() {
        sv(txtName, "name");
        sv(txtBirthDate, "birthDate");
        sv(txtEmail, "email");
        sv(txtUsername, "username");
        String url = getIntent().getStringExtra("image");
        Picasso.get().load(url).into(iv);
    }
}
