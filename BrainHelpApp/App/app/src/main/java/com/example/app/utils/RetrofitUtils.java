package com.example.app.utils;

import android.widget.Toast;

import com.example.app.activities.TelaInicialActivity;
import com.example.app.classes.Diagnosticado;
import com.example.app.services.DiagnosticadoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.15.12:8080/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create()))
            .build();

    public static void getDiagnosticado(){
        DiagnosticadoService diagnosticadoService = retrofit.create(DiagnosticadoService.class);
        diagnosticadoService.buscarLogado(TelaInicialActivity.sp.getString("token", null)).enqueue(new Callback<Diagnosticado>() {
            @Override
            public void onResponse(Call<Diagnosticado> call, Response<Diagnosticado> response) {
                String usuarioJson = new Gson().toJson(response.body());
                TelaInicialActivity.editor.putString("diagnosticado", usuarioJson);
                TelaInicialActivity.editor.commit();
            }

            @Override
            public void onFailure(Call<Diagnosticado> call, Throwable t) {

            }
        });
    }
}
