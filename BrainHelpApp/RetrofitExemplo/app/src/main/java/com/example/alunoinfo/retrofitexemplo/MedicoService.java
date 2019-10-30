package com.example.alunoinfo.retrofitexemplo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MedicoService {

    @GET("medico/teste")
    Call<List<Medico>> listarMedicos(@Header("Authorization") String token);
}
