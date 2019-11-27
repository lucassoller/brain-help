package com.example.app.services;


import com.example.app.classes.Endereco;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnderecoService {

    @GET("endereco/buscar/todos")
    Call<List<Endereco>> buscarTodosEnderecos(@Header("Authorization") String token);

    @GET("endereco/buscar/{ID}")
    Call<Endereco> buscarEnderecoPorId(@Header("Authorization") String token, @Path("ID") Integer codEndereco);

    @POST("endereco/cadastrar")
    Call<Void> cadastrarEndereco(@Header("Authorization") String token, @Body Endereco endereco);

    @PUT("endereco/editar/{ID}")
    Call<Void> editarEndereco(@Header("Authorization") String token, @Path("ID") Integer codEndereco, @Body Endereco endereco);

    @DELETE("endereco/deletar/{ID}")
    Call<Void> deletarEndereco(@Header("Authorization") String token, @Path("ID") Integer codEndereco);
}
