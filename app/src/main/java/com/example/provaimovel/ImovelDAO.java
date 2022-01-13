package com.example.provaimovel;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ImovelDAO (Context context){
        conexao = new Conexao(context);
        banco =conexao.getWritableDatabase();
    }

    public long inserir (Imovel imovel){
        ContentValues values = new ContentValues();
        values.put("bairro", imovel.getBairro());
        values.put("nome", imovel.getNome());
        values.put("tamanho", imovel.getTamanho());
        values.put("quartos", imovel.getQuartos());
        values.put("suites", imovel.getSuites());
        values.put("banheiros", imovel.getBanheiros());
        values.put("vagas", imovel.getVagas());
        return banco.insert("imovel", null, values);

    }

    public List<Imovel>obterTodos(){
        List<Imovel> imoveis = new ArrayList<>();
        Cursor cursor = banco.query("imovel", new String[]{"id", "bairro", "nome", "tamanho"
        , "quartos", "suites", "banheiros", "vagas"}, null, null, null,
                null, null);
        while (cursor.moveToNext()){
            Imovel i = new Imovel();
            i.setId(cursor.getInt(0));
            i.setBairro(cursor.getString(1));
            i.setNome(cursor.getString(2));
            i.setTamanho(cursor.getString(3));
            i.setQuartos(cursor.getString(4));
            i.setSuites(cursor.getString(5));
            i.setBanheiros(cursor.getString(6));
            i.setVagas(cursor.getString(7));
            imoveis.add(i);
        }
        return imoveis;
    }

    public void deletar (Imovel i){
        banco.delete("imovel", "id =?", new String[]{i.getId().toString()});
    }
    public void atualizar (Imovel imovel){
        ContentValues values = new ContentValues();
        values.put("bairro", imovel.getBairro());
        values.put("nome", imovel.getNome());
        values.put("tamanho", imovel.getTamanho());
        values.put("quartos", imovel.getQuartos());
        values.put("suites", imovel.getSuites());
        values.put("banheiros", imovel.getBanheiros());
        values.put("vagas", imovel.getVagas());
        banco.update("imovel", values, "id = ?", new String[]{imovel.getId().toString()});
    }

}