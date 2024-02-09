package com.example.adondeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuActivity : AppCompatActivity() {
    var context: Context = this
    var fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        val linearLayout1 = findViewById<View>(R.id.pesquisarProduto) as TextView
        val linearLayout2 = findViewById<View>(R.id.pesquisarProduto2) as TextView
        val linearLayout3 = findViewById<View>(R.id.pesquisarProduto3) as TextView
        val linearLayout4 = findViewById<View>(R.id.pesquisarProduto4) as TextView
        val linearLayout7 = findViewById<View>(R.id.pesquisarProduto7) as TextView
        val linearLayout8 = findViewById<View>(R.id.pesquisarProduto8) as TextView
        linearLayout1.setOnClickListener { v: View? -> changeToMapsActvity() }
        linearLayout2.setOnClickListener { v: View? -> changeToCadastroProdutoActvity() }
        linearLayout3.setOnClickListener { v: View? -> changeToListarActvity(true) }
        linearLayout4.setOnClickListener { v: View? -> changeToListarActvity(false) }
        linearLayout7.setOnClickListener { v: View? -> changeToPerfilFragment() }
        linearLayout8.setOnClickListener { v: View? ->
            Firebase.auth.signOut()
            PreferencesManager.setUserLoggedIn(context, false)
            PreferencesManager.setUsername(context, null)
            PreferencesManager.setUserId(context, null)
            PreferencesManager.setUserPhotoDelete(context)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun changeToMapsActvity() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    fun changeToCadastroProdutoActvity() {
        val intent = Intent(this, CadastroProdutosActivity()::class.java)
        startActivity(intent)
    }

    fun changeToListarActvity(boo:Boolean) {
        val intent = Intent(this, ListarProdutosActivity::class.java)
        intent.putExtra("All", boo)
        startActivity(intent)
    }

    fun changeToLoginActvity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun changeToPerfilFragment() {
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.menutela, MyAccountFragment())
        ft.addToBackStack(null)
        ft.commit()
    }
}
