package com.example.appguitarworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {

    public static void Inserir(Context context, Equipamento equipamento){
        ContentValues values = new ContentValues();
        values.put("nome",equipamento.getNome());
        values.put("marca",equipamento.getMarca());
        values.put("valor",equipamento.getValor());
        values.put("categoria",equipamento.getCategoria());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.insert("equipamentos",null,values);
    }

    public static void Editar(Context context, Equipamento equipamento){
        ContentValues values = new ContentValues();
        values.put("nome",equipamento.getNome());
        values.put("marca",equipamento.getMarca());
        values.put("valor",equipamento.getValor());
        values.put("categoria",equipamento.getCategoria());

        //Conexão com o banco
        Banco conn = new Banco(context);

        //Buscando permissão de escrita
        SQLiteDatabase db = conn.getWritableDatabase();

        //Update na tabela dando um where pelo id do equipamento
        db.update("equipamentos",values,"id = " + equipamento.getId(),null);
    }

    public static void Excluir(Context context, int idEquipamento){
        //Conexão com o banco
        Banco conn = new Banco(context);

        //Buscando permissão de escrita
        SQLiteDatabase db = conn.getWritableDatabase();

        //deletando
        db.delete("equipamentos","id = " + idEquipamento,null);
    }

    public static List<Equipamento> BuscaEquipamentos(Context context){
        List<Equipamento> lista = new ArrayList<>();

        //Conexão com o banco
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM equipamentos ORDER BY nome",null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Equipamento equipamento = new Equipamento();
                //Instanciado a classe produto e buscando os objetos com as informações do banco
                equipamento.setId(cursor.getInt(0));
                equipamento.setNome(cursor.getString(1));
                equipamento.setMarca(cursor.getString(2));
                equipamento.setValor(cursor.getString(3));
                equipamento.setCategoria(cursor.getString(4));

                lista.add(equipamento);//Adicionando na lista
            }while (cursor.moveToNext()); //enquanto existir um próximo, o laço vai repetir
        }

        return lista;
    }

    //Retornar apenas um produto
    public static Equipamento  BuscaApenasUmEquipamento(Context context, int idEquipamento){

        //Conexão com o banco
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        //Navegar posição por posição no cursor
        Cursor cursor = db.rawQuery("SELECT * FROM equipamentos where id = "+ idEquipamento,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            Equipamento equipamento = new Equipamento();
            //Instanciado a classe produto e buscando os objetos com as informações do banco
            equipamento.setId(cursor.getInt(0));
            equipamento.setNome(cursor.getString(1));
            equipamento.setMarca(cursor.getString(2));
            equipamento.setValor(cursor.getString(3));
            equipamento.setCategoria(cursor.getString(4));

            return  equipamento;
        }else{
            return null;
        }
    }


}
