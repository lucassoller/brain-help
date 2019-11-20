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
import com.example.app.classes.Endereco;
import com.example.app.classes.Vinculo;
import com.example.app.enumerated.Sexo;
import com.example.app.services.VinculoService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaContatosActivity extends AppCompatActivity {

    private ListView lvContatos;
    private Button btAdicionarContato;
    private ArrayList<Vinculo> vinculos = new ArrayList<>();
    public static ContatosAdapter contatosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contatos);
        this.inicializaComponentes();
        Vinculo vinculo = new Vinculo();
        vinculo.setNome("Lucas");
        vinculo.setSobrenome("Soller");
        vinculo.setVinculo("pai");
        vinculo.setTelefone("telefone");
        vinculo.setIdade(18);
        vinculo.setSexo(Sexo.M);
        vinculo.setLembrancas("aaa");
        Endereco endereco = new Endereco();
        endereco.setLogradouro("vitalino");
        endereco.setNumero(43);
        endereco.setEstado("RS");
        endereco.setCidade("Sapucaia");
        endereco.setBairro("Centro");
        endereco.setCep("93214-544");
        vinculo.setEndereco(endereco);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);
        vinculos.add(vinculo);

        this.contatosAdapter = new ContatosAdapter(this, R.layout.list_contatos, vinculos);
        this.lvContatos.setAdapter(contatosAdapter);

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
        this.lvContatos = findViewById(R.id.lv_contatos);
        this.btAdicionarContato = findViewById(R.id.bt_adicionar_contato);

    }
}