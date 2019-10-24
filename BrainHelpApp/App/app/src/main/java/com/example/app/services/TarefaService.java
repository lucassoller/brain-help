package com.example.app.services;

import com.example.app.classes.Tarefa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TarefaService {

    @GET("tarefa/buscar/todos")
    Call<List<Tarefa>> buscarTodos();

    @GET("tarefa/buscar/{ID}")
    Call<Tarefa> buscarPorId(@Path("ID") Integer codTarefa);

    @POST("tarefa/cadastrar")
    Call<Tarefa> cadastrarTarefa(@Body Tarefa tarefa);
}
