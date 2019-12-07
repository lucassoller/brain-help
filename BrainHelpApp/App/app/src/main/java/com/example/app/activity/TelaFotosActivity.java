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
import com.example.app.adapter.FotografiasAdapter;
import com.example.app.model.Fotografia;
import com.example.app.service.FotografiaService;
import com.example.app.util.MyErrorMessage;
import com.example.app.util.RetrofitUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class TelaFotosActivity extends AppCompatActivity {

    private ListView lvFotografias;
    private Button btAdicionarFotografia;
    private List<Fotografia> fotografias = new ArrayList<>();
    public static FotografiasAdapter fotografiasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fotos);
        this.inicializaComponentes();
        this.lvFotografias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fotografia fotografia = fotografias.get(i);
                Intent itTelaFotoCard = new Intent(TelaFotosActivity.this, TelaFotoCardActivity.class);
                itTelaFotoCard.putExtra("fotografia", fotografia);
                startActivity(itTelaFotoCard);
            }
        });

        this.btAdicionarFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaFotoCard = new Intent(TelaFotosActivity.this, TelaFotoCardActivity.class);
                startActivity(itTelaFotoCard);
            }
        });
    }

    private void inicializaComponentes(){
        this.lvFotografias = findViewById(R.id.lv_fotografias);
        this.btAdicionarFotografia = findViewById(R.id.bt_adicionar_fotografia);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getFotos();
    }

    private void getFotos(){
        FotografiaService fotografiaService = RetrofitUtils.retrofit.create(FotografiaService.class);
        fotografiaService.buscarTodasFotografiasDoUsuario(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<List<Fotografia>>() {
            @Override
            public void onResponse(Call<List<Fotografia>> call, Response<List<Fotografia>> response) {
                if(response.isSuccessful()){
                    fotografias = response.body();
                    fotografiasAdapter = new FotografiasAdapter(TelaFotosActivity.this, R.layout.list_fotos, fotografias);
                    lvFotografias.setAdapter(fotografiasAdapter);
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaFotosActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Fotografia>> call, Throwable t) {

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
                Intent itTelaUsuario = new Intent(TelaFotosActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaFotosActivity.this, TelaFotosActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaFotosActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaFotosActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}
