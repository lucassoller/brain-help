package com.example.app.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.services.DiagnosticadoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
//                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DiagnosticadoService service = retrofit.create(DiagnosticadoService.class);
        service.buscarTodos().enqueue(new Callback<List<Diagnosticado>>() {
            @Override
            public void onResponse(Call<List<Diagnosticado>> call, Response<List<Diagnosticado>> response) {
                if(response.isSuccessful()){
                    List<Diagnosticado> listaDiagnosticado = response.body();
                    Toast.makeText(MainActivity.this, "O tamanho da lista é:" + listaDiagnosticado.size(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Não foi possível buscar lista", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Diagnosticado>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na comunicação", Toast.LENGTH_LONG).show();
            }
        });

    }
}
