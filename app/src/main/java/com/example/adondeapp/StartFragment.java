package com.example.adondeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StartFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.start_fragment, container, false);

        TextView tv = view.findViewById(R.id.textMessageStart);

        Context context = requireContext();
        String username =  PreferencesManager.getUsername(context);

        tv.setText("Bem-vindo");



        return view;
    }


}
