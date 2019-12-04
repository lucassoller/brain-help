package com.example.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Tarefa;
import com.example.app.enumerated.StatusTarefa;
import com.example.app.services.TarefaService;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaMedicamentoCardActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "O título da tarefa é obrigatório")
    private EditText etTarefa;
    @NotEmpty(message = "A data é obrigatório")
    private EditText etData;
    @NotEmpty(message = "A hora é obrigatório")
    private EditText etHora;
    private EditText etDescricao;
    private Button btCadastrar;
    private Button btEditar;
    private Button btExcluir;
    private Tarefa tarefa;
    private String acao;
    private RelativeLayout relativeLayout;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tarefa_card);
        this.inicializaComponentes();
        if(tarefa != null){
            this.etTarefa.setText(this.tarefa.getTarefa());
            String data[] = this.tarefa.getDataRealizacao().toString().split("T");
            this.etData.setText(data[0]);
            this.etHora.setText(data[1]);
            this.etDescricao.setText(this.tarefa.getDescricao());
            if(!this.tarefa.getStatusTarefa().equals(StatusTarefa.CONCLUIDA)){
                this.acao = "editar";
                this.relativeLayout.removeView(btCadastrar);
            }else{
                this.relativeLayout.removeView(btEditar);
                this.relativeLayout.removeView(btExcluir);
            }

        }else{
            this.acao = "cadastrar";
            this.relativeLayout.removeView(btEditar);
            this.relativeLayout.removeView(btExcluir);
        }

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        this.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        this.btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

    }

    private void inicializaComponentes(){
        this.tarefa = (Tarefa) getIntent().getSerializableExtra("tarefa");
        this.etTarefa = findViewById(R.id.et_tarefa);
        this.etData = findViewById(R.id.et_data);
        this.etHora = findViewById(R.id.et_hora);
        this.etDescricao = findViewById(R.id.et_descricao);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        this.btEditar = findViewById(R.id.bt_editar);
        this.btExcluir = findViewById(R.id.bt_excluir);
        this.relativeLayout = findViewById(R.id.layout);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        this.tarefa.setTarefa(etTarefa.getText().toString());
        String d[] = etData.getText().toString().split("/");
        String t[] = etHora.getText().toString().split(":");
        this.tarefa.setDataRealizacao(new Date(Integer.parseInt(d[2]),Integer.parseInt(d[1]),Integer.parseInt(d[0]),
                Integer.parseInt(t[0]),Integer.parseInt(t[1])));
        this.tarefa.setDescricao(etDescricao.getText().toString());
        if(this.acao.equals("cadastrar")){
            this.cadastrar();
        }else{
            this.editar();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError erro:errors) {
            View componente = erro.getView();
            String mensagem = erro.getCollatedErrorMessage(this);
            if (componente instanceof EditText) {
                // setar o erro no componente edittext
                ((EditText) componente).setError(mensagem);
            } else if (componente instanceof RadioGroup || componente instanceof Spinner) {
                // exibe um toast
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cadastrar(){
        TarefaService tarefaService = RetrofitUtils.retrofit.create(TarefaService.class);
        tarefaService.cadastrarTarefa(TelaInicialActivity.sp.getString("token", null), tarefa).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaTarefaCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editar(){
        TarefaService tarefaService = RetrofitUtils.retrofit.create(TarefaService.class);
        tarefaService.cadastrarTarefa(TelaInicialActivity.sp.getString("token", null), tarefa).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaTarefaCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir() {
        TarefaService tarefaService = RetrofitUtils.retrofit.create(TarefaService.class);
        tarefaService.deletarTarefa(TelaInicialActivity.sp.getString("token", null), tarefa.getCodTarefa()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaTarefaCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
