package com.example.livrokotlin.listadecompras

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //implementação do adapter
        val produtosAdapter = ProdutoAdapter(this)

        //definindo o adapter na lista
        list_view_produtos.adapter = produtosAdapter

        list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val opcoes = listOf("editar", "excluir")
            val opc_editar = 0
            val opc_excluir = 1

            selector("O que deseja fazer?", opcoes) { dialogInterface, position ->

                when (position) {

                    opc_editar -> {
                        alert("Editar").show()
                    }

                    opc_excluir -> {

                        //buscando o item clicado
                        val item = produtosAdapter.getItem(position)
                        produtosAdapter.remove(item)
                        if (item != null) {
                            deletarProduto(item.id)
                        }
                        toast("item deletado com sucesso")

                    }
                }
            }

            true
        }

        btn_adicionar.setOnClickListener {
            startActivity<CadastroActivity>()
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_view_produtos.adapter as? ProdutoAdapter

        database.use {
            //efetuando uma consulta no banco de dados
            select("produtos").exec {
                //criando o parser que montará o produto
                val parser = rowParser { id: Int, nome: String,
                                         quantidade: Int,
                                         valor: Double,
                                         foto: ByteArray? ->
                    //coluna do bando de dados

                    //montagem do objeto Produyto com as colunas do banco
                    Produto(id, nome, quantidade, valor, foto?.toBitmap())
                }
                //criando a lista de produtos com dados do banco
                var listaProdutos = parseList(parser)

                adapter?.clear()
                adapter?.addAll(listaProdutos)

                val soma = listaProdutos.sumByDouble { it.valor * it.quantidade }
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                txt_total.text = "TOTAL: ${f.format(soma)}"

            }
        }
    }

    fun deletarProduto(idProduto:Int){
            var deleteProduto = arrayOf(idProduto.toString())
        database.use{
            delete("produtos", "id", deleteProduto)
        }
    }
}
