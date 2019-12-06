package com.example.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.app.R;
import com.example.app.utils.AlarmSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TelaInicialActivity extends AppCompatActivity{

    private Button btLogin;
    private Button btCadastrar;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

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
                Intent itTelaLogin = new Intent(TelaInicialActivity.this, TelaLoginActivity.class);
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


//        Date date = null;
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
//        try {
//            date = (format.parse("05/12/2019 22:22:10"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Date date2 = null;
//        try {
//            date2 = (format.parse("05/12/2019 22:23:10"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        AlarmSender.agendarNotificacao(this, date, 0, "Limpar casa", "realizar agora", "a");
//        AlarmSender.agendarNotificacao(this, date2, 1, "Tirar lixo", "saco preto", "b");

    }

    private void inicializaComponentes(){
        this.btLogin = findViewById(R.id.bt_entrar);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        sp = getSharedPreferences("brain_help", MODE_PRIVATE);
        editor = sp.edit();
    }
}