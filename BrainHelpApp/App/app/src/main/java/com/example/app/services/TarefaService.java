package com.example.app.services;

import com.example.app.classes.Tarefa;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TarefaService {

    @GET("tarefa/buscar/todos")
    Call<List<Tarefa>> buscarTodasTarefas(@Header("Authorization") String token);

    @GET("tarefa/buscar/{ID}")
    Call<Tarefa> buscarTarefaPorId(@Header("Authorization") String token, @Path("ID") Integer codTarefa);

    @POST("tarefa/cadastrar")
    Call<Void> cadastrarTarefa(@Header("Authorization") String token, @Body Tarefa tarefa);

    @PUT("tarefa/editar")
    Call<Void> editarTarefa(@Header("Authorization") String token, @Body Tarefa tarefa);

    @PUT("tarefa/editar/status")
    Call<Void> editarStatusTarefa(@Header("Authorization") String token, @Body Tarefa tarefa);

    @DELETE("tarefa/deletar/{ID}")
    Call<Void> deletarTarefa(@Header("Authorization") String token, @Path("ID") Integer codTarefa);
}
