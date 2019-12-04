package com.example.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.app.R;
import com.example.app.classes.dto.RedefinicaoSenhaRequestDto;
import com.example.app.services.DiagnosticadoService;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class TelaAlterarSenhaActivity extends AppCompatActivity implements Validator.ValidationListener{

    private Button btAlterar;
    private EditText etSenhaAtual;
    @Password
    private EditText etNovaSenha;
    @ConfirmPassword
    private EditText etConfimarSenha;
    private Validator validator;
    private RedefinicaoSenhaRequestDto redefinicaoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
        this.inicializaComponentes();
        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        redefinicaoSenha = new RedefinicaoSenhaRequestDto();
        redefinicaoSenha.setSenhaAtual(etSenhaAtual.getText().toString());
        redefinicaoSenha.setSenha(etNovaSenha.getText().toString());
        alterarSenha();
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

    private void inicializaComponentes(){
        this.btAlterar = findViewById(R.id.bt_alterar_senha);
        this.etSenhaAtual = findViewById(R.id.et_senha_atual);
        this.etNovaSenha = findViewById(R.id.et_nova_senha);
        this.etConfimarSenha = findViewById(R.id.et_confirmar_senha);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void alterarSenha(){
        DiagnosticadoService diagnosticadoService = RetrofitUtils.retrofit.create(DiagnosticadoService.class);
        diagnosticadoService.editarSenhaDiagnosticado(TelaInicialActivity.sp.getString("token", null), redefinicaoSenha).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TelaAlterarSenhaActivity.this, "Senha alterada!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaAlterarSenhaActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
