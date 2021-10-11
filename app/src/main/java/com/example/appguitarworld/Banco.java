package com.example.appguitarworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class Banco extends SQLiteOpenHelper {
    private  static final String NOME_BANCO = "dbAppGuitarWorld"; //Banco de Dados
    private static final int VERSAO = 1;

    public Banco(Context context){ super(context, NOME_BANCO,null,VERSAO); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Estrutura do banco de dados
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS equipamentos ( " +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "marca TEXT NOT NULL, " +
                "valor double not null," +
                "categoria TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Se mudar a versão do aplicativo, atualizar a versão do banco
    }

}
