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
import android.widget.Toast;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.classes.Endereco;
import com.example.app.enumerated.EstagioAlzheimer;
import com.example.app.enumerated.Sexo;
import com.example.app.services.DiagnosticadoService;
import com.example.app.utils.BitmapUtils;
import com.example.app.utils.ImagePickerUtils;
import com.example.app.utils.RetrofitUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.io.File;
import java.util.Date;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPerfilActivity extends AppCompatActivity  implements Validator.ValidationListener{

    @NotEmpty(message = "Nome é obrigatório")
    private EditText etNome;
    @NotEmpty(message = "Sobrenome é obrigatório")
    private EditText etSobrenome;
    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email incorreto")
    private EditText etEmail;
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
    @NotEmpty(message = "CEP é obrigatório")
    private EditText etCep;
    private Button btEditar;
    private Button btExcluir;
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
        setContentView(R.layout.activity_tela_edicao);
        this.inicializaComponentes();
        this.inicializaEditText();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strEstados);
        this.spEstados.setAdapter(adapter);

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
                Intent i = ImagePickerUtils.getPickImageIntent(TelaPerfilActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });
    }

    private void inicializaComponentes(){
        this.diagnosticado = (Diagnosticado) getIntent().getSerializableExtra("diagnosticado");
        this.etNome = findViewById(R.id.et_nome);
        this.etSobrenome = findViewById(R.id.et_sobrenome);
        this.etEmail = findViewById(R.id.et_email);
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
        this.btEditar = findViewById(R.id.bt_editar);
        this.btExcluir = findViewById(R.id.bt_excluir);
        this.spEstados = findViewById(R.id.sp_estados);
        this.ivFoto = findViewById(R.id.iv_foto);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void inicializaEditText(){
        this.etNome.setText(diagnosticado.getNome());
        this.etSobrenome.setText(diagnosticado.getSobrenome());
        this.etEmail.setText(diagnosticado.getEmail());
        this.etIdade.setText(String.valueOf(diagnosticado.getIdade()));
        this.etTelefone.setText(diagnosticado.getTelefone());
        this.etDataDiagnostico.setText(String.valueOf(diagnosticado.getDataDiagnostico()));
        this.etChaveSeguranca.setText(diagnosticado.getChaveSeguranca());
        if(this.diagnosticado.getSexo().equals(Sexo.M)){
            rbSexoSelecionado = findViewById(R.id.rb_masculino);
            rbSexoSelecionado.setChecked(true);
        }else {
            rbSexoSelecionado = findViewById(R.id.rb_feminino);
            rbSexoSelecionado.setChecked(true);
        }
        for(int i = 0; i < strEstados.length; i++){
            if(this.diagnosticado.getEndereco().getEstado().equals(strEstados[i])){
                this.spEstados.setSelection(i);
            }
        }
        if(diagnosticado.getEstagioAlzheimer().equals(EstagioAlzheimer.INICIAL)){
            rbSexoSelecionado = findViewById(R.id.rb_inicial);
            rbSexoSelecionado.setChecked(true);
        }else if (diagnosticado.getEstagioAlzheimer().equals(EstagioAlzheimer.INTERMEDIARIO)){
            rbSexoSelecionado = findViewById(R.id.rb_intermediario);
            rbSexoSelecionado.setChecked(true);
        }else{
            rbSexoSelecionado = findViewById(R.id.rb_avancado);
            rbSexoSelecionado.setChecked(true);
        }
        this.etLogradouro.setText(diagnosticado.getEndereco().getLogradouro());
        this.etNumero.setText(String.valueOf(diagnosticado.getEndereco().getNumero()));
        this.etCidade.setText(diagnosticado.getEndereco().getCidade());
        this.etBairro.setText(diagnosticado.getEndereco().getBairro());
        this.etCep.setText(diagnosticado.getEndereco().getCep());
    }

    @Override
    public void onValidationSucceeded() {
        diagnosticado.setNome(etNome.getText().toString());
        diagnosticado.setSobrenome(etSobrenome.getText().toString());
        diagnosticado.setEmail(etEmail.getText().toString());
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
        editar();
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


    private void editar(){
        DiagnosticadoService diagnosticadoService = RetrofitUtils.retrofit.create(DiagnosticadoService.class);
        diagnosticadoService.editarDiagnosticado(TelaInicialActivity.sp.getString("token", null), diagnosticado).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TelaPerfilActivity.this, "Edição concluída!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir(){
        DiagnosticadoService diagnosticadoService = RetrofitUtils.retrofit.create(DiagnosticadoService.class);
        diagnosticadoService.deletarDiagnosticado(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TelaPerfilActivity.this, "Exclusão concluída!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}