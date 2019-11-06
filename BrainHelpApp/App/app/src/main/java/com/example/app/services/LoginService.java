package com.example.app.services;

import com.example.app.classes.dto.LoginRequestDto;
import com.example.app.classes.dto.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("public/login/medico")
    Call<LoginResponseDto> logarMedico(@Body LoginRequestDto loginDto);

    @POST("public/login/medico/google")
    Call<LoginResponseDto> logarMedicoGoogle(@Body LoginRequestDto loginDto);

    @POST("public/login/diagnosticado")
    Call<LoginResponseDto> logarDiagnosticado(@Body LoginRequestDto loginDto);

}
