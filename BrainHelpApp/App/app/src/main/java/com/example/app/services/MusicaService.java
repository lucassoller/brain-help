package com.example.app.services;

import com.example.app.classes.Musica;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MusicaService {

    @GET("musica/buscar/todos")
    Call<List<Musica>> buscarTodasMusicas(@Header("Authorization") String token);

    @GET("musica/buscar/todos/usuario")
    Call<List<Musica>> buscarTodasMusicasDoUsuario(@Header("Authorization") String token);

    @GET("musica/buscar/{ID}")
    Call<Musica> buscarMusicaPorId(@Header("Authorization") String token, @Path("ID") Integer codMusica);

    @POST("musica/cadastrar")
    Call<Void> cadastrarMusica(@Header("Authorization") String token, @Body Musica musica);

    @PUT("musica/editar")
    Call<Void> editarMusica(@Header("Authorization") String token, @Body Musica musica);

    @DELETE("musica/deletar/{ID}")
    Call<Void> deletarMusica(@Header("Authorization") String token, @Path("ID") Integer codMusica);
}