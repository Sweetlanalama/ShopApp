package com.example.shopapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shopapp.R;
import com.example.shopapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    Button addToCart;
    TextView name,description,score, price;
    ImageView picItem,backBtn;
    ImageView addItem, removeItem;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        }


        quantity = findViewById(R.id.quantity);
        price = findViewById(R.id.priceTxt);
        name = findViewById(R.id.pop_name);
        description = findViewById(R.id.descriptionTxt);
        picItem = findViewById(R.id.itemPic);
        score = findViewById(R.id.pop_score);
        backBtn = findViewById(R.id.backBtn);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        if (viewAllModel !=null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(picItem);
            score.setText(viewAllModel.getScore());
            description.setText(viewAllModel.getDescription());
            name.setText(viewAllModel.getName());
            price.setText(String.valueOf(viewAllModel.getPrice()));
            totalPrice = viewAllModel.getPrice() * totalQuantity;
        }


        backBtn.setOnClickListener(view -> finish());
        addToCart = findViewById(R.id.add_to_cart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }


            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuantity > 0) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });

    }

    private void addedToCart() {

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productImg", viewAllModel.getImg_url());
        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);



        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailActivity.this, "Добавлено в корзину", Toast.LENGTH_SHORT).show();
                        finish();


                    }
                });
    }

}