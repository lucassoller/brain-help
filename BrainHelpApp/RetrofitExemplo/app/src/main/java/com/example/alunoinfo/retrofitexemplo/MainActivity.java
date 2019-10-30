package com.example.alunoinfo.retrofitexemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Retrofit retrofit;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .build();

        button = findViewById(R.id.button);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });


    }
    private void logar(){
        LoginService loginService = retrofit.create(LoginService.class);
        loginService.logar(new LoginRequestDto("lucas", "123")).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                Toast.makeText(getApplicationContext(), response.body().getToken(), Toast.LENGTH_LONG).show();
                String token = response.body().getToken();
                listar(token);
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
    private void listar(String token){
        MedicoService medicoService = retrofit.create(MedicoService.class);
        medicoService.listarMedicos(token).enqueue(new Callback<List<Medico>>() {
            @Override
            public void onResponse(Call<List<Medico>> call, Response<List<Medico>> response) {
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Medico>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
