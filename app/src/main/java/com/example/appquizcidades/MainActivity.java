package com.example.appquizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquizcidades.Model.City;
import com.example.appquizcidades.apiCity.RetrofitConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  EditText input;
  ImageView cityImage;
  Random r;
  int num, points;
  int rodada = 0;
  TextView output;
  String[] cidades = {"barcelona", "brasilia", "curitiba", "las vegas", "montreal", "paris",
                    "rio de janeiro", "salvador", "sao paulo", "toquio"};
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
    output = findViewById(R.id.textViewOutput);
  }

  public void sort(View view){
    r = new Random();
    num = r.nextInt(10) + 1;
    Call<ResponseBody> call = new RetrofitConfig().getCity().getCityImage(cidadesRequest[num]);
    call.enqueue(new Callback<okhttp3.ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
        cityImage.setImageBitmap(bmp);
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {

      }
    });
    Button button = findViewById(R.id.button);
    button.setVisibility(View.INVISIBLE);
  }

  public void check(View view){
    if(input.length() == 0){
      Toast.makeText(this, "Forneça uma cidade", Toast.LENGTH_SHORT).show();
    }else{
      if (cidades[num] == (input.getText().toString())) {
        output.setText("Resposta correta!");
        points += 25;
      } else {
        output.setText("Errou! A resposta é " + cidades[num]);
        points += 0;
      }
    }

    //Intent it = new Intent(this, ResultActivity.class);
    //Bundle params = new Bundle();
    //params.putInt("pontos", points);
    //it.putExtras(params);
    //startActivity(it);
  }

  public void nextQuestion(View view){
    r = new Random();
    num = r.nextInt(10) + 1;
    Call<ResponseBody> call = new RetrofitConfig().getCity().getCityImage(cidadesRequest[num]);
    call.enqueue(new Callback<okhttp3.ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
        cityImage.setImageBitmap(bmp);
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {

      }
    });
  }
}