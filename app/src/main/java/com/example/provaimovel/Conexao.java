package com.example.provaimovel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao (Context context) {
        super (context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table imovel(id integer primary key autoincrement, " +
                "bairro varchar(50), nome varchar(50), tamanho varchar(50), quartos varchar(50), " +
                "suites varchar(50), banheiros varchar(50), vagas varchar(50))"  );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
