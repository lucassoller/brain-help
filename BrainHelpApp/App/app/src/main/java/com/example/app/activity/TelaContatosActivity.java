package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.adapter.ContatosAdapter;
import com.example.app.model.Vinculo;
import com.example.app.service.VinculoService;
import com.example.app.util.MyErrorMessage;
import com.example.app.util.RetrofitUtils;
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

    @Override
    protected void onResume() {
        super.onResume();
        this.getVinculos();
    }
    
    private void inicializaComponentes(){
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
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaContatosActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vinculo>> call, Throwable t) {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent itTelaUsuario = new Intent(TelaContatosActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaContatosActivity.this, TelaContatosActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaContatosActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaContatosActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}