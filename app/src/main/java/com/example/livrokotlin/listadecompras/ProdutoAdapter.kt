package com.example.livrokotlin.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class ProdutoAdapter(contexto: Context) : ArrayAdapter<Produto>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val v: View //carrega o layout do item que será mostrado na lista

        //caso a convertview ñ seja nula, ñ precisa inflar o layout, pois já está inflado dentro dela
        if (convertView != null) {
            v = convertView
        } else {
            //inflar o layout
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false) //true ou false - indica se o layout será anexado a viewgroup (loc 3208)
        }

        val item = getItem(position)

        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.txt_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        txt_qtd.text = item?.quantidade.toString()
        txt_produto.text = item?.nome.toString()
        txt_valor.text = f.format(item?.valor)

        if (item != null) {
                img_produto.setImageBitmap(item.foto)
            }

        return v
    }
}

//Comentários para estudo

/* position: posição do item que está prestes a ser montado na lista
 convertView: a View que foi usada no último item para ser reutilizada. Anter de
 utilizar essa variável devemos sempre verificar se ela não está nula, pois se for o
 primeiro item a ser criado, estará nula e não será possível reutilizá-la
 parent: uma view par à qual eventualmente a view principal está atrelada*/

/*CONVERTVIEW - this parameter is used strictly to increase the performance of your Adapter.
When a ListView uses an Adapter to fill its rows with Views, the adapter populates each
list item with a View object by calling getView() on each row. The Adapter uses the convertView as
a way of recycling old View objects that are no longer being used. In this way, the ListView can
send the Adapter old, "recycled" view objects that are no longer being displayed instead of instantiating
an entirely new object each time the Adapter wants to display a new list item.
This is the purpose of the convertView parameter.*/



