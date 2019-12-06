package com.example.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Tarefa;
import com.example.app.enumerated.StatusTarefa;
import com.example.app.services.TarefaService;
import com.example.app.utils.AlarmSender;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaTarefaCardActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "O título da tarefa é obrigatório")
    private EditText etTarefa;
    @NotEmpty(message = "A data é obrigatório")
    private EditText etData;
    @NotEmpty(message = "A hora é obrigatório")
    private EditText etHora;
    private EditText etDescricao;
    private RadioGroup rbStatus;
    private RadioButton rbSelecionado;
    private RadioButton rbCompleta;
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
            this.rbCompleta.setEnabled(true);
            this.etTarefa.setText(this.tarefa.getTarefa());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            String data = dateFormat.format(tarefa.getDataRealizacao());
            this.etData.setText(data);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", new Locale("pt", "BR"));
            String hora = timeFormat.format(tarefa.getDataRealizacao());
            this.etHora.setText(hora);
            this.etDescricao.setText(this.tarefa.getDescricao());
            if(tarefa.getStatusTarefa().equals(StatusTarefa.CONCLUIDA)){
                rbCompleta.setChecked(true);
            }else{
                rbSelecionado = findViewById(R.id.rb_incompleta);
                rbSelecionado.setChecked(true);
            }
            this.relativeLayout.removeView(btCadastrar);
        }else{
            tarefa = new Tarefa();
            this.acao = "cadastrar";
            this.rbCompleta.setEnabled(false);
            this.relativeLayout.removeView(btEditar);
            this.relativeLayout.removeView(btExcluir);
        }

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        this.rbStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rbSelecionado = findViewById(i);
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
        this.rbStatus = findViewById(R.id.rg_status);
        this.rbCompleta = findViewById(R.id.rb_completa);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        this.tarefa.setTarefa(etTarefa.getText().toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        try {
            tarefa.setDataRealizacao(dateFormat.parse(etData.getText().toString() + " " + etHora.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(rbSelecionado.getText().toString().equals("Concluída")){
            tarefa.setStatusTarefa(StatusTarefa.CONCLUIDA);
        }else{
            tarefa.setStatusTarefa(StatusTarefa.NAO_CONCLUIDA);
        }
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
                    AlarmSender.agendarTarefa(TelaTarefaCardActivity.this, tarefa);
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
        tarefaService.editarTarefa(TelaInicialActivity.sp.getString("token", null), tarefa).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Edição concluída", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(), "Exclusão concluída", Toast.LENGTH_LONG).show();
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
