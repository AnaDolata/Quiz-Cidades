package com.example.appquizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appquizcidades.Model.City;
import com.example.appquizcidades.apiCity.RetrofitConfig;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  EditText input;
  ImageView cityImage;
  Random r;
  String[] cidades = {"barcelona", "brasilia", "curitiba", "lasvegas", "montreal", "paris",
                    "riodejaneiro", "salvador", "saopaulo", "toquio"};
  String[] cidadesRequest = {"01_barcelona.jpg", "02_brasilia.jpg", "03_curitiba.jpg",
          "04_lasvegas.jpg", "05_montreal.jpg", "06_paris.jpg",
          "07_riodejaneiro.jpg", "08_salvador.jpg", "09_saopaulo.jpg", "10_toquio.jpg"};

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    input = findViewById(R.id.editTextCity);
    cityImage = findViewById(R.id.imageViewCity);
  }

  public void sort(View view){
    //primeiro click, gera as 4 cidades e depois some
    r = new Random();
    int num = r.nextInt(10) + 1;
    Call<City> call = new RetrofitConfig().getCity().getCityImage(cidadesRequest[num]);
    call.enqueue(new Callback<City>() {
      @Override
      public void onResponse(Call<City> call, Response<City> response) {
        if(response.isSuccessful()){
          City city = response.body();
          cityImage.setImageBitmap(city.getImage());
        }
      }

      @Override
      public void onFailure(Call<City> call, Throwable t) {

      }
    });
  }

  public void check(View view){
    if(input.length() == 0){
      Toast.makeText(this, "Forneça uma cidade", Toast.LENGTH_SHORT).show();
    }else{

    }
  }
}