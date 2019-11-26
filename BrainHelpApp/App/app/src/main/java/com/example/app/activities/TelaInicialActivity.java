package com.example.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.R;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaInicialActivity extends AppCompatActivity {

    private Button btLogin;
    private Button btCadastrar;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    public static Retrofit retrofit;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inicializaComponentes();

        String emailLogado = this.sp.getString("email", null);
        if(emailLogado != null){
            Intent itTelaLogado = new Intent(TelaInicialActivity.this, TelaHomeActivity.class);
            startActivity(itTelaLogado);
            finish();
        }

        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itTelaLogin = new Intent(TelaInicialActivity.this, TelaHomeActivity.class);
                startActivity(itTelaLogin);
            }
        });

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaCadastro = new Intent(TelaInicialActivity.this, TelaCadastroActivity.class);
                startActivity(itTelaCadastro);
            }
        });
    }

    private void inicializaComponentes(){
        this.btLogin = findViewById(R.id.bt_entrar);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        sp = getSharedPreferences("brain_help", MODE_PRIVATE);
        editor = sp.edit();
        this.client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create()))
                .build();
    }
}