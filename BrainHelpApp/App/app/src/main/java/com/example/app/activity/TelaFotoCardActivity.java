package com.example.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.model.Fotografia;
import com.example.app.service.FotografiaService;
import com.example.app.util.BitmapUtils;
import com.example.app.util.ImagePickerUtils;
import com.example.app.util.MyErrorMessage;
import com.example.app.util.RetrofitUtils;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaFotoCardActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Fotografia fotografia;
    @NotEmpty(message = "Lugar é obrigatório")
    private EditText etLugar;
    @NotEmpty(message = "Data é obrigatório")
    private EditText etData;
    @NotEmpty(message = "Lembraças é obrigatório")
    private EditText etLembrancas;
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
        setContentView(R.layout.activity_tela_foto_card);
        this.inicializaComponentes();
        if(fotografia != null){
            if(fotografia.getFoto() != null && !fotografia.getFoto().isEmpty()){
                ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(fotografia.getFoto()));
            }
            this.etLugar.setText(fotografia.getLugar());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            String data = dateFormat.format(fotografia.getData());
            this.etData.setText(data);
            this.etLembrancas.setText(fotografia.getLembrancas());
            this.acao = "editar";
            relativeLayout.removeView(this.btCadastrar);
        }else{
            this.fotografia = new Fotografia();
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
                Intent i = ImagePickerUtils.getPickImageIntent(TelaFotoCardActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });
    }

    private void inicializaComponentes() {
        this.fotografia = (Fotografia) getIntent().getSerializableExtra("fotografia");
        this.etLugar = findViewById(R.id.et_lugar);
        this.etData = findViewById(R.id.et_data);
        this.etLembrancas = findViewById(R.id.et_lembrancas);
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
        fotografia.setLugar(etLugar.getText().toString());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fotografia.setData(format.parse(etData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fotografia.setLembrancas(etLembrancas.getText().toString());
        if(foto != null){
            fotografia.setFoto(BitmapUtils.bitmapToBase64(foto));
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
        FotografiaService fotografiaService = RetrofitUtils.retrofit.create(FotografiaService.class);
        fotografiaService.cadastrarFotografia(TelaInicialActivity.sp.getString("token", null), fotografia).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro concluído", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaFotoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editar(){
        FotografiaService fotografiaService = RetrofitUtils.retrofit.create(FotografiaService.class);
        fotografiaService.editarFotografia(TelaInicialActivity.sp.getString("token", null), fotografia).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Edição concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaFotoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void excluir(){
        FotografiaService fotografiaService = RetrofitUtils.retrofit.create(FotografiaService.class);
        fotografiaService.deletarFotografia(TelaInicialActivity.sp.getString("token", null), fotografia.getCodFotografia()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Exclusão concluída", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(TelaFotoCardActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
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
                Intent itTelaUsuario = new Intent(TelaFotoCardActivity.this, TelaPerfilActivity.class);
                itTelaUsuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaUsuario);
                return true;
            case R.id.alterar_senha:
                Intent itTelaSenha = new Intent(TelaFotoCardActivity.this, TelaFotoCardActivity.class);
                itTelaSenha.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaSenha);
                return true;
            case R.id.desempenhos:
                Intent itTelaDesempenho = new Intent(TelaFotoCardActivity.this, TelaDesempenhosActivity.class);
                itTelaDesempenho.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaDesempenho);
                return true;
            case R.id.sair:
                TelaInicialActivity.editor.remove("token");
                TelaInicialActivity.editor.remove("email");
                TelaInicialActivity.editor.commit();

                Intent itTelaInicial = new Intent(TelaFotoCardActivity.this, TelaInicialActivity.class);
                itTelaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(itTelaInicial);
                finish();
                return true;
        }
        return false;
    }
}