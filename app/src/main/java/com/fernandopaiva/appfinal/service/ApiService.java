package com.fernandopaiva.appfinal.service;

import com.fernandopaiva.appfinal.model.Feedback;
import com.fernandopaiva.appfinal.model.Atividade;
import com.fernandopaiva.appfinal.model.ItemCardapio;
import com.fernandopaiva.appfinal.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/feedbacks")
    Call<List<Feedback>> getFeedbacks(@Query("status") String status);

    @PUT("/feedback/{id}")
    Call<Feedback> updateFeedback(@Path("id") String id, @Body Feedback feedback);

    @POST("/atividades")
    Call<Atividade> createAtividade(@Body Atividade atividade);

    @GET("/atividades")
    Call<List<Atividade>> getAtividades(@Query("status") String status);

    @PUT("/atividades/{id}")
    Call<Atividade> updateAtividade(@Path("id") String id, @Body Atividade atividade);

    @POST("/itemcardapio")
    Call<ItemCardapio> createItemCardapio(@Body ItemCardapio itemCardapio);

    @GET("/itemcardapio")
    Call<List<ItemCardapio>> getItemCardapio();

    @POST("/usuarios")
    Call<Usuario> createUsuario(@Body Usuario usuario);

    @GET("/usuarios/{loginUsuario}")
    Call<Usuario> getUsuario(@Path("loginUsuario") String loginUsuario);

    @DELETE("/usuarios/{loginUsuario}")
    Call<Void> deleteUsuario(@Path("loginUsuario") String loginUsuario);
}
