package com.example.appquizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private EditText inputResposta;
  private ImageView cityImage;
  private TextView outputMenssagem;
  private Button buttonIniciar;
  private Button buttonConfirmar;
  private Button buttonPergunta;
  private Random r;
  private int num, points;
  private int rodada = 0;
  private String[] cidades = {"barcelona", "brasilia", "curitiba", "las vegas", "montreal", "paris",
                    "rio de janeiro", "salvador", "sao paulo", "toquio"};
  private String[] cidadesRequest = {"01_barcelona.jpg", "02_brasilia.jpg", "03_curitiba.jpg",
          "04_lasvegas.jpg", "05_montreal.jpg", "06_paris.jpg",
          "07_riodejaneiro.jpg", "08_salvador.jpg", "09_saopaulo.jpg", "10_toquio.jpg"};

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    cityImage = findViewById(R.id.imageViewCity);
    inputResposta = findViewById(R.id.editTextCity);
    outputMenssagem = findViewById(R.id.textViewOutput);
    buttonIniciar = findViewById(R.id.buttonIniciar);
    buttonConfirmar = findViewById(R.id.buttonConfirmar);
    buttonPergunta = findViewById(R.id.buttonPergunta);
  }

  public void sortCity(View view){
    r = new Random();
    num = r.nextInt(10);
    String imageUrl = "http://31.220.51.31/ufpr/cidades/" + cidadesRequest[num];
    //Picasso.get().load(imageUrl).into(cityImage);
    new DownloadImageTask(cityImage).execute(imageUrl);
    buttonIniciar.setVisibility(View.INVISIBLE);
  }

  private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
      this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
      String urldisplay = urls[0];
      Bitmap mIcon11 = null;
      try {
        InputStream in = new java.net.URL(urldisplay).openStream();
        mIcon11 = BitmapFactory.decodeStream(in);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
      bmImage.setImageBitmap(result);
    }
  }

  public void checkAnswer(View view){
    if(inputResposta.length() == 0){
      Toast.makeText(this, "Forneça uma cidade", Toast.LENGTH_SHORT).show();
    }else{
      if (cidades[num].toString().equalsIgnoreCase(inputResposta.getText().toString())) {
        outputMenssagem.setText("Resposta correta!");
        points += 25;
      } else {
        outputMenssagem.setText("Errou! A resposta é " + cidades[num]);
        points += 0;
      }
      buttonIniciar.setVisibility(View.INVISIBLE);
    }
  }

  public void nextQuestion(View view){
    outputMenssagem.setText("");
    sortCity(view);
  }
}