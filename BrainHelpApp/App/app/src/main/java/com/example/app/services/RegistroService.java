package com.example.app.services;

import com.example.app.classes.Medico;
import com.example.app.classes.dto.RedefinicaoSenhaRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RegistroService {

    @POST("public/registro/medico")
    Call<Medico> cadastrarMedico(@Body Medico medico);

    @PUT("public/registro/medico/editar/senha")
    Call<RedefinicaoSenhaRequestDto> editarMedico(@Body RedefinicaoSenhaRequestDto redefinicao);


}
