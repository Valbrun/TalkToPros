package com.wv.talktopros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wv.talktopros.DetailsActivity;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    TextView item;
    TextView hello;
    ImageButton btn_item;
    Button btn_logout;
    FirebaseAuth mAuth;
String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      this.uid = getIntent().getStringExtra("UID");
        mAuth = FirebaseAuth.getInstance();
        getUserInfos(this.uid);
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(view->{
            mAuth.signOut();
            Toast.makeText(MainActivity.this, "Deconnecté", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });

    }

    private void getUserInfos(String uid) {
        db.collection("user").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                hello = findViewById(R.id.screen_hello);

                     String s = task.getResult().getString("type");

//                    hello.setText(uid);

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
      //       Toast.makeText(this, "nu lllllllll", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(this,LoginActivity.class));
        }
    }


    public void medecin(View view) {
        item = findViewById(R.id.screen_med);
        btn_item = findViewById(R.id.btn_med);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        intent = intent.putExtra("UID", this.uid);
        startActivity(intent);
    }

    public void avocat(View view) {
        item = findViewById(R.id.screen_avo);
        btn_item = findViewById(R.id.btn_avo);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        intent = intent.putExtra("UID", this.uid);
        startActivity(intent);
    }

    public void commercant(View view) {
        item = findViewById(R.id.screen_com);
        btn_item = findViewById(R.id.btn_com);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        intent = intent.putExtra("UID", this.uid);
        startActivity(intent);
    }

    public void informaticien(View view) {
        item = findViewById(R.id.screen_inf);
        btn_item = findViewById(R.id.btn_inf);
        String reference = item.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent = intent.putExtra("REFE", reference);
        intent = intent.putExtra("UID", this.uid);
        startActivity(intent);
    }


}