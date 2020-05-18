package com.example.livrokotlin.listadecompras

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*


class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {

            //pegando os valores digitados pelo usuário

            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()

            //verificando se o usuário digitou algum valor

            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                //enviando item para lista
                val prod = Produto(produto, qtd.toInt(), valor.toDouble())

                produtosGlobal.add(prod)

                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            } else {
                txt_produto.error = if (txt_produto.text.isEmpty()) "Preencha o nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null
            }

        }
    }
}

