package com.example.app.services;

import com.example.app.classes.Diagnosticado;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface RegistroService {

    @PUT("public/registro/diagnosticado/editar/foto")
    Call<Void> editarFotoDiagnosticado(@Part RequestBody file, @Field("email") String email);

    @POST("public/registro/diagnosticado")
    Call<Void> cadastrarDiagnosticado(@Body Diagnosticado diagnosticado);
}
