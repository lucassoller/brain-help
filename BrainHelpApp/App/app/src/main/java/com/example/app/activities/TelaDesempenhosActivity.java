package com.example.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.adapter.DesempenhosAdapter;
import com.example.app.classes.Desempenho;
import com.example.app.services.DesempenhoService;
import com.example.app.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaDesempenhosActivity extends AppCompatActivity {

    private ListView lvDesempenhos;
    private List<Desempenho> desempenhos = new ArrayList<>();
    public static DesempenhosAdapter desempenhosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desempenhos);
        this.inicializaComponentes();
    }

    private void inicializaComponentes(){
        getDesempenhos();
        this.lvDesempenhos = findViewById(R.id.lv_desempenhos);
    }

    private void getDesempenhos(){
        DesempenhoService desempenhoService = RetrofitUtils.retrofit.create(DesempenhoService.class);
        desempenhoService.buscarTodosDesempenhos(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<List<Desempenho>>() {
            @Override
            public void onResponse(Call<List<Desempenho>> call, Response<List<Desempenho>> response) {
                if(response.isSuccessful()){
                    desempenhos = response.body();
                    Collections.reverse(desempenhos);
                    desempenhosAdapter = new DesempenhosAdapter(TelaDesempenhosActivity.this, R.layout.list_desempenhos, desempenhos);
                    lvDesempenhos.setAdapter(desempenhosAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Desempenho>> call, Throwable t) {

            }
        });
    }
}
