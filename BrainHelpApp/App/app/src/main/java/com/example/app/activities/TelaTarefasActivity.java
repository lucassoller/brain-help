package com.example.app.activities;

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
import com.example.app.adapter.TarefasAdapter;
import com.example.app.classes.Tarefa;
import com.example.app.services.TarefaService;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class TelaTarefasActivity extends AppCompatActivity {

    private ListView lvTarefas;
    private Button btAdicionarTarefa;
    private List<Tarefa> tarefas = new ArrayList<>();
    public static TarefasAdapter tarefasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tarefas);
        this.inicializaComponentes();
        this.lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tarefa tarefa = tarefas.get(i);
                Intent itTelaTarefaCard = new Intent(TelaTarefasActivity.this, TelaTarefaCardActivity.class);
                itTelaTarefaCard.putExtra("tarefa", tarefa);
                startActivity(itTelaTarefaCard);
            }
        });

        this.btAdicionarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaTarefaCard = new Intent(TelaTarefasActivity.this, TelaTarefaCardActivity.class);
                startActivity(itTelaTarefaCard);
            }
        });
    }

    private void inicializaComponentes(){
        this.lvTarefas = findViewById(R.id.lv_tarefas);
        this.btAdicionarTarefa = findViewById(R.id.bt_adicionar_tarefa);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getTarefas();
    }

    private void getTarefas(){
        TarefaService tarefaService = RetrofitUtils.retrofit.create(TarefaService.class);
        tarefaService.buscarTodasTarefas(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<List<Tarefa>>() {
            @Override
            public void onResponse(Call<List<Tarefa>> call, Response<List<Tarefa>> response) {
                if(response.isSuccessful()){
                    tarefas = response.body();
                    tarefasAdapter = new TarefasAdapter(TelaTarefasActivity.this, R.layout.list_tarefas, tarefas);
                    lvTarefas.setAdapter(tarefasAdapter);
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaTarefasActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tarefa>> call, Throwable t) {

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
                Intent itTelaUsuario = new Intent(TelaTarefasActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaTarefasActivity.this, TelaAlterarSenhaActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaTarefasActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaTarefasActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}