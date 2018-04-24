package com.example.ishanpant.sharedprefdemoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SharedPrefActivity extends AppCompatActivity {
    private Button logout;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        session = new Session(this);
        if(!session.loggedIn()) {
            Logout();
        }
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Logout();
            }
        });
    }

    private void Logout() {
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(SharedPrefActivity.this,LoginActivity.class));
    }
}
