package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.enumerated.EstagioAlzheimer;
import com.example.app.enumerated.Sexo;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.util.Date;
import java.util.List;

public class TelaCadastro2Activity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty(message = "Idade é obrigatória")
    private EditText etIdade;
    @NotEmpty(message = "Telefone é obrigatório")
    private EditText etTelefone;
    @NotEmpty(message = "Data do diagnóstico é obrigatória")
    private EditText etDataDiagnostico;
    private EditText etChaveSeguranca;
    @Checked(message = "Estágio Alzheimer é obrigatório")
    private RadioGroup rgEstagio;
    @Checked(message = "Sexo é obrigatório")
    private RadioGroup rgSexo;
    private RadioButton rbEstagioSelecionado;
    private RadioButton rbSexoSelecionado;
    private Button btContinuar;
    private TextView tvLogar;
    private Diagnosticado diagnosticado;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro2);
        this.inicializaComponentes();
        this.btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        this.tvLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaLogin = new Intent(TelaCadastro2Activity.this, TelaLoginActivity.class);
                startActivity(itTelaLogin);
                finish();
            }
        });

        this.rgEstagio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rbEstagioSelecionado = findViewById(i);
            }
        });

        this.rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rbSexoSelecionado = findViewById(i);
            }
        });
    }

    private void inicializaComponentes(){
        this.diagnosticado = (Diagnosticado) getIntent().getSerializableExtra("diagnosticado");
        this.etIdade = findViewById(R.id.et_idade);
        this.etTelefone = findViewById(R.id.et_telefone);
        this.etDataDiagnostico = findViewById(R.id.et_data_diagnostico);
        this.etChaveSeguranca = findViewById(R.id.et_chave_seguranca);
        this.rgEstagio = findViewById(R.id.rg_estagio);
        this.rgSexo = findViewById(R.id.rg_sexo);
        this.btContinuar = findViewById(R.id.bt_continuar);
        this.tvLogar = findViewById(R.id.tv_logar);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        diagnosticado.setIdade(Integer.parseInt(etIdade.getText().toString()));
        diagnosticado.setTelefone(etTelefone.getText().toString());

        if(!etChaveSeguranca.getText().toString().equals("")){
            diagnosticado.setChaveSeguranca(etChaveSeguranca.getText().toString());
        }

        if(rbEstagioSelecionado.getText().toString().equals("Inicial")){
            diagnosticado.setEstagioAlzheimer(EstagioAlzheimer.INICIAL);
        }else if (rbEstagioSelecionado.getText().toString().equals("Intermediário")){
            diagnosticado.setEstagioAlzheimer(EstagioAlzheimer.INTERMEDIARIO);
        }else{
            diagnosticado.setEstagioAlzheimer(EstagioAlzheimer.AVANCADO);
        }

        if(rbSexoSelecionado.getText().toString().equals("Masculino")){
            diagnosticado.setSexo(Sexo.M);
        }else{
            diagnosticado.setSexo(Sexo.F);
        }

        String dataDiagnosticoVet [] = etDataDiagnostico.getText().toString().split("/");
        String dia = dataDiagnosticoVet[0];
        String mes = dataDiagnosticoVet[1];
        String ano = dataDiagnosticoVet[2];
        diagnosticado.setDataDiagnostico(new Date(mes+"/"+dia+"/"+ano));

        Intent itTelaCadastro3 = new Intent(TelaCadastro2Activity.this, TelaCadastro3Activity.class);
        itTelaCadastro3.putExtra("diagnosticado", diagnosticado);
        startActivity(itTelaCadastro3);
    }

    @Override
    public void onValidationFailed(List<ValidationError> listaErros) {
        for(ValidationError erro:listaErros) {
            View componente = erro.getView();
            String mensagem = erro.getCollatedErrorMessage(this);
            if (componente instanceof EditText) {
                // setar o erro no componente edittext
                ((EditText) componente).setError(mensagem);
            } else if (componente instanceof RadioGroup) {
                // exibe um toast
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            }
        }
    }
}