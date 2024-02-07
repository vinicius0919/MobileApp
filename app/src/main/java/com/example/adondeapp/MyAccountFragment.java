package com.example.adondeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyAccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_account_fragment, container, false);

        ImageView minhafoto = (ImageView) view.findViewById(R.id.minhafotoconta);
        if(PreferencesManager.getUserPhoto(getContext())!=null){
            minhafoto.setImageBitmap(PreferencesManager.getUserPhoto(getContext()));
        }
        EditText edtText1 = view.findViewById(R.id.minhacontaemail);
        edtText1.setText(PreferencesManager.getUsername(getContext()));

        Button btnVoltarConta = (Button) view.findViewById(R.id.buttonvoltarconta);
        btnVoltarConta.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(this);
            ft.commit();
        });


        return view;
    }
}
