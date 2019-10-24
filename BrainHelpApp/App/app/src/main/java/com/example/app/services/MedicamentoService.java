package com.example.app.services;

import com.example.app.classes.Medicamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MedicamentoService {

    @GET("medicamento/buscar/todos")
    Call<List<Medicamento>> buscarTodos();

    @GET("medicamento/buscar/{ID}")
    Call<Medicamento> buscarPorId(@Path("ID") Integer codMedicamento);

    @POST("medicamento/cadastrar")
    Call<Medicamento> cadastrarMedicamento(@Body Medicamento medicamento);
}
