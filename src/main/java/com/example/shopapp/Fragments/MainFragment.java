package com.example.shopapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Adapter.PopularListAdapter;
import com.example.shopapp.R;
import com.example.shopapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    RecyclerView popularRec;
    FirebaseFirestore db;

    List<ViewAllModel> viewAllModelList;
    PopularListAdapter popularListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_main, container, false);

        db = FirebaseFirestore.getInstance();
        popularRec = root.findViewById(R.id.pop_rec);

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        viewAllModelList = new ArrayList<>();
        popularListAdapter = new PopularListAdapter(getActivity(), viewAllModelList);
        popularRec.setAdapter(popularListAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);
                                viewAllModelList.add(viewAllModel);
                                popularListAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Oшибка"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}