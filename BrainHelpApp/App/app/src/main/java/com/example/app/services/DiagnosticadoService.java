package com.example.app.services;

import com.example.app.classes.Diagnosticado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DiagnosticadoService {

    @GET("diagnosticado/buscar/todos")
    Call<List<Diagnosticado>> buscarTodos();

    @GET("desempenho/buscar/{EMAIL}")
    Call<Diagnosticado> buscarPorEmail(@Path("EMAIL") String email);
}
