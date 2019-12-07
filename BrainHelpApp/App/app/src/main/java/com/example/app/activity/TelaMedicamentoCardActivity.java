package com.example.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.app.model.Medicamento;
import com.example.app.model.enumerated.Frequencia;
import com.example.app.model.enumerated.Medida;
import com.example.app.service.MedicamentoService;
import com.example.app.util.AlarmSender;
import com.example.app.util.BitmapUtils;
import com.example.app.util.ImagePickerUtils;
import com.example.app.util.MyErrorMessage;
import com.example.app.util.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaMedicamentoCardActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Medicamento medicamento;
    private String [] strMedidas = {"ML", "L", "MG", "G", "Gotas", "Comprimidos"};
    private String [] strFrequencias = {"4 em 4 horas", "6 em 6 horas", "8 em 8 horas", "12 em 12 horas", "diária", "semanal", "mensal"};
    @NotEmpty(message = "Nome do medicamento é obrigatório")
    private EditText etNomeMedicamento;
    @NotEmpty(message = "Função é obrigatório")
    private EditText etFuncao;
    private EditText etContraindicacoes;
    private EditText etEfeitosColaterais;
    @NotEmpty(message = "Quantidade é obrigatório")
    private EditText etQuantidade;
    @NotEmpty(message = "Horário é obrigatório")
    private EditText etHorario;
    @Select(message = "Medida é obrigatório")
    private Spinner spMedidas;
    @Select(message = "Frequência é obrigatório")
    private Spinner spFrequencias;
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
        setContentView(R.layout.activity_tela_medicamento_card);
        this.inicializaComponentes();

        ArrayAdapter<String> adapterMedidas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strMedidas);
        this.spMedidas.setAdapter(adapterMedidas);

        ArrayAdapter<String> adapterFrequencias = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strFrequencias);
        this.spFrequencias.setAdapter(adapterFrequencias);

        if(medicamento != null){
            if(medicamento.getFoto() != null && !medicamento.getFoto().isEmpty()){
                ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(medicamento.getFoto()));
            }
            this.etNomeMedicamento.setText(medicamento.getNomeMedicamento());
            this.etFuncao.setText(medicamento.getFuncao());
            this.etContraindicacoes.setText(medicamento.getContraIndicacoes());
            this.etEfeitosColaterais.setText(medicamento.getEfeitosColaterais());
            this.etQuantidade.setText(String.valueOf(medicamento.getQuantidade()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", new Locale("pt", "BR"));
            String horario = dateFormat.format(medicamento.getHorario());
            this.etHorario.setText(horario);

            switch (medicamento.getMedida()){
                case ML:
                    this.spMedidas.setSelection(0);
                    break;
                case L:
                    this.spMedidas.setSelection(1);
                    break;
                case MG:
                    this.spMedidas.setSelection(2);
                    break;
                case G:
                    this.spMedidas.setSelection(3);
                    break;
                case GOTAS:
                    this.spMedidas.setSelection(4);
                    break;
                case COMPRIMIDOS:
                    this.spMedidas.setSelection(5);
                    break;
            }

            switch (medicamento.getFrequencia()){
                case QUATRO:
                    this.spFrequencias.setSelection(0);
                    break;
                case SEIS:
                    this.spFrequencias.setSelection(1);
                    break;
                case OITO:
                    this.spFrequencias.setSelection(2);
                    break;
                case DOZE:
                    this.spFrequencias.setSelection(3);
                    break;
                case DIARIA:
                    this.spFrequencias.setSelection(4);
                    break;
                case SEMANAL:
                    this.spFrequencias.setSelection(5);
                    break;
                case MENSAL:
                    this.spFrequencias.setSelection(6);
                    break;
            }

            this.acao = "editar";
            relativeLayout.removeView(this.btCadastrar);
        }else{
            this.medicamento = new Medicamento();
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
                Intent i = ImagePickerUtils.getPickImageIntent(TelaMedicamentoCardActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });
    }

    private void inicializaComponentes() {
        this.medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");
        this.etNomeMedicamento = findViewById(R.id.et_nome_medicamento);
        this.etFuncao = findViewById(R.id.et_funcao);
        this.etContraindicacoes = findViewById(R.id.et_contraindicacoes);
        this.etEfeitosColaterais = findViewById(R.id.et_efeitos_colaterais);
        this.etQuantidade = findViewById(R.id.et_quantidade);
        this.etHorario = findViewById(R.id.et_horario);
        this.spMedidas = findViewById(R.id.sp_medidas);
        this.spFrequencias = findViewById(R.id.sp_frequencias);
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
        medicamento.setNomeMedicamento(etNomeMedicamento.getText().toString());
        medicamento.setFuncao(etFuncao.getText().toString());
        medicamento.setContraIndicacoes(etContraindicacoes.getText().toString());
        medicamento.setEfeitosColaterais(etEfeitosColaterais.getText().toString());
        medicamento.setQuantidade(Integer.parseInt(etQuantidade.getText().toString()));

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            medicamento.setHorario(format.parse(etHorario.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        switch (this.spMedidas.getSelectedItemPosition()){
            case 0:
                medicamento.setMedida(Medida.ML);
                break;
            case 1:
                medicamento.setMedida(Medida.L);
                break;
            case 2:
                medicamento.setMedida(Medida.MG);
                break;
            case 3:
                medicamento.setMedida(Medida.G);
                break;
            case 4:
                medicamento.setMedida(Medida.GOTAS);
                break;
            case 5:
                medicamento.setMedida(Medida.COMPRIMIDOS);
                break;
        }

        switch (this.spFrequencias.getSelectedItemPosition()){
            case 0:
                medicamento.setFrequencia(Frequencia.QUATRO);
                break;
            case 1:
                medicamento.setFrequencia(Frequencia.SEIS);
                break;
            case 2:
                medicamento.setFrequencia(Frequencia.OITO);
                break;
            case 3:
                medicamento.setFrequencia(Frequencia.DOZE);
                break;
            case 4:
                medicamento.setFrequencia(Frequencia.DIARIA);
                break;
            case 5:
                medicamento.setFrequencia(Frequencia.SEMANAL);
                break;
            case 6:
                medicamento.setFrequencia(Frequencia.MENSAL);
                break;
        }

        if(foto != null){
            medicamento.setFoto(BitmapUtils.bitmapToBase64(foto));
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
        MedicamentoService medicamentoService = RetrofitUtils.retrofit.create(MedicamentoService.class);
        medicamentoService.cadastrarMedicamento(TelaInicialActivity.sp.getString("token", null), medicamento).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                    AlarmSender.agendarMedicamento(TelaMedicamentoCardActivity.this, medicamento);
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaMedicamentoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editar(){
        MedicamentoService medicamentoService = RetrofitUtils.retrofit.create(MedicamentoService.class);
        medicamentoService.editarMedicamento(TelaInicialActivity.sp.getString("token", null), medicamento).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Edição concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaMedicamentoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir(){
        MedicamentoService medicamentoService = RetrofitUtils.retrofit.create(MedicamentoService.class);
        medicamentoService.deletarMedicamento(TelaInicialActivity.sp.getString("token", null), medicamento.getCodMedicamento()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Exclusão concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaMedicamentoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent itTelaUsuario = new Intent(TelaMedicamentoCardActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaMedicamentoCardActivity.this, TelaAlterarSenhaActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaMedicamentoCardActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaMedicamentoCardActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}