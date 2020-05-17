package com.example.livrokotlin.listadecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val produtosAdapter = ProdutoAdapter(this)
        btn_adicionar.setOnClickListener {

            //criando a intent explícita
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_view_produtos.adapter as ProdutoAdapter
        adapter.addAll(produtosGlobal)
    }
}