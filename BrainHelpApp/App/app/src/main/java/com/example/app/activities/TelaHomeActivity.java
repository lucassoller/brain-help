package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;

public class TelaHomeActivity extends AppCompatActivity {

    private ImageView ivContatos;
    private ImageView ivJogos;
    private ImageView ivChatbot;
    private ImageView ivTarefas;
    private ImageView ivMusicas;
    private ImageView ivFotos;
    private ImageView ivMedicamentos;
    private ImageView ivEnderecos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);
        this.inicializaComponenetes();

        this.ivContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaContatos = new Intent(TelaHomeActivity.this, TelaContatosActivity.class);
                startActivity(itTelaContatos);
            }
        });

        this.ivJogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaJogos = new Intent(TelaHomeActivity.this, TelaJogosActivity.class);
                startActivity(itTelaJogos);
            }
        });

        this.ivChatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaChatbot = new Intent(TelaHomeActivity.this, TelaChatbotActivity.class);
                startActivity(itTelaChatbot);
            }
        });

        this.ivTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaTarefas = new Intent(TelaHomeActivity.this, TelaTarefasActivity.class);
                startActivity(itTelaTarefas);
            }
        });

        this.ivMusicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaMusicas = new Intent(TelaHomeActivity.this, TelaMusicasActivity.class);
                startActivity(itTelaMusicas);
            }
        });

        this.ivFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaFotos = new Intent(TelaHomeActivity.this, TelaFotosActivity.class);
                startActivity(itTelaFotos);
            }
        });

        this.ivMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaMedicamentos = new Intent(TelaHomeActivity.this, TelaMedicamentosActivity.class);
                startActivity(itTelaMedicamentos);
            }
        });

        this.ivEnderecos.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                Intent itTelaEnderecos = new Intent(TelaHomeActivity.this, TelaEnderecosActivity.class);
                startActivity(itTelaEnderecos);
            }
        });
    }

    private void inicializaComponenetes(){
        this.ivContatos = findViewById(R.id.iv_contatos);
        this.ivJogos = findViewById(R.id.iv_jogos);
        this.ivChatbot = findViewById(R.id.iv_chatbot);
        this.ivTarefas = findViewById(R.id.iv_tarefas);
        this.ivMusicas = findViewById(R.id.iv_musicas);
        this.ivFotos = findViewById(R.id.iv_fotos);
        this.ivMedicamentos = findViewById(R.id.iv_medicamentos);
        this.ivEnderecos = findViewById(R.id.iv_enderecos);
    }
}
