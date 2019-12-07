package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.adapter.JogosAdapter;
import com.example.app.model.Atividade;
import com.example.app.model.enumerated.TipoAtividade;
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
    }

    private void inicializaComponentes(){
        this.lvJogos = findViewById(R.id.lv_jogos);
        this.jogosAdapter = new JogosAdapter(this, android.R.layout.simple_list_item_1, jogos);
        this.lvJogos.setAdapter(jogosAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent itTelaUsuario = new Intent(TelaJogosActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaJogosActivity.this, TelaAlterarSenhaActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaJogosActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaJogosActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }

}
