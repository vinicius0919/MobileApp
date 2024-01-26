package com.example.adondeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        LinearLayout button = view.findViewById(R.id.opcao1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment3(v);
            }
        });

        return view;
    }


    public void changeFragment3(View view) {
        FragmentManager fm = getParentFragmentManager();

        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_content, new MapsFragment());
        ft.addToBackStack(null);

        ft.commit();
    }

}
