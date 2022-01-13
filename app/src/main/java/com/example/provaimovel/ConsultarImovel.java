package com.example.provaimovel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ConsultarImovel extends AppCompatActivity {

    private ListView listView;
    private ImovelDAO dao;
    private List<Imovel> imoveis;
    private List<Imovel> imoveisFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_imovel);

        listView = findViewById(R.id.lista_imoveis);
        dao = new ImovelDAO(this);
        imoveis = dao.obterTodos();
        imoveisFiltrados.addAll(imoveis);
        ArrayAdapter<Imovel> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imoveisFiltrados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_consultar, menu);

        SearchView sv =(SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraImovel(s);
                return false;
            }
        });
        return true;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater c = getMenuInflater();
        c.inflate(R.menu.menu_contexto, menu);

    }

    public void procuraImovel (String nome) {
        imoveisFiltrados.clear();
        for(Imovel i : imoveis){
            if(i.getNome().toLowerCase().contains(nome.toLowerCase())){
                imoveisFiltrados.add(i);
            }
        }
        listView.invalidateViews();
    }

    public void deletar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Imovel imovelExcluir = imoveisFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Tem certeza que deseja excluir o imóvel?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imoveisFiltrados.remove(imovelExcluir);
                        imoveis.remove(imovelExcluir);
                        dao.deletar(imovelExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();


    }

    public void inicio(MenuItem item){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Imovel imovelAtualizar = imoveisFiltrados.get(menuInfo.position);
                Intent it = new Intent(this, CriarImovel.class);
                it.putExtra("imovel", imovelAtualizar);
                startActivity(it);

    }
}