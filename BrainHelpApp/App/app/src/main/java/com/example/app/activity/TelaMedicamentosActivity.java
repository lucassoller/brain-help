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
import com.example.app.adapter.MedicamentosAdapter;
import com.example.app.model.Medicamento;
import com.example.app.service.MedicamentoService;
import com.example.app.util.MyErrorMessage;
import com.example.app.util.RetrofitUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class TelaMedicamentosActivity extends AppCompatActivity {

    private ListView lvMedicamentos;
    private Button btAdicionarMedicamentos;
    private List<Medicamento> medicamentos = new ArrayList<>();
    public static MedicamentosAdapter medicamentosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_medicamentos);
        this.inicializaComponentes();
        this.lvMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Medicamento medicamento = medicamentos.get(i);
                Intent itTelaMedicamentoCard = new Intent(TelaMedicamentosActivity.this, TelaMedicamentoCardActivity.class);
                itTelaMedicamentoCard.putExtra("medicamento", medicamento);
                startActivity(itTelaMedicamentoCard);
            }
        });

        this.btAdicionarMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaMedicamentoCard = new Intent(TelaMedicamentosActivity.this, TelaMedicamentoCardActivity.class);
                startActivity(itTelaMedicamentoCard);
            }
        });
    }

    private void inicializaComponentes(){
        this.lvMedicamentos = findViewById(R.id.lv_medicamentos);
        this.btAdicionarMedicamentos = findViewById(R.id.bt_adicionar_medicamento);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getMedicamentos();
    }

    private void getMedicamentos(){
        MedicamentoService medicamentoService = RetrofitUtils.retrofit.create(MedicamentoService.class);
        medicamentoService.buscarTodosMedicamentosDoUsuario(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<List<Medicamento>>() {
            @Override
            public void onResponse(Call<List<Medicamento>> call, Response<List<Medicamento>> response) {
                if(response.isSuccessful()){
                    medicamentos = response.body();
                    medicamentosAdapter = new MedicamentosAdapter(TelaMedicamentosActivity.this, R.layout.list_medicamentos, medicamentos);
                    lvMedicamentos.setAdapter(medicamentosAdapter);
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaMedicamentosActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Medicamento>> call, Throwable t) {

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
                Intent itTelaUsuario = new Intent(TelaMedicamentosActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaMedicamentosActivity.this, TelaAlterarSenhaActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaMedicamentosActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaMedicamentosActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}