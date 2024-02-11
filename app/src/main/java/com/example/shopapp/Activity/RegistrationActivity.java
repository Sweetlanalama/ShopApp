package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;
import com.example.shopapp.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    EditText name, email, password;
    TextView signIn;

    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        signUp =  findViewById(R.id.reg_btn);
        name =  findViewById(R.id.name);
        email =  findViewById(R.id.email_reg);
        password =  findViewById(R.id.password_reg);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createUser();
                progressBar.setVisibility(View.VISIBLE);

            }

            private void createUser() {

                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegistrationActivity.this, "Имя не заполнено!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(RegistrationActivity.this, "Почта не заполнена!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userPassword)) {
                    Toast.makeText(RegistrationActivity.this, "Пароль не заполнен!", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (userPassword.length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Пароль должен содержать не менее 6 символов", Toast.LENGTH_SHORT).show();
                    return;
                }

                // создание пользователя
                auth.createUserWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    UserModel userModel = new UserModel(userName, userEmail, userPassword);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("User").child(id).setValue(userModel);
                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(RegistrationActivity.this, "Аккаунт создан", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                }
                                else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegistrationActivity.this, "Ошибка:"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

        });

    }

    //public void goToLogin(View view) {
        //startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    //}
}