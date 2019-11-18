package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.classes.Endereco;
import com.example.app.classes.dto.LoginRequestDto;
import com.example.app.classes.dto.LoginResponseDto;
import com.example.app.services.LoginService;
import com.example.app.services.RegistroService;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaCadastro3Activity extends AppCompatActivity implements Validator.ValidationListener{

    private Diagnosticado diagnosticado;
    @NotEmpty(message = "Logradouro é obrigatório")
    private EditText etLogradouro;
    @NotEmpty(message = "Número é obrigatório")
    private EditText etNumero;
    @NotEmpty(message = "Estado é obrigatório")
    private EditText etEstado;
    @NotEmpty(message = "Cidade é obrigatória")
    private EditText etCidade;
    @NotEmpty(message = "Bairro é obrigatório")
    private EditText etBairro;
    @NotEmpty(message = "CEP é obrigatório")
    private EditText etCep;
    private Button btCadastrar;
    private TextView tvLogar;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro3);
        this.inicializaComponentes();
        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        this.tvLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaLogin = new Intent(TelaCadastro3Activity.this, TelaLoginActivity.class);
                startActivity(itTelaLogin);
                finish();
            }
        });
    }

    private void inicializaComponentes(){
        this.diagnosticado = (Diagnosticado) getIntent().getSerializableExtra("diagnosticado");
        this.etLogradouro = findViewById(R.id.et_logradouro);
        this.etNumero = findViewById(R.id.et_numero);
        this.etEstado = findViewById(R.id.et_estado);
        this.etCidade = findViewById(R.id.et_cidade);
        this.etBairro = findViewById(R.id.et_bairro);
        this.etCep = findViewById(R.id.et_cep);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        this.tvLogar = findViewById(R.id.tv_logar);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(etLogradouro.getText().toString());
        endereco.setNumero(Integer.parseInt(etNumero.getText().toString()));
        endereco.setEstado(etEstado.getText().toString());
        endereco.setCidade(etCidade.getText().toString());
        endereco.setBairro(etBairro.getText().toString());
        endereco.setCep(etCep.getText().toString());

        diagnosticado.setEndereco(endereco);
        cadastrar();
    }

    @Override
    public void onValidationFailed(List<ValidationError> listaErros) {
        for(ValidationError erro:listaErros) {
            View componente = erro.getView();
            String mensagem = erro.getCollatedErrorMessage(this);
            if (componente instanceof EditText) {
                // setar o erro no componente edittext
                ((EditText) componente).setError(mensagem);
            }
        }
    }

    private void cadastrar(){
        RegistroService registroService = TelaInicialActivity.retrofit.create(RegistroService.class);
        registroService.cadastrarDiagnosticado(diagnosticado).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Cadastro conluído! Fazendo login...", Toast.LENGTH_LONG).show();
                logar();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void logar(){
        LoginService loginService = TelaInicialActivity.retrofit.create(LoginService.class);
        loginService.logarDiagnosticado(new LoginRequestDto(diagnosticado.getEmail(), diagnosticado.getSenha())).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                TelaInicialActivity.editor.putString("email", response.body().getEmail());
                TelaInicialActivity.editor.putString("token", response.body().getToken());
                TelaInicialActivity.editor.commit();
                Intent itTelaHome = new Intent(TelaCadastro3Activity.this, TelaHomeActivity.class);
                startActivity(itTelaHome);
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}