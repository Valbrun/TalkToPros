package com.wv.talktopros;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {
    EditText space_name;
    EditText space_email;
    EditText space_password;
    Button btn_signup;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        space_name = findViewById(R.id.space_uname);
         space_email = findViewById(R.id.space_email);
         space_password =findViewById(R.id.space_password);
         btn_signup= findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();
        btn_signup.setOnClickListener(view->
                {
                    createUser();
                }
                );


    }

    private void createUser() {
        String name = space_name.getText().toString();
        String email = space_email.getText().toString();
        String password = space_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email ne peut etre vide", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "username ne peut etre vide", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "password ne peut etre vide", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                addTodb(name,email,password,user.getUid());
                    Toast.makeText(RegisterActivity.this, "Inscrit", Toast.LENGTH_SHORT).show();
                }else  {
                    Toast.makeText(RegisterActivity.this, "Echec" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addTodb(String name,String email,String password,String UID ) {


        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("username", name);
        user.put("email", email);
        user.put("password", password);
        user.put("type", "normal");
        user.put("date", new Date());
        user.put("uid", UID);

// Add a new document with a generated ID Toast.makeText(this,   documentReference.getId(), Toast.LENGTH_SHORT).show())
        db.collection("users").document(UID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(RegisterActivity.this, "BIENVENUE", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           window.setStatusBarColor(Color.TRANSPARENT);
            //           window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
    public void onLoginClick2(View view){
        startActivity(new Intent(this,RegisterSpecialistActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}