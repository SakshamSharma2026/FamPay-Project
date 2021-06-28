package com.codewithshadow.fampay.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FamPayApi {

    //FamPay Api
    @GET("04a04703-5557-4c84-a127-8c55335bb3b4")
    Call<ResponseBody> getModel();
}
