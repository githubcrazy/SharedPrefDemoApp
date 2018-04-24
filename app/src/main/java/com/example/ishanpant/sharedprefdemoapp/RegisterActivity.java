package com.example.ishanpant.sharedprefdemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button registered;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DbHelper(this,null,null,4);
        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
        registered = (Button) findViewById(R.id.registered);
        registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registered :
                addThisUser();
                Toast.makeText(this,"User Successfully Registered...",Toast.LENGTH_SHORT).show();
        }
    }

    private void addThisUser() {
        String etEmail = editTextEmail.getText().toString();
        String etPass =editTextPassword.getText().toString();
        dbHelper.addUser(etEmail,etPass);
    }
}
