package com.example.adondeapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Switch {

    private final FragmentManager fragmentManager;

    public Switch(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
