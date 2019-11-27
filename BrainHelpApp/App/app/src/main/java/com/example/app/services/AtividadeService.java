package com.example.app.services;

import com.example.app.classes.Atividade;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AtividadeService {

    @GET("atividade/buscar/todos")
    Call<List<Atividade>> buscarTodasAtividade(@Header("Authorization") String token);

    @GET("atividade/buscar/{ID}")
    Call<Atividade> buscarAtividadePorId(@Header("Authorization") String token, @Path("ID") Integer codAtividade);

    @POST("atividade/cadastrar")
    Call<Void> cadastrarAtividade(@Header("Authorization") String token, @Body Atividade atividade);

    @PUT("atividade/editar/{ID}")
    Call<Void> editarAtividade(@Header("Authorization") String token, @Path("ID") Integer codAtividade, @Body Atividade atividade);

    @DELETE("atividade/deletar/{ID}")
    Call<Void> deletarAtividade(@Header("Authorization") String token, @Path("ID") Integer codAtividade);
}