package com.example.app.service;

import com.example.app.model.Medicamento;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MedicamentoService {

    @GET("medicamento/buscar/todos")
    Call<List<Medicamento>> buscarTodosMedicamentos(@Header("Authorization") String token);

    @GET("medicamento/buscar/todos/usuario")
    Call<List<Medicamento>> buscarTodosMedicamentosDoUsuario(@Header("Authorization") String token);

    @GET("medicamento/buscar/{ID}")
    Call<Medicamento> buscarMedicamentoPorId(@Header("Authorization") String token, @Path("ID") Integer codMedicamento);

    @POST("medicamento/cadastrar")
    Call<Void> cadastrarMedicamento(@Header("Authorization") String token, @Body Medicamento medicamento);

    @PUT("medicamento/editar")
    Call<Void> editarMedicamento(@Header("Authorization") String token, @Body Medicamento medicamento);

    @DELETE("medicamento/deletar/{ID}")
    Call<Void> deletarMedicamento(@Header("Authorization") String token, @Path("ID") Integer codMedicamento);
}
