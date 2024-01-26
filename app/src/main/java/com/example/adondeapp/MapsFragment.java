package com.example.adondeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MapsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        Button button = view.findViewById(R.id.butaoVoltar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar(v);
            }
        });


        return view;

    }

    public void voltar(View view){
        FragmentManager fm = getParentFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_content, new MenuFragment());

        ft.commit();

    }


}
