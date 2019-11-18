package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;

public class TelaCadastroInicialActivity extends AppCompatActivity {

    private Button btCadastrarGoogle;
    private Button btCadastrar;
    private TextView tvLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_inicial);
        this.inicializaComponentes();
        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaCadastro = new Intent(TelaCadastroInicialActivity.this, TelaCadastroActivity.class);
                startActivity(itTelaCadastro);
            }
        });

        this.tvLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaLogin = new Intent(TelaCadastroInicialActivity.this, TelaLoginActivity.class);
                startActivity(itTelaLogin);
            }
        });
    }

    private void inicializaComponentes(){
        this.btCadastrarGoogle = findViewById(R.id.bt_cadastrar_google);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        this.tvLogar = findViewById(R.id.tv_logar);
    }
}
