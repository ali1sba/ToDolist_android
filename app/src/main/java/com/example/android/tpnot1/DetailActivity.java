package com.example.android.tpnot1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageButton imageButton = findViewById(R.id.tts);
        Intent intent = getIntent();
        String nom = intent.getStringExtra("name");
        String text = intent.getStringExtra("text");
        String date = intent.getStringExtra("date");
        //String img = intent.getStringExtra("img");

        TextView nomView = findViewById(R.id.detail_name);
        TextView textView = findViewById(R.id.detail_description);
        TextView dateView = findViewById(R.id.detail_date);
        ImageView imgView = findViewById(R.id.detail_image);

        nomView.setText(nom);
        textView.setText(text);
        dateView.setText(date);
        imgView.setImageResource(R.drawable.ic_baseline_account_circle_24);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.FRANCE);
                }
                imageButton.setEnabled(true);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    protected void onPause() {
        tts.stop();
        super.onPause();
    }
}