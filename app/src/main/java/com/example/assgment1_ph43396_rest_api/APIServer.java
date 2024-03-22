package com.example.assgment1_ph43396_rest_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServer {
    String DOMAIN = "http://10.24.34.79:3000";

    @GET("/api/list")
    Call<List<SanphamModel>> getSanphams();

    @POST("/addsp")
    Call<SanphamModel> addSanpham();
    @DELETE("/xoa/{id}")
    Call<Void> deleteProduct(@Path("id") String id);
}
