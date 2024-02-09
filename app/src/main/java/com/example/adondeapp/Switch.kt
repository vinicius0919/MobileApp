package com.example.adondeapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Switch(private val fragmentManager: FragmentManager) {
    fun changeFragment(fragment: Fragment?) {
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_content, fragment!!)
        ft.addToBackStack(null)
        ft.commit()
    }
}
