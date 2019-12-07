package com.example.app.service;

import com.example.app.model.Fotografia;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FotografiaService {

    @GET("fotografia/buscar/todos")
    Call<List<Fotografia>> buscarTodasFotografias(@Header("Authorization") String token);

    @GET("fotografia/buscar/todos/usuario")
    Call<List<Fotografia>> buscarTodasFotografiasDoUsuario(@Header("Authorization") String token);

    @GET("fotografia/buscar/{ID}")
    Call<Fotografia> buscarFotografiaPorId(@Header("Authorization") String token, @Path("ID") Integer codFotografia);

    @POST("fotografia/cadastrar")
    Call<Void> cadastrarFotografia(@Header("Authorization") String token, @Body Fotografia fotografia);

    @PUT("fotografia/editar")
    Call<Void> editarFotografia(@Header("Authorization") String token, @Body Fotografia fotografia);

    @DELETE("fotografia/deletar/{ID}")
    Call<Void> deletarFotografia(@Header("Authorization") String token, @Path("ID") Integer codFotografia);
}
