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
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaContatosActivity extends AppCompatActivity {

    private ListView lvContatos;
    private Button btAdicionarContato;
    private List<Vinculo> vinculos = new ArrayList<>();
    public static ContatosAdapter contatosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contatos);
        this.inicializaComponentes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getVinculos();
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
        getVinculos();
        this.lvContatos = findViewById(R.id.lv_contatos);
        this.btAdicionarContato = findViewById(R.id.bt_adicionar_contato);
    }

    private void getVinculos(){
        VinculoService vinculoService = RetrofitUtils.retrofit.create(VinculoService.class);
        vinculoService.buscarTodosVinculosDoUsuario(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<List<Vinculo>>() {
            @Override
            public void onResponse(Call<List<Vinculo>> call, Response<List<Vinculo>> response) {
                if(response.isSuccessful()){
                    vinculos = response.body();
                    contatosAdapter = new ContatosAdapter(TelaContatosActivity.this, R.layout.list_contatos, vinculos);
                    lvContatos.setAdapter(contatosAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vinculo>> call, Throwable t) {

            }
        });
    }
}