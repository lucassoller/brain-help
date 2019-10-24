package com.example.app.services;

import com.example.app.classes.Musica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MusicaService {

    @GET("musica/buscar/todos")
    Call<List<Musica>> buscarTodos();

    @GET("musica/buscar/{ID}")
    Call<Musica> buscarPorId(@Path("ID") Integer codMusica);

    @POST("musica/cadastrar")
    Call<Musica> cadastrarMusica(@Body Musica musica);
}
