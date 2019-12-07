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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.app.R;
import com.example.app.adapter.EnderecosAdapter;
import com.example.app.model.Endereco;
import com.example.app.service.EnderecoService;
import com.example.app.util.MyErrorMessage;
import com.example.app.util.RetrofitUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class TelaEnderecosActivity extends AppCompatActivity {

    private ListView lvEnderecos;
    private Button btAdicionarEndereco;
    private List<Endereco> enderecos = new ArrayList<>();
    public static EnderecosAdapter enderecosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_enderecos);
        this.inicializaComponentes();
        this.lvEnderecos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Endereco endereco = enderecos.get(i);
                Intent itTelaEnderecoCard = new Intent(TelaEnderecosActivity.this, TelaEnderecoCardActivity.class);
                itTelaEnderecoCard.putExtra("endereco", endereco);
                startActivity(itTelaEnderecoCard);
            }
        });

        this.btAdicionarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaEnderecoCard = new Intent(TelaEnderecosActivity.this, TelaEnderecoCardActivity.class);
                startActivity(itTelaEnderecoCard);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getEnderecos();
    }

    private void inicializaComponentes(){
        this.lvEnderecos = findViewById(R.id.lv_enderecos);
        this.btAdicionarEndereco = findViewById(R.id.bt_adicionar_endereco);
    }

    private void getEnderecos(){
        EnderecoService enderecoService = RetrofitUtils.retrofit.create(EnderecoService.class);
        enderecoService.buscarTodosEnderecosDoUsuario(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<List<Endereco>>() {
            @Override
            public void onResponse(Call<List<Endereco>> call, Response<List<Endereco>> response) {
                if(response.isSuccessful()){
                    enderecos = response.body();
                    enderecosAdapter = new EnderecosAdapter(TelaEnderecosActivity.this, R.layout.list_enderecos, enderecos);
                    lvEnderecos.setAdapter(enderecosAdapter);
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaEnderecosActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Endereco>> call, Throwable t) {

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
                Intent itTelaUsuario = new Intent(TelaEnderecosActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaEnderecosActivity.this, TelaEnderecosActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaEnderecosActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaEnderecosActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}