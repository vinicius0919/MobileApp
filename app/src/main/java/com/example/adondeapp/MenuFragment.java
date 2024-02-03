package com.example.adondeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.menu_fragment, container, false);

        Context context = requireContext();
        TextView tv = (TextView) view.findViewById(R.id.textViewUsername);
        if(PreferencesManager.getUsername(context)!=null){
            tv.setText("Bem-vindo, "+ PreferencesManager.getUsername(context));
        }


        LinearLayout linearLayout1 =(LinearLayout) view.findViewById(R.id.opcao1);
        LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.opcao5);
        Switch switcher = new Switch(getActivity().getSupportFragmentManager());



        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.changeFragment(new MapsFragment());
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
