package com.example.app.services;

import com.example.app.classes.Diagnosticado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DiagnosticadoService {

    @GET("diagnosticado/buscar/todos")
    Call<List<Diagnosticado>> buscarTodos(@Header("Authorization") String token);

    @GET("desempenho/buscar/{EMAIL}")
    Call<Diagnosticado> buscarPorEmail(@Header("Authorization") String token, @Path("EMAIL") String email);
}
