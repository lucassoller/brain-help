package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class TelaCadastroActivity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty(message = "Nome é obrigatório")
    private EditText etNome;

    @NotEmpty(message = "Sobrenome é obrigatório")
    private EditText etSobrenome;

    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email incorreto")
    private EditText etEmail;

    @NotEmpty(message = "Senha é obrigatória")
    @Password
    private EditText etSenha;

    @ConfirmPassword(message = "As senhas não coincidem")
    private EditText etConfirmarSenha;

    private Button btContinuar;
    private TextView tvLogar;
    private Diagnosticado diagnosticado;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
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
                Intent itTelaLogin = new Intent(TelaCadastroActivity.this, TelaLoginActivity.class);
                startActivity(itTelaLogin);
                finish();
            }
        });
    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome);
        this.etSobrenome = findViewById(R.id.et_sobrenome);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.etConfirmarSenha = findViewById(R.id.et_confirmar_senha);
        this.btContinuar = findViewById(R.id.bt_continuar);
        this.tvLogar = findViewById(R.id.tv_logar);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        diagnosticado = new Diagnosticado();
        diagnosticado.setNome(etNome.getText().toString());
        diagnosticado.setSobrenome(etSobrenome.getText().toString());
        diagnosticado.setEmail(etEmail.getText().toString());
        diagnosticado.setSenha(etEmail.getText().toString());
        Intent itTelaCadastro2 = new Intent(TelaCadastroActivity.this, TelaCadastro2Activity.class);
        itTelaCadastro2.putExtra("diagnosticado", diagnosticado);
        startActivity(itTelaCadastro2);
    }

    @Override
    public void onValidationFailed(List<ValidationError> listaErros) {
        for(ValidationError erro:listaErros) {
            View componente = erro.getView();
            String mensagem = erro.getCollatedErrorMessage(this);
            if(componente instanceof EditText) {
                // setar o erro no componente edittext
                ((EditText) componente).setError(mensagem);
            }
        }
    }
}