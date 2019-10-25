package com.example.app.services;

import com.example.app.classes.dto.LoginRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("public/login/medico")
    Call<LoginRequestDto> logarMedico(@Body LoginRequestDto loginDto);

    @POST("public/login/medico/google")
    Call<LoginRequestDto> logarMedicoGoogle(@Body LoginRequestDto loginDto);

    @POST("public/login/diagnosticado")
    Call<LoginRequestDto> logarDiagnosticado(@Body LoginRequestDto loginDto);

}
