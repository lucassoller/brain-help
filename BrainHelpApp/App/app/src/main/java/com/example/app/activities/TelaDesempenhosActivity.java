package com.example.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.app.R;
import com.example.app.adapter.DesempenhosAdapter;
import com.example.app.classes.Desempenho;

import java.util.ArrayList;
import java.util.List;

public class TelaDesempenhosActivity extends AppCompatActivity {

    private RecyclerView rvDesempenhos;
    private Button btAdicionarContato;
    private List<Desempenho> desempenhos = new ArrayList<>();
    public static DesempenhosAdapter desempenhosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desempenhos);
        this.inicializaComponentes();
    }

    private void inicializaComponentes(){

    }
}
