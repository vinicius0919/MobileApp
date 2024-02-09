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


class CadastroProdutosActivity : AppCompatActivity() {
    var context: Context = this
    private var clickimageid: ImageView? = null
    private lateinit var fotoProduto: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastroprodutos_fragments)

        val db = Firebase.firestore

        clickimageid = findViewById(R.id.viewProduct)
        val photoImageView = clickimageid

        if (photoImageView != null) {
            photoImageView.setOnClickListener(View.OnClickListener { v: View? ->
                // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
                val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // Start the activity with camera_intent, and request pic id
                startActivityForResult(camera_intent, CadastroActivity.REQUEST_IMAGE_CAPTURE)
            })
        }

        val NomeProd: EditText = findViewById(R.id.nomeproduto)
        val PrecoProd: EditText = findViewById(R.id.precoproduto)
        val DescProd: EditText = findViewById(R.id.marcaproduto)

        val CadProdButton: Button = findViewById(R.id.cadastrarproduto)
        CadProdButton.setOnClickListener {
            if (this::fotoProduto.isInitialized) {
                val NomeProd = NomeProd.text.toString()
                val PrecoProd = PrecoProd.text.toString()
                val DescProd = DescProd.text.toString()
                val FotoProd = fotoProduto

                auth = FirebaseAuth.getInstance()
                val firebaseUser = auth.currentUser
                val userId: String? = firebaseUser?.uid

                val produto = hashMapOf<String, String>(
                    "IDUSER" to userId.toString(),
                    "NOME" to NomeProd,
                    "PRECO" to PrecoProd,
                    "DESCRICAO" to DescProd,
                    "FOTOPRODUTO" to FotoProd
                )
                if (userId != null) {
                    db.collection("PRODUTOS").add(produto)
                        .addOnSuccessListener {
                            Toast.makeText(
                                baseContext,
                                "Produto cadastrado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                baseContext,
                                "Erro no cadastro do produto!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }


            } else {
                Toast.makeText(
                    baseContext,
                    "Adicione uma foto do produto!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    fun switchMenu(view: View?) {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == CadastroActivity.REQUEST_IMAGE_CAPTURE) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            fotoProduto = PreferencesManager.encodeBitmapToString(photo)
            // Set the image in imageview for display
            clickimageid!!.setImageBitmap(photo)
            clickimageid!!.background = null
        }
    }

    private fun openCamera() {
        val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera_intent, CadastroActivity.REQUEST_IMAGE_CAPTURE)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 2
    }
}
