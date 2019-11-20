package com.example.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.classes.Endereco;
import com.example.app.classes.Vinculo;
import com.example.app.enumerated.Sexo;
import com.example.app.services.VinculoService;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import java.util.List;
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
    @NotEmpty(message = "CEP é obrigatório")
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_card);
        this.inicializaComponentes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strEstados);
        this.spEstados.setAdapter(adapter);
        if(vinculo != null){
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
        this.vinculo.setEndereco(endereco);
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
        VinculoService vinculoService = TelaInicialActivity.retrofit.create(VinculoService.class);
        vinculoService.cadastrarVinculo(TelaInicialActivity.sp.getString("token", null), vinculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editar(){
        VinculoService vinculoService = TelaInicialActivity.retrofit.create(VinculoService.class);
        vinculoService.cadastrarVinculo(TelaInicialActivity.sp.getString("token", null), vinculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Edição concluída", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir(){
        TelaContatosActivity.contatosAdapter.remove(vinculo);
        TelaContatosActivity.contatosAdapter.notifyDataSetChanged();
    }
}