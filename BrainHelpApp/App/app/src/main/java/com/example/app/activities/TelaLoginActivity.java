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
import com.example.app.classes.dto.LoginRequestDto;
import com.example.app.classes.dto.LoginResponseDto;
import com.example.app.services.LoginService;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaLoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Button btEntrar;
    private Button btGoogle;

    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email incorreto")
    private EditText etEmail;
    @NotEmpty(message = "Senha é obrigatória")
    private EditText etSenha;
    private TextView tvEsqueceuSenha;
    private TextView tvCadastrar;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        this.inicializaComponentes();

        this.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        this.tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itTelaCadastro = new Intent(TelaLoginActivity.this, TelaCadastroActivity.class);
                startActivity(itTelaCadastro);
                finish();
            }
        });
    }

    private void logar(){
        LoginService loginService = RetrofitUtils.retrofit.create(LoginService.class);
        loginService.logarDiagnosticado(new LoginRequestDto(etEmail.getText().toString(), etSenha.getText().toString())).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                if(response.isSuccessful()){
                    TelaInicialActivity.editor.putString("email", response.body().getEmail());
                    TelaInicialActivity.editor.putString("token", response.body().getToken());
                    TelaInicialActivity.editor.commit();
                    Intent itTelaHome = new Intent(TelaLoginActivity.this, TelaHomeActivity.class);
                    startActivity(itTelaHome);
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaLoginActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializaComponentes(){
        this.btEntrar = findViewById(R.id.bt_entrar);
        this.btGoogle = findViewById(R.id.bt_google);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.tvEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha);
        this.tvCadastrar = findViewById(R.id.tv_cadastrar);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        logar();
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