package com.example.alunoinfo.retrofitexemplo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoginService {

    @POST("public/login/medico")
    Call<LoginResponseDto> logar(@Body LoginRequestDto loginRequestDto);
}