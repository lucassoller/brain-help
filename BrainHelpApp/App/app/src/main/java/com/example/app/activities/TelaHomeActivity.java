package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.services.DiagnosticadoService;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;

public class TelaHomeActivity extends AppCompatActivity {

    private ImageView ivContatos;
    private ImageView ivJogos;
    private ImageView ivChatbot;
    private ImageView ivTarefas;
    private ImageView ivMusicas;
    private ImageView ivFotos;
    private ImageView ivMedicamentos;
    private ImageView ivEnderecos;
    private Diagnosticado diagnosticado;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent itTelaUsuario = new Intent(TelaHomeActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaHomeActivity.this, TelaAlterarSenhaActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaHomeActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaHome = new Intent(TelaHomeActivity.this, TelaInicialActivity.class);
                itTelaHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaHome);
                finish();
                return true;
        }
        return false;
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