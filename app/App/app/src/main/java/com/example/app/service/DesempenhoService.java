package com.example.app.service;

import com.example.app.model.Desempenho;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DesempenhoService {


    @GET("desempenho/buscar/todos")
    Call<List<Desempenho>> buscarTodosDesempenhos(@Header("Authorization") String token);

    @GET("desempenho/buscar/todos/usuario")
    Call<List<Desempenho>> buscarTodosDesempenhosDoUsuario(@Header("Authorization") String token);

    @GET("desempenho/buscar/{ID}")
    Call<Desempenho> buscarDesempenhoPorId(@Header("Authorization") String token, @Path("ID") Integer codDesempenho);

    @POST("desempenho/cadastrar")
    Call<Void> cadastrarDesempenho(@Header("Authorization") String token, @Body Desempenho desempenho);

    @DELETE("desempenho/deletar/{ID}")
    Call<Void> deletarDesempenho(@Header("Authorization") String token, @Path("ID") Integer codDesempenho);
}