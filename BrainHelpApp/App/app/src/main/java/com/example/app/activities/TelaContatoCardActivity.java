package com.example.app.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Diagnosticado;
import com.example.app.classes.Endereco;
import com.example.app.classes.Vinculo;
import com.example.app.enumerated.Sexo;
import com.example.app.services.DiagnosticadoService;
import com.example.app.services.VinculoService;
import com.example.app.utils.BitmapUtils;
import com.example.app.utils.ImagePickerUtils;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaContatoCardActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Vinculo vinculo;
    private String [] strEstados = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MT", "MS", "PA",
            "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO"};
    @NotEmpty(message = "Nome é obrigatório")
    private EditText etNome;
    @NotEmpty(message = "Sobrenome é obrigatório")
    private EditText etSobrenome;
    @NotEmpty(message = "Vínculo é obrigatório")
    private EditText etVinculo;
    private EditText etTelefone;
    private EditText etIdade;
    @NotEmpty(message = "Lembranças são obrigatórias")
    private EditText etLembrancas;
    @NotEmpty(message = "Logradouro é obrigatório")
    private EditText etLogradouro;
    @NotEmpty(message = "Número é obrigatório")
    private EditText etNumero;
    @NotEmpty(message = "Cidade é obrigatório")
    private EditText etCidade;
    @NotEmpty(message = "Bairro é obrigatório")
    private EditText etBairro;
    private EditText etCep;
    @Checked(message = "Sexo é obrigatório")
    private RadioGroup rgSexo;
    private RadioButton rbSexoSelecionado;
    @Select(message = "Estado é obrigatório")
    private Spinner spEstados;
    private Button btCadastrar;
    private Button btExcluir;
    private Button btEditar;
    private String acao;
    private Validator validator;
    private RelativeLayout relativeLayout;
    private Bitmap foto;
    private CircleImageView ivFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_card);
        this.inicializaComponentes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strEstados);
        this.spEstados.setAdapter(adapter);
        if(vinculo != null){
            if(vinculo.getFoto() != null && !vinculo.getFoto().isEmpty()){
                ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(vinculo.getFoto()));
            }
            this.etNome.setText(this.vinculo.getNome());
            this.etSobrenome.setText(this.vinculo.getSobrenome());
            this.etVinculo.setText(this.vinculo.getVinculo());
            this.etTelefone.setText(this.vinculo.getTelefone());
            this.etIdade.setText(String.valueOf(this.vinculo.getIdade()));
            this.etLembrancas.setText(this.vinculo.getLembrancas());
            this.etLogradouro.setText(this.vinculo.getEndereco().getLogradouro());
            this.etNumero.setText(String.valueOf(this.vinculo.getEndereco().getNumero()));
            this.etCidade.setText(this.vinculo.getEndereco().getCidade());
            this.etBairro.setText(this.vinculo.getEndereco().getBairro());
            this.etCep.setText(this.vinculo.getEndereco().getCep());
            if(this.vinculo.getSexo().equals(Sexo.M)){
                rbSexoSelecionado = findViewById(R.id.rb_masculino);
                rbSexoSelecionado.setChecked(true);
            }else {
                rbSexoSelecionado = findViewById(R.id.rb_feminino);
                rbSexoSelecionado.setChecked(true);
            }
            for(int i = 0; i < strEstados.length; i++){
                if(this.vinculo.getEndereco().getEstado().equals(strEstados[i])){
                    this.spEstados.setSelection(i);
                }
            }
            this.acao = "editar";
            relativeLayout.removeView(this.btCadastrar);
        }else{
            this.vinculo = new Vinculo();
            this.rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    rbSexoSelecionado = findViewById(checkedId);
                }
            });
            this.acao = "cadastrar";
            relativeLayout.removeView(this.btEditar);
            relativeLayout.removeView(this.btExcluir);
        }
        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        this.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        this.btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluir();
            }
        });

        this.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = ImagePickerUtils.getPickImageIntent(TelaContatoCardActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });
    }

    private void inicializaComponentes() {
        this.vinculo = (Vinculo) getIntent().getSerializableExtra("vinculo");
        this.etNome = findViewById(R.id.et_nome);
        this.etSobrenome = findViewById(R.id.et_sobrenome);
        this.etVinculo = findViewById(R.id.et_vinculo);
        this.etTelefone = findViewById(R.id.et_telefone);
        this.etIdade = findViewById(R.id.et_idade);
        this.etLembrancas = findViewById(R.id.et_lembrancas);
        this.etLogradouro = findViewById(R.id.et_logradouro);
        this.etNumero = findViewById(R.id.et_numero);
        this.etCidade = findViewById(R.id.et_cidade);
        this.etBairro = findViewById(R.id.et_bairro);
        this.etCep = findViewById(R.id.et_cep);
        this.rgSexo = findViewById(R.id.rg_sexo);
        this.spEstados = findViewById(R.id.sp_estados);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);
        this.btEditar = findViewById(R.id.bt_editar);
        this.btExcluir = findViewById(R.id.bt_excluir);
        this.validator = new Validator(this);
        this.relativeLayout = findViewById(R.id.layout);
        this.ivFoto = findViewById(R.id.iv_foto);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        this.vinculo.setNome(this.etNome.getText().toString());
        this.vinculo.setSobrenome(this.etSobrenome.getText().toString());
        this.vinculo.setVinculo(this.etVinculo.getText().toString());
        this.vinculo.setTelefone(this.etTelefone.getText().toString());
        this.vinculo.setIdade(Integer.parseInt(this.etIdade.getText().toString()));
        if(this.rbSexoSelecionado.getText().toString().equals("Masculino")){
            this.vinculo.setSexo(Sexo.M);
        }else{
            this.vinculo.setSexo(Sexo.F);
        }
        this.vinculo.setLembrancas(this.etLembrancas.getText().toString());
        Endereco endereco;
        if(this.vinculo.getEndereco() == null){
            endereco = new Endereco();
        }else{
            endereco = this.vinculo.getEndereco();
        }
        endereco.setLogradouro(this.etLogradouro.getText().toString());
        endereco.setNumero(Integer.parseInt(this.etNumero.getText().toString()));
        endereco.setEstado(this.spEstados.getSelectedItem().toString());
        endereco.setCidade(this.etCidade.getText().toString());
        endereco.setBairro(this.etBairro.getText().toString());
        endereco.setCep(this.etCep.getText().toString());
        endereco.setTitulo("Endereço de "+ vinculo.getNome());
        this.vinculo.setEndereco(endereco);
        if(foto != null){
            vinculo.setFoto(BitmapUtils.bitmapToBase64(foto));
        }

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
        VinculoService vinculoService = RetrofitUtils.retrofit.create(VinculoService.class);
        vinculoService.cadastrarVinculo(TelaInicialActivity.sp.getString("token", null), vinculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaContatoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editar(){
        VinculoService vinculoService = RetrofitUtils.retrofit.create(VinculoService.class);
        vinculoService.editarVinculo(TelaInicialActivity.sp.getString("token", null), vinculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Edição concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaContatoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir(){
        VinculoService vinculoService = RetrofitUtils.retrofit.create(VinculoService.class);
        vinculoService.deletarVinculo(TelaInicialActivity.sp.getString("token", null), vinculo.getCodVinculo()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Exclusão concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaContatoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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