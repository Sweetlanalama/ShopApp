package com.example.shopapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopapp.Activity.DetailActivity;
import com.example.shopapp.R;
import com.example.shopapp.models.ViewAllModel;

import java.util.List;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.Viewholder> {

    Context context;
    List<ViewAllModel> viewAllModelList;

    public PopularListAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pop_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(viewAllModelList.get(position).getName());
        holder.price.setText(String.valueOf(viewAllModelList.get(position).getPrice()));
        holder.score.setText(viewAllModelList.get(position).getScore());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", viewAllModelList.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name, price, score;
        ImageView popImg;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.pop_name);
            price=itemView.findViewById(R.id.pop_price);
            score=itemView.findViewById(R.id.pop_score);
            popImg=itemView.findViewById(R.id.pop_img);
        }
    }
}
