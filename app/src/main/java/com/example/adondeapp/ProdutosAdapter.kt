package com.example.adondeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdutosAdapter : RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder>() {
    private var produtosList: List<Produto> = listOf()

    fun setProdutos(produtos: List<Produto>) {
        produtosList = produtos
        notifyDataSetChanged()
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

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeTextView: TextView = itemView.findViewById(R.id.textNome)
        private val precoTextView: TextView = itemView.findViewById(R.id.textPreco)
        private val descricaoTextView: TextView = itemView.findViewById(R.id.textDescricao)
        private val fotoProdutoIMage: ImageView = itemView.findViewById(R.id.imageViewProd)

        fun bind(produto: Produto) {
            nomeTextView.text = "Nome: ${produto.nome}"
            precoTextView.text = "Preço: ${produto.preco}"
            descricaoTextView.text = "Descrição: ${produto.descricao}"
            try {
                fotoProdutoIMage.setImageBitmap(PreferencesManager.decodeStringToBitmap(produto.fotoproduto.toString()))
            } catch (e: Exception) {

            }


        }
    }
}

data class Produto(
    val nome: String,
    val preco: String,
    val descricao: String,
    val fotoproduto: String?
)
