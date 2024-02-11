package com.example.shopapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.shopapp.Activity.LoginActivity;
import com.example.shopapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    CircleImageView profileImg;
    Button buttonOut;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        profileImg = root.findViewById(R.id.profile_img);
        buttonOut = root.findViewById(R.id.bSignOut);


        buttonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignOut();
            }
        });


        return root;

    }

    private void onClickSignOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }


}

