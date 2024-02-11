package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;

public class CategoryActivity extends AppCompatActivity {

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        bottom_navigation();
        category1();
        category2();
        category3();
        category4();
        category5();
        category6();
        category7();
        category8();
        category9();
        category10();
    }

    private void category10() {
        LinearLayout router = findViewById(R.id.router);

        router.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "router");
                startActivity(intent);
            }
        });
    }

    private void category9() {
        LinearLayout scanner = findViewById(R.id.scanner);

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "scanner");
                startActivity(intent);
            }
        });
    }

    private void category8() {
        LinearLayout projector = findViewById(R.id.projector);

        projector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "projector");
                startActivity(intent);
            }
        });
    }

    private void category7() {
        LinearLayout mouse = findViewById(R.id.mouse);

        mouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "mouse");
                startActivity(intent);
            }
        });
    }

    private void category6() {
        LinearLayout keyboard = findViewById(R.id.keyboard);

        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "keyboard");
                startActivity(intent);
            }
        });
    }

    private void category5() {
        LinearLayout printer = findViewById(R.id.printer);

        printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "printer");
                startActivity(intent);
            }
        });
    }

    private void category4() {
        LinearLayout console = findViewById(R.id.console);

        console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
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
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
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
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
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
                Intent intent = new Intent(new Intent(CategoryActivity.this, ViewAllActivity.class));
                intent.putExtra("type", "PC");
                startActivity(intent);
            }

        });
    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout catalogBtn = findViewById(R.id.catalogBtn);

        homeBtn.setOnClickListener(view -> startActivity(new Intent(CategoryActivity.this,MainActivity.class)));
        cartBtn.setOnClickListener(view -> startActivity(new Intent(CategoryActivity.this,CartActivity.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(CategoryActivity.this,ProfileActivity.class)));
        catalogBtn.setOnClickListener(view -> startActivity(new Intent(CategoryActivity.this, CategoryActivity.class)));
    }

}