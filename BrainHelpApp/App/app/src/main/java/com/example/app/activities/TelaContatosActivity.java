package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.adapter.ContatosAdapter;
import com.example.app.classes.Diagnosticado;
import com.example.app.classes.Endereco;
import com.example.app.classes.Vinculo;
import com.example.app.enumerated.Sexo;
import com.example.app.services.VinculoService;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaContatosActivity extends AppCompatActivity {

    private ListView lvContatos;
    private Button btAdicionarContato;
    private List<Vinculo> vinculos;
    public static ContatosAdapter contatosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contatos);
        this.inicializaComponentes();

        this.lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Vinculo vinculo = vinculos.get(i);
                Intent itTelaContatoCard = new Intent(TelaContatosActivity.this, TelaContatoCardActivity.class);
                itTelaContatoCard.putExtra("vinculo", vinculo);
                startActivity(itTelaContatoCard);
            }
        });

        this.btAdicionarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaContatoCard = new Intent(TelaContatosActivity.this, TelaContatoCardActivity.class);
                startActivity(itTelaContatoCard);
            }
        });
    }

    private void inicializaComponentes(){
        String diagnosticadoJson = TelaInicialActivity.sp.getString("diagnosticado" , null);
        Diagnosticado diagnosticado = new Gson().fromJson(diagnosticadoJson, Diagnosticado.class);
        vinculos = diagnosticado.getVinculos();

        this.lvContatos = findViewById(R.id.lv_contatos);
        this.btAdicionarContato = findViewById(R.id.bt_adicionar_contato);
        this.contatosAdapter = new ContatosAdapter(this, R.layout.list_contatos, vinculos);
        this.lvContatos.setAdapter(contatosAdapter);
    }
}