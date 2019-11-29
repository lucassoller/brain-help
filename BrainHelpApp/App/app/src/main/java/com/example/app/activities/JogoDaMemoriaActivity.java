package com.example.app.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.app.R;
import com.example.app.classes.Atividade;
import com.example.app.classes.Card;
import com.example.app.classes.Desempenho;
import com.example.app.enumerated.AvaliacaoDesempenho;
import com.example.app.services.DesempenhoService;
import com.example.app.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class JogoDaMemoriaActivity extends AppCompatActivity {
    private Chronometer cronometro;
    private TextView tvNomeJogo;
    private Button btIniciar;
    private Button btParar;
    private Atividade atividade;
    private ArrayList<ImageView> ivCards = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private int idJogoMemoria;
    private int cartasViradas = 0;
    private int totalCartasViradas = 0;
    private ImageView iv1;
    private ImageView iv2;
    private Card c1;
    private AlertDialog alerta;
    private int pontuacao = 0;
    private String avaliacao;
    private Desempenho desempenho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_da_memoria);
        this.inicializaComponentes();
        this.tvNomeJogo.setText(atividade.getNome());
        this.btParar.setClickable(false);
        this.btParar.setVisibility(View.INVISIBLE);
        this.btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
            }
        });

        this.btParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciar();
            }
        });


        for (int i = 0; i < 12; i++) {
            final int y = i;
            this.ivCards.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    virar(y);
                }
            });
        }
    }

    private void virar(int i){
        if(cronometro.isActivated()) {
            if(cartasViradas == 2){
                cartasViradas = 0;
                c1 = null;
                iv1.setImageResource(idJogoMemoria);
                iv2.setImageResource(idJogoMemoria);
            }
            final Card card = cards.get(i);
            if(!card.isVirada() && card != c1) {
                ImageView ivCard = ivCards.get(i);
                ivCard.setImageResource(card.getIdFoto());
                cartasViradas++;
                if (cartasViradas == 1) {
                    iv1 = ivCard;
                    c1 = card;
                } else if (cartasViradas == 2) {
                    iv2 = ivCard;
                    if (c1.getId().equals(card.getId())) {
                        if (totalCartasViradas != 12) {
                            Toast.makeText(getApplicationContext(), "Você encontrou um par!", Toast.LENGTH_SHORT).show();
                            c1.setVirada(true);
                            card.setVirada(true);
                            totalCartasViradas += 2;
                            cartasViradas = 0;
                        }
                        if (totalCartasViradas == 12) {
                            Toast.makeText(getApplicationContext(), "Parabéns, você ganhou!", Toast.LENGTH_LONG).show();
                            c1 = null;
                            totalCartasViradas = 0;
                            cartasViradas = 0;
                            reiniciar();
                            showDialog();
                        }
                    }
                }
            }
        }
    }

    private void inicializaComponentes(){
        this.idJogoMemoria = R.drawable.memory_card;
        this.tvNomeJogo = findViewById(R.id.tv_nome_jogo);
        this.btIniciar = findViewById(R.id.bt_iniciar);
        this.btParar = findViewById(R.id.bt_parar);
        this.atividade = (Atividade) getIntent().getSerializableExtra("atividade");
        this.cronometro = findViewById(R.id.chronometer);

        this.ivCards.add((ImageView) findViewById(R.id.iv_card1));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card2));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card3));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card4));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card5));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card6));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card7));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card8));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card9));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card10));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card11));
        this.ivCards.add((ImageView) findViewById(R.id.iv_card12));

        this.cards.add(new Card("A", R.drawable.house));
        this.cards.add(new Card("A", R.drawable.house));
        this.cards.add(new Card("B", R.drawable.album));
        this.cards.add(new Card("B", R.drawable.album));
        this.cards.add(new Card("C", R.drawable.chatbot));
        this.cards.add(new Card("C", R.drawable.chatbot));
        this.cards.add(new Card("D", R.drawable.medicine));
        this.cards.add(new Card("D", R.drawable.medicine));
        this.cards.add(new Card("E", R.drawable.puzzle));
        this.cards.add(new Card("E", R.drawable.puzzle));
        this.cards.add(new Card("F", R.drawable.logo_brain));
        this.cards.add(new Card("F", R.drawable.logo_brain));
    }

    private void reiniciar(){
        c1 = null;
        cronometro.stop();
        cronometro.setActivated(false);
        btParar.setClickable(false);
        btParar.setVisibility(View.INVISIBLE);
        btIniciar.setClickable(true);
        btIniciar.setVisibility(View.VISIBLE);
    }

    private void iniciar(){
        Collections.shuffle(this.cards);
        for (int i = 0; i < 12; i++) {
            this.ivCards.get(i).setImageResource(idJogoMemoria);
            this.cards.get(i).setVirada(false);
        }
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
        cronometro.setActivated(true);
        btParar.setClickable(true);
        btParar.setVisibility(View.VISIBLE);
        btIniciar.setClickable(false);
        btIniciar.setVisibility(View.INVISIBLE);
    }

    private void calcularDesempenho(){
        desempenho = new Desempenho();
        desempenho.setAtividade(atividade.getNome());
        String [] tempo = cronometro.getText().toString().split(":");
        int minutos = Integer.parseInt(tempo[0]);
        int segundos = Integer.parseInt(tempo[1]);

        segundos = segundos + (minutos * 60);

        int pontuacaoMaxima = atividade.getPontuacaoTotal();
        pontuacao = pontuacaoMaxima - (segundos - 15) ;
        if(pontuacao > pontuacaoMaxima){
            pontuacao = pontuacaoMaxima;
        }

        desempenho.setPontuacao(pontuacao);

        if(pontuacao <= pontuacaoMaxima/4){
            desempenho.setAvaliacaoDesempenho(AvaliacaoDesempenho.RUIM);
        }else if(pontuacao <= (pontuacaoMaxima * 2) / 4) {
            desempenho.setAvaliacaoDesempenho(AvaliacaoDesempenho.REGULAR);
        }else if(pontuacao <= (pontuacaoMaxima * 3) / 4) {
            desempenho.setAvaliacaoDesempenho(AvaliacaoDesempenho.BOA);
        }else{
            desempenho.setAvaliacaoDesempenho(AvaliacaoDesempenho.EXCELENTE);
        }

        desempenho.setDataRealizacao(new Date());

        cadastrarDesempenho();
    }

    private void showDialog() {
        calcularDesempenho();
        AlertDialog.Builder builder = new AlertDialog.Builder(JogoDaMemoriaActivity.this);
        builder.setTitle("Desempenho");
        builder.setMessage("Você obteve " + pontuacao + " pontos e uma avalição " + desempenho.getAvaliacaoDesempenho().toString() + " na atividade " + atividade.getNome());
        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        alerta = builder.create();
        alerta.show();
    }

    private void cadastrarDesempenho(){
        DesempenhoService desempenhoService = RetrofitUtils.retrofit.create(DesempenhoService.class);
        desempenhoService.cadastrarDesempenho(TelaInicialActivity.sp.getString("token", null), desempenho).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
