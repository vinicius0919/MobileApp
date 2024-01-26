package com.example.adondeapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;  // Importe a classe Fragment correta
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }
}
