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
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaLoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Button btEntrar;
    private Button btGoogle;

    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email incorreto")
    private EditText etEmail;

    @NotEmpty(message = "Senha é obrigatória")
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText etSenha;

    private TextView tvEsqueceuSenha;
    private TextView tvCadastrar;
    private Retrofit retrofit;
    private OkHttpClient client;
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
        client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.15.1:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        loginService.logarDiagnosticado(new LoginRequestDto(etEmail.getText().toString(), etSenha.getText().toString())).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                TelaInicialActivity.editor.putString("email", response.body().getEmail());
                TelaInicialActivity.editor.putString("token", response.body().getToken());
                TelaInicialActivity.editor.commit();
                Intent itTelaHome = new Intent(TelaLoginActivity.this, TelaHomeActivity.class);
                startActivity(itTelaHome);
                finish();
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