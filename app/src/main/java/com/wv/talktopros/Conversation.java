package com.wv.talktopros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Conversation extends AppCompatActivity {
    TextView screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        screen = findViewById(R.id.screen_conversation);
        String tampon = getIntent().getStringExtra("REFE");
        screen.setText(tampon);
    }


}