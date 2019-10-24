package com.example.app.services;

import com.example.app.classes.Desempenho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DesempenhoService {

    @GET("desempenho/buscar/todos")
    Call<List<Desempenho>> buscarTodos();

    @GET("desempenho/buscar/{ID}")
    Call<Desempenho> buscarPorId(@Path("ID") Integer codDesempenho);

    //conferir
    @POST("desempenho/cadastrar")
    Call<Desempenho> cadastrarDesempenho(@Body Integer codDesempenho);
}
