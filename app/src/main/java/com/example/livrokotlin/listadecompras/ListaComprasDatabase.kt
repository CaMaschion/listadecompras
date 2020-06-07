package com.example.livrokotlin.listadecompras

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*



//função de extensão para acesso à instância
val Context.database: ListaComprasDatabase
    get() = ListaComprasDatabase.getInstance(getApplicationContext())

class ListaComprasDatabase(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context, name = "ListaCompras.db", version = 1) {


    //Padrão Singleton
    //companion objetc é usado quando queremos definir uma função ou um objeto estático na classe
    companion object{
        private var instance: ListaComprasDatabase? = null

        @Synchronized
        //protege o método contra acesso concorrente de múltiplas threads

        fun getInstance (ctx: Context) : ListaComprasDatabase {
            if (instance == null){
                instance = ListaComprasDatabase(ctx.getApplicationContext())
            }
            return instance!!

        }
    }

    //onCreate = responsável pela criação das tabelas no banco de dados.

    override fun onCreate(db: SQLiteDatabase) {

        //criação das tabelas

        db.createTable(
            "produtos", true, "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "quantidade" to INTEGER,
            "valor" to REAL,
            "foto" to BLOB
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //executado somente quando há uma alteração na versão do banco de dados
        //variaveis old e new guardam a versao do db

    }

}

