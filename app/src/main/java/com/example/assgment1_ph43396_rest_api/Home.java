package com.example.assgment1_ph43396_rest_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.assgment1_ph43396_rest_api.databinding.ActivityHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    ListView lvMain;
    List<SanphamModel> sanphamModels;
    AdapterSanpham adapterSanpham;
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        lvMain= binding.lvDanhSach;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIServer.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServer apiService = retrofit.create(APIServer.class);

        Call<List<SanphamModel>> call = apiService.getSanphams();

        call.enqueue(new Callback<List<SanphamModel>>() {
            @Override
            public void onResponse(Call<List<SanphamModel>> call, Response<List<SanphamModel>> response) {
                if (response.isSuccessful()) {
                    sanphamModels = response.body();

                    adapterSanpham = new AdapterSanpham(Home.this, sanphamModels);

                    lvMain.setAdapter(adapterSanpham);
                }
            }

            @Override
            public void onFailure(Call<List<SanphamModel>> call, Throwable t) {
                Log.e("Main", t.getMessage());
            }
        });
    }
}