package com.example.appquizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

  TextView output;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    output = findViewById(R.id.textViewPontos);

    Intent it = getIntent();
    if(it != null){
      Bundle params = it.getExtras();
      if(params != null){
        int pontos = params.getInt("pontos");
        output.setText(String.valueOf(pontos));
      }
    }
  }
}