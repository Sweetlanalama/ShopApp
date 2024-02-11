package com.example.shopapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.PlaceOrderActivity;
import com.example.shopapp.Adapter.MyCartAdapter;
import com.example.shopapp.R;
import com.example.shopapp.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView overTotalAmount;
    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModellist;
    Button buyNow;


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        overTotalAmount = root.findViewById(R.id.totalTxt);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        buyNow = root.findViewById(R.id.buy_now);

        cartModellist = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), cartModellist);
        recyclerView.setAdapter(cartAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                String documentId = documentSnapshot.getId();

                                MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);

                                myCartModel.setDocumentId(documentId);
                                cartModellist.add(myCartModel);
                                cartAdapter.notifyDataSetChanged();
                            }

                            calculateTotalAmount(cartModellist);
                        }
                    }
                });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
                intent.putExtra("itemList", (Serializable) cartModellist);
                startActivity(intent);
            }
        });

        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> cartModellist) {

        double totalAmount = 0.0;
        for (MyCartModel myCartModel : cartModellist) {
            totalAmount += myCartModel.getTotalPrice();
        }

        overTotalAmount.setText("₽"+totalAmount);
    }

}