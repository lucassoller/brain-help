package com.example.app.services;

import com.example.app.classes.Vinculo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VinculoService {

    @GET("vinculo/buscar/todos")
    Call<List<Vinculo>> buscarTodosVinculos(@Header("Authorization") String token);

    @GET("vinculo/buscar/todos/usuario")
    Call<List<Vinculo>> buscarTodosVinculosDoUsuario(@Header("Authorization") String token);

    @GET("vinculo/buscar/{ID}")
    Call<Vinculo> buscarVinculoPorId(@Header("Authorization") String token, @Path("ID") Integer codVinculo);

    @POST("vinculo/cadastrar")
    Call<Void> cadastrarVinculo(@Header("Authorization") String token, @Body Vinculo vinculo);

    @PUT("vinculo/editar/{ID}")
    Call<Void> editarVinculo(@Header("Authorization") String token, @Path("ID") Integer codVinculo, @Body Vinculo vinculo);

    @DELETE("vinculo/deletar/{ID}")
    Call<Void> deletarVinculo(@Header("Authorization") String token, @Path("ID") Integer codVinculo);
}