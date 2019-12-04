package com.example.app.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Endereco;
import com.example.app.services.EnderecoService;
import com.example.app.utils.BitmapUtils;
import com.example.app.utils.ImagePickerUtils;
import com.example.app.utils.RetrofitUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import java.io.File;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaEnderecoCardActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Endereco endereco;
    private String [] strEstados = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MT", "MS", "PA",
            "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO"};
    @NotEmpty(message = "Título é obrigatório")
    private EditText etTitulo;
    @NotEmpty(message = "Logradouro é obrigatório")
    private EditText etLogradouro;
    @NotEmpty(message = "Número é obrigatório")
    private EditText etNumero;
    @NotEmpty(message = "Cidade é obrigatório")
    private EditText etCidade;
    @NotEmpty(message = "Bairro é obrigatório")
    private EditText etBairro;
    private EditText etCep;
    @Select(message = "Estado é obrigatório")
    private Spinner spEstados;
    private Button btCadastrar;
    private Button btExcluir;
    private Button btEditar;
    private String acao;
    private Validator validator;
    private RelativeLayout relativeLayout;
    private Bitmap foto;
    private ImageView ivFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_endereco_card);
        this.inicializaComponentes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strEstados);
        this.spEstados.setAdapter(adapter);
        if(endereco != null){
            if(endereco.getFoto() != null && !endereco.getFoto().isEmpty()){
                ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(endereco.getFoto()));
            }
            this.etLogradouro.setText(this.endereco.getLogradouro());
            this.etNumero.setText(String.valueOf(this.endereco.getNumero()));
            this.etCidade.setText(this.endereco.getCidade());
            this.etBairro.setText(this.endereco.getBairro());
            this.etCep.setText(this.endereco.getCep());

            for(int i = 0; i < strEstados.length; i++){
                if(this.endereco.getEstado().equals(strEstados[i])){
                    this.spEstados.setSelection(i);
                }
            }
            this.acao = "editar";
            relativeLayout.removeView(this.btCadastrar);
        }else{
            this.endereco = new Endereco();
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
                Intent i = ImagePickerUtils.getPickImageIntent(TelaEnderecoCardActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });
    }

    private void inicializaComponentes() {
        this.endereco = (Endereco) getIntent().getSerializableExtra("endereco");
        this.etTitulo = findViewById(R.id.et_titulo);
        this.etLogradouro = findViewById(R.id.et_logradouro);
        this.etNumero = findViewById(R.id.et_numero);
        this.etCidade = findViewById(R.id.et_cidade);
        this.etBairro = findViewById(R.id.et_bairro);
        this.etCep = findViewById(R.id.et_cep);
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
        endereco.setLogradouro(this.etLogradouro.getText().toString());
        endereco.setNumero(Integer.parseInt(this.etNumero.getText().toString()));
        endereco.setEstado(this.spEstados.getSelectedItem().toString());
        endereco.setCidade(this.etCidade.getText().toString());
        endereco.setBairro(this.etBairro.getText().toString());
        endereco.setCep(this.etCep.getText().toString());
        endereco.setTitulo(this.etTitulo.getText().toString());
        if(foto != null){
            endereco.setFoto(BitmapUtils.bitmapToBase64(foto));
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
        EnderecoService enderecoService = RetrofitUtils.retrofit.create(EnderecoService.class);
        enderecoService.cadastrarEndereco(TelaInicialActivity.sp.getString("token", null), endereco).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editar(){
        EnderecoService enderecoService = RetrofitUtils.retrofit.create(EnderecoService.class);
        enderecoService.editarEndereco(TelaInicialActivity.sp.getString("token", null), endereco).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Edição concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir(){
        EnderecoService enderecoService = RetrofitUtils.retrofit.create(EnderecoService.class);
        enderecoService.deletarEndereco(TelaInicialActivity.sp.getString("token", null), endereco.getCodEndereco()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Exclusão concluída", Toast.LENGTH_LONG).show();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
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