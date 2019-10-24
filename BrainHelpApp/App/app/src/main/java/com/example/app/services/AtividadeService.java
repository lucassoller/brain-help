package com.example.app.services;

import com.example.app.classes.Atividade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AtividadeService {

    @GET("atividade/buscar/todos")
    Call<List<Atividade>> buscarTodos();

    @GET("atividade/buscar/{ID}")
    Call<Atividade> buscarPorId(@Path("ID") Integer codAtividade);

    @POST("atividade/cadastrar")
    Call<Atividade> cadastrar(@Body Atividade atividade);

}
