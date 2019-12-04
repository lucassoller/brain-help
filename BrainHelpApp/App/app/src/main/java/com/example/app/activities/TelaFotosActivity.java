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
import com.example.app.adapter.FotografiasAdapter;
import com.example.app.classes.Fotografia;
import com.example.app.services.FotografiaService;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return false;
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
}
