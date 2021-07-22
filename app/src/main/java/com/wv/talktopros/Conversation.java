package com.wv.talktopros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Conversation extends AppCompatActivity {
    TextView screen;
    EditText text_send;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        screen = findViewById(R.id.screen_conversation);
        String tampon = getIntent().getStringExtra("REFE");
        screen.setText(tampon);



    }


    public void sendMessage(View view) {

        text_send = findViewById(R.id.text_send);
        String reference = text_send.getText().toString();
        screen.setText(reference);

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("message", reference);

// Add a new document with a generated ID
        db.collection("messages")
                .add(user)
                .addOnSuccessListener(documentReference -> Toast.makeText(this,   documentReference.getId(), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

    }
}