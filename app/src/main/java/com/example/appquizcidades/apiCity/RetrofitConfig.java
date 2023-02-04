package com.example.appquizcidades.apiCity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
  private final Retrofit retrofit;

  public RetrofitConfig(){
    this.retrofit = new Retrofit.Builder()
            .baseUrl("http://31.220.51.31/ufpr/cidades/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public CityService getCity(){
    return this.retrofit.create(CityService.class);
  }
}
