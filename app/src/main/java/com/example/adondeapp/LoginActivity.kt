package com.example.adondeapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class LoginActivity : AppCompatActivity() {
    private val context: Context = this
    var switcher = Switch(supportFragmentManager)
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        val btnMenu = findViewById<Button>(R.id.voltarMenu)
        val btnLogar = findViewById<Button>(R.id.logar01)
        btnLogar.setOnClickListener { v: View? -> clickListener() }
        val minhafoto = findViewById<View>(R.id.minhafotodeperfil) as ImageView
        if (PreferencesManager.getUsername(context) != null) {
            val black = Color.parseColor("#000000")
            btnMenu.isClickable = true
            btnMenu.setBackgroundColor(black)
            btnMenu.visibility = View.VISIBLE
            btnMenu.setOnClickListener { v: View? ->
                val intent = Intent(context, MenuActivity::class.java)
                startActivity(intent)
            }
        }

    }

    fun switchCadastro(view: View?) {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun clickListener() {
        val el = findViewById<EditText>(R.id.textInput)
        val el2 = findViewById<EditText>(R.id.passwordInput)
        val email = el.text.toString().trim { it <= ' ' }
        val password = el2.text.toString().trim { it <= ' ' }
        val alertUsr = findViewById<TextView>(R.id.alertaUsrname)
        val alertPass = findViewById<TextView>(R.id.alertaPassword)

        if (!TextUtils.isEmpty(email)) {
            if (!TextUtils.isEmpty(password)) {
                alertPass.visibility = View.INVISIBLE
                alertUsr.visibility = View.INVISIBLE

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
                            // Sign in success, update UI with the signed-in user's information
                            PreferencesManager.setUserLoggedIn(context, true)
                            PreferencesManager.setUserId(context, currentFirebaseUser!!.uid)
                            PreferencesManager.setUsername(context, email)

                            val intent = Intent(this, MenuActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(
                                baseContext,
                                "Preencha todos os campos!",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }


            } else {
                alertPass.visibility = View.VISIBLE
            }
        } else {
            alertUsr.visibility = View.VISIBLE
        }


    }


}