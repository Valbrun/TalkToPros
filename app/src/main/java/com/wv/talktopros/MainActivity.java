package com.wv.talktopros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wv.talktopros.DetailsActivity;

public class MainActivity extends AppCompatActivity {
    TextView item;
    ImageButton btn_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        btn_med.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String reference = medecin.getText().toString();
//                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent = intent.putExtra("REFE", "+reference+");
//                startActivity(intent);
//            }
//        });



    }

    public void medecin(View view) {
        item = findViewById(R.id.screen_med);
        btn_item = findViewById(R.id.btn_med);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        startActivity(intent);
    }

    public void avocat(View view) {
        item = findViewById(R.id.screen_avo);
        btn_item = findViewById(R.id.btn_avo);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        startActivity(intent);
    }

    public void commercant(View view) {
        item = findViewById(R.id.screen_com);
        btn_item = findViewById(R.id.btn_com);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        startActivity(intent);
    }

    public void informaticien(View view) {
        item = findViewById(R.id.screen_inf);
        btn_item = findViewById(R.id.btn_inf);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        startActivity(intent);
    }

}