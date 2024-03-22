package com.example.assgment1_ph43396_rest_api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assgment1_ph43396_rest_api.databinding.ItemUpdateAddBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterSanpham extends BaseAdapter {

    Context context;
    List<SanphamModel> sanphamModels;
ImageView btnDelete;
ImageView btnUpdate;
    public AdapterSanpham(Context context, List<SanphamModel> sanphamModels) {
        this.context = context;
        this.sanphamModels = sanphamModels;
    }

    @Override
    public int getCount() {
        return sanphamModels.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_sp, viewGroup, false);

        TextView tvID = (TextView) rowView.findViewById(R.id.tvId);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgAvatatr);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);

        TextView tvSoluong = (TextView) rowView.findViewById(R.id.tvSoluong);

        TextView tvTonkho = (TextView) rowView.findViewById(R.id.tvTonkho);

        TextView tvGia = (TextView) rowView.findViewById(R.id.tvGia);
         btnDelete=rowView.findViewById(R.id.imgdelete);
        btnUpdate = rowView.findViewById(R.id.btnUpdate);
//        String imageUrl = mList.get(position).getThumbnailUrl();
//        Picasso.get().load(imageUrl).into(imgAvatar);
////        imgAvatar.setImageResource(imageId[position]);
        tvName.setText(String.valueOf(sanphamModels.get(position).getTen()));

        tvSoluong.setText(String.valueOf(sanphamModels.get(position).getSoluong()));

        tvTonkho.setText(String.valueOf(sanphamModels.get(position).isTonkho()));

        tvGia.setText(String.valueOf(sanphamModels.get(position).getGia()));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIServer.DOMAIN)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIServer apiService = retrofit.create(APIServer.class);


                Call<Void> call = apiService.deleteProduct(sanphamModels.get(position).get_id());


                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("ZZZZZZ", "onResponse: " + response);
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();

                            CallAPI(retrofit);
                        } else {
                            Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("DELETE_PRODUCT_ERROR", "onFailure: " + t.getMessage());
                        Toast.makeText(context, "Lỗi khi xóa sản phẩm", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        btnUpdate.setOnClickListener(view1 -> {
            showPopupDialog(context, sanphamModels.get(position));
        });
        return rowView;
    }
    private void showPopupDialog(Context context1,SanphamModel sanphamModel) {
        Dialog dialog = new Dialog(context1);

        LayoutInflater inflater = ((Activity) context1).getLayoutInflater();
        ItemUpdateAddBinding binding = ItemUpdateAddBinding.inflate(inflater);
        dialog.setContentView(binding.getRoot());


        binding.btnUPDATEup.setOnClickListener(view -> {
            Toast.makeText(context1, "UPDATE", Toast.LENGTH_SHORT).show();
        });





        Window window = dialog.getWindow();




        if ( window != null ) {

            window.setGravity(Gravity.CENTER);

            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

            window.getAttributes().flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;


        }

        dialog.show();
    }
    private void CallAPI(Retrofit retrofit) {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(APIServer.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServer apiService = retrofit1.create(APIServer.class);

        Call<List<SanphamModel>> call = apiService.getSanphams();

        call.enqueue(new Callback<List<SanphamModel>>() {
            @Override
            public void onResponse(Call<List<SanphamModel>> call, Response<List<SanphamModel>> response) {
                if (response.isSuccessful()) {
                    sanphamModels = response.body();

                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<SanphamModel>> call, Throwable t) {
                Log.e("Main", t.getMessage());
            }
        });


    }
}
