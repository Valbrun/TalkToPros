package com.wv.talktopros;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

public class LoginActivity extends AppCompatActivity {
    EditText space_email;
    EditText space_password;
    Button btn_signin;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(Color.WHITE);
        }
        setContentView(R.layout.activity_login);


        space_email = findViewById(R.id.space_email);
        space_password =findViewById(R.id.space_password);
        btn_signin= findViewById(R.id.btn_signin);

        mAuth = FirebaseAuth.getInstance();
        btn_signin.setOnClickListener(view->
                {
                   loginUser();
                }
        );





    }

    private void loginUser() {

        String email = space_email.getText().toString();
        String password = space_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email ne peut etre vide", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "password ne peut etre vide", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    Toast.makeText(LoginActivity.this, "Connecté", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent = intent.putExtra("UID", user.getUid());
                    startActivity(intent);

                }else  {
                    Toast.makeText(LoginActivity.this, "Echec" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }










    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}