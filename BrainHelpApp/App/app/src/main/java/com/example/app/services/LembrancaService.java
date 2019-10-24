package com.example.app.services;

import com.example.app.classes.Lembranca;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LembrancaService {

    @GET("lembranca/buscar/todos")
    Call<List<Lembranca>> buscarTodos();

    @GET("lembranca/buscar/{ID}")
    Call<Lembranca> buscarPorId(@Path("ID") Integer codLembranca);

    @POST("lembranca/cadastrar")
    Call<Lembranca> cadastrarLembranca(@Body Lembranca lembranca);
}
