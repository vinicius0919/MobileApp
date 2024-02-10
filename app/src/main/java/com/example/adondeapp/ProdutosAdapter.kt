package com.example.adondeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ProdutosAdapter(private val productActionListener: ProductActionListener) : RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder>() {
    private var produtosList: List<Produto> = listOf()

    fun setProdutos(produtos: List<Produto>) {
        produtosList = produtos
        notifyDataSetChanged()
    }

    fun deleteProduct(produtoId: String?) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("PRODUTOS").document(produtoId ?: "")
        docRef.delete()
            .addOnSuccessListener {
                notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                // Falha ao excluir o produto
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtosList[position]
        holder.bind(produto)
    }

    override fun getItemCount(): Int {
        return produtosList.size
    }

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeTextView: TextView = itemView.findViewById(R.id.textNome)
        private val precoTextView: TextView = itemView.findViewById(R.id.textPreco)
        private val descricaoTextView: TextView = itemView.findViewById(R.id.textDescricao)
        private val fotoProdutoIMage: ImageView = itemView.findViewById(R.id.imageViewProd)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.imageButton5)

        fun bind(produto: Produto) {
            nomeTextView.text = "Nome: ${produto.nome}"
            precoTextView.text = "Preço: ${produto.preco}"
            descricaoTextView.text = "Descrição: ${produto.descricao}"
            try {
                fotoProdutoIMage.setImageBitmap(PreferencesManager.decodeStringToBitmap(produto.fotoproduto.toString()))
            } catch (e: Exception) {
                // Trate exceções de decodificação de bitmap aqui
            }

            // Configura o OnClickListener para o botão delete
            btnDelete.setOnClickListener {
                // Obtém o ID do produto a ser excluído
                val produtoId = produtosList[adapterPosition].id
                // Chama o método deleteProduct para excluir o produto do Firestore
                deleteProduct(produtoId)
                productActionListener.getAllProducts()
            }
        }
    }
}

data class Produto(
    val id: String,
    val nome: String,
    val preco: String,
    val descricao: String,
    val fotoproduto: String?
)
