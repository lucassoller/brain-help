package com.example.app.service;

import com.example.app.model.dto.LoginRequestDto;
import com.example.app.model.dto.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("public/login/diagnosticado")
    Call<LoginResponseDto> logarDiagnosticado(@Body LoginRequestDto loginDto);
}
