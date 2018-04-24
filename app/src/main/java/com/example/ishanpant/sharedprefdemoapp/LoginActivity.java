package com.example.ishanpant.sharedprefdemoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextForEmail;
    private EditText editTextForPassword;
    private Button login,register;
    private DbHelper dbHelper;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DbHelper(this,null,null,4);
        session = new Session(this);
        editTextForEmail = (EditText) findViewById(R.id.edit_text_for_email);
        editTextForPassword = (EditText) findViewById(R.id.edit_text_for_password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        if(session.loggedIn()) {
            startActivity(new Intent(LoginActivity.this,SharedPrefActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login :
                login();
                break;
            case R.id.register :
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }
    }

    public void login() {
        if(dbHelper.getUser(editTextForEmail.getText().toString(),editTextForPassword.getText().toString())){
            session.setLoggedIn(true);
            startActivity(new Intent(LoginActivity.this,SharedPrefActivity.class));
            Toast.makeText(this,"Successful Logged In...",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Cannot Be Logged In...",Toast.LENGTH_SHORT).show();
        }
    }
}
