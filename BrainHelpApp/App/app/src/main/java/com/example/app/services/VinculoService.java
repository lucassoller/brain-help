package com.example.app.services;

import com.example.app.classes.Vinculo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VinculoService {

    @GET("vinculo/buscar/todos")
    Call<List<Vinculo>> buscarTodos();

    @GET("vinculo/buscar/{ID}")
    Call<Vinculo> buscarPorId(@Path("ID") Integer codVinculo);

    @POST("vinculo/cadastrar")
    Call<Vinculo> cadastrarVinculo(@Body Vinculo vinculo);
}
