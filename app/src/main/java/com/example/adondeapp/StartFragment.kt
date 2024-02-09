package com.example.adondeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class StartFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.start_fragment, container, false)
        val tv = view.findViewById<TextView>(R.id.textMessageStart)
        val context = requireContext()
        val username = PreferencesManager.getUsername(context)
        tv.text = "Bem-vindo"
        return view
    }
}
