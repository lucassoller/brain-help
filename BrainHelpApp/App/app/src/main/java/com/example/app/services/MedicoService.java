package com.example.app.services;

import com.example.app.classes.Medico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MedicoService {

    @GET("medico/buscar/todos")
    Call<List<Medico>> buscarTodos();

    @GET("medico/buscar/{EMAIL}")
    Call<Medico> buscarPorEmail(@Path("EMAIL") String email);
}
