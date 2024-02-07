package com.example.adondeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_maps, container, false);

        FloatingActionButton btn = view.findViewById(R.id.floatingActionButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void changeToMenuActvity() {
        Intent intent = new Intent(getContext(), MenuActivity.class);
        startActivity(intent);
    }



}
