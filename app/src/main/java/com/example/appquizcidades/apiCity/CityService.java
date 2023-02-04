package com.example.appquizcidades.apiCity;

import com.example.appquizcidades.Model.City;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CityService {
  @GET("{city}")
  Call<ResponseBody> getCityImage(@Path("city") String city);
}
