package com.example.app.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Vinculo;

public class TelaContatoCardActivity extends AppCompatActivity {

    private Vinculo vinculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_card);
        this.inicializaComponentes();
        if(vinculo == null){

        }else{

        }
    }

    private void inicializaComponentes() {
        this.vinculo = (Vinculo) getIntent().getSerializableExtra("vinculo");
    }
}