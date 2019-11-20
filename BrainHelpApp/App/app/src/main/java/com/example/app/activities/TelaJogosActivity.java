package com.example.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.adapter.JogosAdapter;
import com.example.app.classes.Atividade;
import com.example.app.enumerated.TipoAtividade;

import java.util.ArrayList;

public class TelaJogosActivity extends AppCompatActivity {

    private ListView lvJogos;
    private ArrayList<Atividade> jogos = new ArrayList<>();
    private JogosAdapter jogosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogos);
        this.inicializaComponentes();
        Atividade atividade = new Atividade();
        atividade.setNome("Jogo da Memória");
        atividade.setPontuacaoTotal(100);
        atividade.setTipoAtividade(TipoAtividade.PUZZLE);
        atividade.setDescricao("Um jogo em que deve ser encontradas todas a peças iguais");
        jogos.add(atividade);
        jogos.add(atividade);
        jogos.add(atividade);
        jogos.add(atividade);
        jogos.add(atividade);
        jogos.add(atividade);
        jogos.add(atividade);
        jogos.add(atividade);
    }

    private void inicializaComponentes(){
        this.lvJogos = findViewById(R.id.lv_jogos);
        this.jogosAdapter = new JogosAdapter(this, android.R.layout.simple_list_item_1, jogos);
        this.lvJogos.setAdapter(jogosAdapter);
    }
}
