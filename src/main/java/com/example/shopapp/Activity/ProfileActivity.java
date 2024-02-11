package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopapp.Fragments.MyCartFragment;
import com.example.shopapp.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottom_navigation();

        MyCartFragment myCartFragment = new MyCartFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout_profile, myCartFragment);

    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout catalogBtn = findViewById(R.id.catalogBtn);

        homeBtn.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this,MainActivity.class)));
        cartBtn.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this,CartActivity.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this,ProfileActivity.class)));
        catalogBtn.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, CategoryActivity.class)));
    }
}