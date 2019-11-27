package com.example.app.services;

import com.example.app.classes.Desempenho;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DesempenhoService {

    @GET("desempenho/buscar/todos")
    Call<List<Desempenho>> buscarTodosDesempenhos(@Header("Authorization") String token);

    @GET("desempenho/buscar/{ID}")
    Call<Desempenho> buscarDesempenhoPorId(@Header("Authorization") String token, @Path("ID") Integer codDesempenho);

    @POST("desempenho/cadastrar")
    Call<Void> cadastrarDesempenho(@Header("Authorization") String token, @Body Desempenho desempenho);

    @PUT("desempenho/editar/{ID}")
    Call<Void> editarDesempenho(@Header("Authorization") String token, @Path("ID") Integer codDesempenho, @Body Desempenho desempenho);

    @DELETE("desempenho/deletar/{ID}")
    Call<Void> deletarDesempenho(@Header("Authorization") String token, @Path("ID") Integer codDesempenho);
}