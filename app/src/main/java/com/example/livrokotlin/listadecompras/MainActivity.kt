package com.example.livrokotlin.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //implementando o adapter
        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        //linkando o adapter ao listview
        list_view_produtos.adapter = produtosAdapter

        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()

            if (produto.isNotEmpty()) {
                produtosAdapter.add(produto)
                txt_produto.text.clear()
            } else {
                txt_produto.error = "Preencha um valor"
            }

            list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>?, view: View?, position: Int, id: Long ->
                val item = produtosAdapter.getItem(position)
                produtosAdapter.remove(item)
                true
            }
        }
    }
}