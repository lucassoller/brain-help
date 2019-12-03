package com.example.app.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.classes.Endereco;
import com.example.app.classes.dto.LoginRequestDto;
import com.example.app.classes.dto.LoginResponseDto;
import com.example.app.enumerated.EstagioAlzheimer;
import com.example.app.enumerated.Sexo;
import com.example.app.services.DiagnosticadoService;
import com.example.app.services.LoginService;
import com.example.app.utils.BitmapUtils;
import com.example.app.utils.ImagePickerUtils;
import com.example.app.utils.RetrofitUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.File;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @NotEmpty(message = "Logradouro é obrigatório")
    private EditText etLogradouro;
    @NotEmpty(message = "Número é obrigatório")
    private EditText etNumero;
    @NotEmpty(message = "Cidade é obrigatória")
    private EditText etCidade;
    @NotEmpty(message = "Bairro é obrigatório")
    private EditText etBairro;
    private EditText etCep;
    private Button btCadastrar;
    private TextView tvLogar;
    private Diagnosticado diagnosticado;
    private Validator validator;
    private String [] strEstados = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MT", "MS", "PA",
            "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO"};
    private Spinner spEstados;
    private CircleImageView ivFoto;
    private Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        this.inicializaComponentes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strEstados);
        this.spEstados.setAdapter(adapter);
        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
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

        this.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = ImagePickerUtils.getPickImageIntent(TelaCadastroActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });
    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome);
        this.etSobrenome = findViewById(R.id.et_sobrenome);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.etConfirmarSenha = findViewById(R.id.et_confirmar_senha);
        this.etIdade = findViewById(R.id.et_idade);
        this.etTelefone = findViewById(R.id.et_telefone);
        this.etDataDiagnostico = findViewById(R.id.et_data_diagnostico);
        this.etChaveSeguranca = findViewById(R.id.et_chave_seguranca);
        this.rgEstagio = findViewById(R.id.rg_estagio);
        this.rgSexo = findViewById(R.id.rg_sexo);
        this.etLogradouro = findViewById(R.id.et_logradouro);
        this.etNumero = findViewById(R.id.et_numero);
        this.etCidade = findViewById(R.id.et_cidade);
        this.etBairro = findViewById(R.id.et_bairro);
        this.etCep = findViewById(R.id.et_cep);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        this.tvLogar = findViewById(R.id.tv_logar);
        this.spEstados = findViewById(R.id.sp_estados);
        this.ivFoto = findViewById(R.id.iv_foto);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        diagnosticado = new Diagnosticado();
        diagnosticado.setNome(etNome.getText().toString());
        diagnosticado.setSobrenome(etSobrenome.getText().toString());
        diagnosticado.setEmail(etEmail.getText().toString());
        diagnosticado.setSenha(etSenha.getText().toString());
        diagnosticado.setTelefone(etTelefone.getText().toString());
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
        Endereco endereco = new Endereco();
        endereco.setLogradouro(etLogradouro.getText().toString());
        endereco.setNumero(Integer.parseInt(etNumero.getText().toString()));
        endereco.setEstado(spEstados.getSelectedItem().toString());
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
            if(componente instanceof EditText) {
                // setar o erro no componente edittext
                ((EditText) componente).setError(mensagem);
            } else if (componente instanceof RadioGroup) {
                // exibe um toast
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cadastrar(){
        DiagnosticadoService diagnosticadoService = RetrofitUtils.retrofit.create(DiagnosticadoService.class);
        diagnosticadoService.cadastrarDiagnosticado(diagnosticado).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TelaCadastroActivity.this, "cadastrado!", Toast.LENGTH_SHORT).show();
                logar();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void logar(){
        LoginService loginService = RetrofitUtils.retrofit.create(LoginService.class);
        loginService.logarDiagnosticado(new LoginRequestDto(diagnosticado.getEmail(), diagnosticado.getSenha())).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                TelaInicialActivity.editor.putString("email", response.body().getEmail());
                TelaInicialActivity.editor.putString("token", response.body().getToken());
                TelaInicialActivity.editor.commit();
                Intent itTelaHome = new Intent(TelaCadastroActivity.this, TelaHomeActivity.class);
                startActivity(itTelaHome);
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePickerUtils.PICK_FOTO_FROM_AVATAR && resultCode == RESULT_OK) {
            try {
                File f = ImagePickerUtils.parseReturningDataToFile(data, this);
                Bitmap bmp = BitmapUtils.getCompressor(this).compressToBitmap(f);
                foto = bmp;
                ivFoto.setImageBitmap(bmp);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}