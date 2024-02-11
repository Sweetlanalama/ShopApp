package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopapp.Fragments.MainFragment;
import com.example.shopapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

// Объявление класса MainActivity, наследующего BaseActivity
public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseDatabase database;
    FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        bottom_navigation();
        bottom_category();
        category1();
        category2();
        category3();
        category4();


        MainFragment mainFragment = new MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, mainFragment);

        TextView mainName = findViewById(R.id.main_name_profile);


    }


    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }

    private void bottom_category() {
        LinearLayout categoryBtn = findViewById(R.id.categoryBtn);

        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });
    }

    private void category4() {
        LinearLayout console = findViewById(R.id.console);

        console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "console");
                startActivity(intent);
            }
        });
    }

    private void category3() {
        LinearLayout headphone = findViewById(R.id.headphone);

        headphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "headphone");
                startActivity(intent);
            }
        });
    }

    private void category2() {
        LinearLayout phone = findViewById(R.id.phone);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "phone");
                startActivity(intent);
            }
        });
    }

    private void category1() {
        LinearLayout PC = findViewById(R.id.PC);

        PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "PC");
                startActivity(intent);
            }

        });
    }


    //Дейсвия при нажатии кнопок нижней навигации
    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout catalogBtn = findViewById(R.id.catalogBtn);

        homeBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        catalogBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CategoryActivity.class)));
    }


}


