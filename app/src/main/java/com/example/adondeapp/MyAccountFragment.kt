package com.example.adondeapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.adondeapp.PreferencesManager.encodeBitmapToString
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MyAccountFragment : Fragment() {
    private lateinit var userId: String
    private lateinit var minhafoto: ImageView
    var db = Firebase.firestore

    // Contexto a ser inicializado no método onAttach()
    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_account_fragment, container, false)
        minhafoto = view.findViewById<View>(R.id.minhafotoconta) as ImageView
        if (this::minhafoto.isInitialized) {
            loadUserProfileImage()
        }
        val edtText1 = view.findViewById<EditText>(R.id.minhacontaemail)
        edtText1.setText(PreferencesManager.getUsername(context))
        val btnVoltarConta = view.findViewById<View>(R.id.buttonvoltarconta) as Button
        btnVoltarConta.setOnClickListener { v: View? ->
            val fragmentManager = parentFragmentManager
            val ft = fragmentManager.beginTransaction()
            ft.remove(this)
            ft.commit()
        }

        minhafoto.setOnClickListener { v: View? ->
// Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, CadastroActivity.REQUEST_IMAGE_CAPTURE)

        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == CadastroActivity.REQUEST_IMAGE_CAPTURE) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data?.extras?.get("data") as? Bitmap

            if (photo != null && this::minhafoto.isInitialized) {
                val previousPhotoString = PreferencesManager.getUserPhotoString(context)

                // Verifica se há uma alteração na foto do perfil
                if (previousPhotoString.isNullOrEmpty() || previousPhotoString != encodeBitmapToString(
                        photo
                    )
                ) {
                    PreferencesManager.setUserPhoto(context, photo)
                    // Set the image in imageview for display
                    setPerfilImage(
                        PreferencesManager.getUserPhotoString(context).toString(),
                        PreferencesManager.getUserId(context).toString()
                    )
                }

                minhafoto.setImageBitmap(photo)
                minhafoto.background = null
            }
        }
    }

    fun loadUserProfileImage() {
        // Certifique-se de que o UID do usuário esteja disponível
        userId = PreferencesManager.getUserId(requireContext()) ?: return

        val db = Firebase.firestore
        val docRef = db.collection("USERS").document(userId)

        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val fotoPerfil = documentSnapshot.getString("FOTOUSER")
                    if (fotoPerfil != null && fotoPerfil.isNotEmpty()) {
                        PreferencesManager.setUserPhotoString(context, fotoPerfil)
                        minhafoto.setImageBitmap(PreferencesManager.getUserPhoto(context))
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    requireContext(),
                    "Erro ao carregar imagem de perfil",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun setPerfilImage(fotoPerfil: String, idUser: String) {
        val perfil = hashMapOf<String, String>(
            "FOTOUSER" to fotoPerfil
        )

        db.collection("USERS").document(idUser).set(perfil)
            .addOnSuccessListener { documentReference: Void? ->
                Toast.makeText(
                    context,
                    "Usuário cadastrado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Foto de perfil não encontrada!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
