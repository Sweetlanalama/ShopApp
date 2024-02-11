package com.example.shopapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        auth = FirebaseAuth.getInstance();


    }

    public void login(View view) {
        startActivity(new Intent(AuthenticationActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(AuthenticationActivity.this, RegistrationActivity.class));
    }
}