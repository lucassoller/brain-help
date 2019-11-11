package com.example.app.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.classes.dto.LoginRequestDto;
import com.example.app.classes.dto.LoginResponseDto;
import com.example.app.services.DiagnosticadoService;
import com.example.app.services.LoginService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaLogin extends AppCompatActivity {

    private Button btEntrar;
    private EditText etEmail;
    private EditText etSenha;

    private Retrofit retrofit;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        btEntrar = findViewById(R.id.bt_entrar);
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senha);

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
//                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });
    }

    private void logar(){
        LoginService loginService = retrofit.create(LoginService.class);
        loginService.logarDiagnosticado(new LoginRequestDto(etEmail.getText().toString(), etSenha.getText().toString())).enqueue(new Callback<LoginResponseDto>() {
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
        DiagnosticadoService service = retrofit.create(DiagnosticadoService.class);
        service.buscarTodos(token).enqueue(new Callback<List<Diagnosticado>>() {
            @Override
            public void onResponse(Call<List<Diagnosticado>> call, Response<List<Diagnosticado>> response) {
                if(response.isSuccessful()){
                    List<Diagnosticado> listaDiagnosticado = response.body();
                    Toast.makeText(TelaLogin.this, "O tamanho da lista é:" + listaDiagnosticado.size(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TelaLogin.this, "Não foi possível buscar lista", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Diagnosticado>> call, Throwable t) {
                Toast.makeText(TelaLogin.this, "Falha na comunicação", Toast.LENGTH_LONG).show();
            }
        });
    }

}
