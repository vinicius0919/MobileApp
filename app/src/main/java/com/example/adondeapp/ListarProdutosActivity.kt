package com.example.adondeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ListarProdutosActivity : AppCompatActivity(), ProductActionListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var produtosAdapter: ProdutosAdapter
    private var auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exibirprodutos)
        val parametro = intent.getBooleanExtra("All", false)


        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        produtosAdapter = ProdutosAdapter(this)
        recyclerView.adapter = produtosAdapter


        val listarProdButton: Button = findViewById(R.id.listarProdutos)

        listarProdButton.setOnClickListener {
            if(parametro){
                getAllProducts()
            }else{
                getMyProducts()
            }
        }
    }

    fun getMyProducts(){
        val db = FirebaseFirestore.getInstance()
        val firebaseUser = auth.currentUser
        val userId: String? = firebaseUser?.uid

        // Realiza a leitura dos dados da coleção "PRODUTOS"
        db.collection("PRODUTOS")
            .whereEqualTo("IDUSER", userId)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot? ->
                val produtosList = mutableListOf<Produto>()

                // Itera sobre os documentos da coleção
                for (document: DocumentSnapshot in querySnapshot!!.documents) {
                    // Obtém os dados do documento e adiciona à lista de produtos
                    val idProduct = document.id
                    val nome = document.getString("NOME")
                    val preco = document.getString("PRECO")
                    val descricao = document.getString("DESCRICAO")
                    val fotoproduto = document.getString("FOTOPRODUTO")

                    if (nome != null && preco != null && descricao != null) {
                        val produto = Produto(idProduct, nome, preco, descricao, fotoproduto)
                        produtosList.add(produto)
                    }
                }

                // Atualiza o adapter com a lista de produtos
                produtosAdapter.setProdutos(produtosList)
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    baseContext,
                    "Erro ao listar os produtos!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun getAllProducts(){
        val db = FirebaseFirestore.getInstance()

        // Realiza a leitura dos dados da coleção "PRODUTOS"
        db.collection("PRODUTOS")
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot? ->
                val produtosList = mutableListOf<Produto>()

                // Itera sobre os documentos da coleção
                for (document: DocumentSnapshot in querySnapshot!!.documents) {
                    // Obtém os dados do documento e adiciona à lista de produtos
                    val idProduct = document.id
                    val nome = document.getString("NOME")
                    val preco = document.getString("PRECO")
                    val descricao = document.getString("DESCRICAO")
                    val fotoproduto = document.getString("FOTOPRODUTO")

                    if (nome != null && preco != null && descricao != null) {
                        val produto = Produto(idProduct, nome, preco, descricao, fotoproduto)
                        produtosList.add(produto)
                    }
                }

                // Atualiza o adapter com a lista de produtos
                produtosAdapter.setProdutos(produtosList)
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    baseContext,
                    "Erro ao listar os produtos!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
    override fun onResume() {
        super.onResume()
        // Atualiza o RecyclerView quando a atividade é retomada
        produtosAdapter.notifyDataSetChanged()
    }
    fun switchLogin(view: View?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}
interface ProductActionListener {
    fun getAllProducts()
}
