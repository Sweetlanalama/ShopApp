package com.example.shopapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopapp.R;
import com.example.shopapp.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder>{

    Context context;
    List<MyCartModel> cartModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList =cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(cartModelList.get(position).getProductImg()).into(holder.cartImg);
        holder.name.setText(cartModelList.get(position).getProductName());
        holder.price.setText(cartModelList.get(position).getProductPrice());
        holder.quantity.setText(cartModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(cartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    cartModelList.remove(cartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Позиция удалена", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Ошибка"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        });

            }

        });

    }

    @Override
    public int getItemCount() {
       return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, quantity, totalPrice;
        ImageView cartImg, deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.quantity_cart);
            totalPrice = itemView.findViewById(R.id.total_price);
            cartImg = itemView.findViewById(R.id.product_img);
            deleteItem = itemView.findViewById(R.id.delete);
        }
    }
}
