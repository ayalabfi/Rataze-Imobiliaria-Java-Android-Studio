package com.example.provaimovel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CriarImovel extends AppCompatActivity {

    private EditText bairro;
    private EditText nome;
    private EditText tamanho;
    private EditText quartos;
    private EditText suites;
    private EditText banheiros;
    private EditText vagas;
    private ImovelDAO dao;
    private Imovel imovel = null;



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.criar_imovel);

        bairro = findViewById(R.id.editBairro);
        nome = findViewById(R.id.editNome);
        tamanho = findViewById(R.id.editTamanho);
        quartos = findViewById(R.id.editQuartos);
        suites = findViewById(R.id.editSuites);
        banheiros = findViewById(R.id.editBanheiros);
        vagas = findViewById(R.id.editVagas);
        dao = new ImovelDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("imovel")) {
            imovel = (Imovel) it.getSerializableExtra("imovel");
            bairro.setText (imovel.getBairro());
            nome.setText(imovel.getNome());
            tamanho.setText(imovel.getTamanho());
            quartos.setText(imovel.getQuartos());
            suites.setText(imovel.getSuites());
            banheiros.setText(imovel.getBanheiros());
            vagas.setText(imovel.getVagas());


        }
    }

    public void salvar (View view) {
        if (imovel == null) {
            imovel = new Imovel();
            imovel.setBairro(bairro.getText().toString());
            imovel.setNome(nome.getText().toString());
            imovel.setTamanho(tamanho.getText().toString());
            imovel.setQuartos(quartos.getText().toString());
            imovel.setSuites(suites.getText().toString());
            imovel.setBanheiros(banheiros.getText().toString());
            imovel.setVagas(vagas.getText().toString());
            long id = dao.inserir(imovel);
            Toast.makeText(this, "O ID do Imóvel é: " + id, Toast.LENGTH_SHORT).show();
        }else {
            imovel.setBairro(bairro.getText().toString());
            imovel.setNome(nome.getText().toString());
            imovel.setTamanho(tamanho.getText().toString());
            imovel.setQuartos(quartos.getText().toString());
            imovel.setSuites(suites.getText().toString());
            imovel.setBanheiros(banheiros.getText().toString());
            imovel.setVagas(vagas.getText().toString());
            dao.atualizar(imovel);
            Toast.makeText(this, "O Imovel foi atualizado", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_principal, menu);
        return true;

    }

    public void inicio(MenuItem item) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }

}


