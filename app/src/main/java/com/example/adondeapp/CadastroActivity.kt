package com.example.adondeapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class CadastroActivity : AppCompatActivity() {
    var context: Context = this
    private var clickimageid: ImageView? = null
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        clickimageid = findViewById(R.id.imageFoto)
        val photoImageView = clickimageid


        if (photoImageView != null) {
            photoImageView.setOnClickListener(View.OnClickListener { v: View? ->
                // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
                val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // Start the activity with camera_intent, and request pic id
                startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE)
            })
        }

        val emailText: EditText = findViewById(R.id.editTextEmailCadastro)
        val password: EditText = findViewById(R.id.editTextSenhaCadastro)

        val loginButton: Button = findViewById(R.id.buttonCadastrar)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {

            var email = emailText.text.toString()
            var password = password.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Usuário cadastrado com sucesso!",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val firebaseUser = auth.currentUser
                        val userId = firebaseUser?.uid
                        if (userId != null) {
                            setPerfilImage(
                                PreferencesManager.getUserPhotoString(context).toString(), userId
                            )
                        }

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)

                        // Finalize a LoginActivity se necessário
                        finish()
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Algo deu errado!",
                            Toast.LENGTH_SHORT,
                        ).show()
                        //updateUI(null)
                    }
                }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            PreferencesManager.setUserPhoto(context, photo)
            // Set the image in imageview for display
            clickimageid!!.setImageBitmap(photo)
            clickimageid!!.background = null
        }
    }

    private fun openCamera() {
        val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE)
    }

    fun switchLogin(view: View?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    fun setPerfilImage(fotoPerfil: String, idUser: String) {
        val perfil = hashMapOf<String, String>(
            "FOTOUSER" to fotoPerfil
        )

        db.collection("USERS").document(idUser).set(perfil)
            .addOnSuccessListener { documentReference: Void? ->
                Toast.makeText(
                    baseContext,
                    "Usuário cadastrado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    baseContext,
                    "Foto de perfil não encontrada!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
