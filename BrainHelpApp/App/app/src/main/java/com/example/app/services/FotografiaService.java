package com.example.app.services;

import com.example.app.classes.Fotografia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FotografiaService {

    @GET("fotografia/buscar/todos")
    Call<List<Fotografia>> buscarTodos();

    @GET("fotografia/buscar/{ID}")
    Call<Fotografia> buscarPorId(@Path("ID") Integer codFotografia);

    @POST("atividade/cadastrar")
    Call<Fotografia> cadastrarFotografia(@Body Fotografia fotografia);
}
