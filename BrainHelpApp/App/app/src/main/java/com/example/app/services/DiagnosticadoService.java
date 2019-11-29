package com.example.app.services;

import com.example.app.classes.Diagnosticado;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface DiagnosticadoService {


    @GET("diagnosticado/buscar/todos")
    Call<List<Diagnosticado>> buscarTodos(@Header("Authorization") String token);

    @GET("diagnosticado/buscar/{EMAIL}")
    Call<Diagnosticado> buscarPorEmail(@Header("Authorization") String token, @Path("EMAIL") String email);

    @GET("diagnosticado/buscar/logado")
    Call<Diagnosticado> buscarLogado(@Header("Authorization") String token);

    @POST("public/registro/diagnosticado")
    Call<Void> cadastrarDiagnosticado(@Body Diagnosticado diagnosticado);

    @PUT("diagnosticado/editar")
    Call<Void> editarDiagnosticado(@Header("Authorization") String token, @Body Diagnosticado diagnosticado);

    @DELETE("diagnosticado/deletar")
    Call<Void> deletarDiagnosticado(@Header("Authorization") String token);
}