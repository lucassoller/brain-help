package com.example.app.services;

import com.example.app.classes.Desempenho;
import com.example.app.classes.Endereco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EnderecoService {

    @GET("endereco/buscar/todos")
    Call<List<Endereco>> buscarTodos();

    @GET("endereco/buscar/{ID}")
    Call<Endereco> buscarPorId(@Path("ID") Integer codEndereco);

    @POST("endereco/cadastrar")
    Call<Endereco> cadastrarEndereco(@Body Endereco endereco);
}
