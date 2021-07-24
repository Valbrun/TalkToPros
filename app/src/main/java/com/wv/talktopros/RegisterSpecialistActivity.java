

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
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.FirebaseFirestore;

        import org.jetbrains.annotations.NotNull;

        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;

        import static android.content.ContentValues.TAG;


public class RegisterSpecialistActivity extends AppCompatActivity     implements     AdapterView.OnItemSelectedListener {
        String[] categories = { "Médecin", "Informaticien", "Avocat", "Commercant"};
        EditText space_name;
    EditText space_email;
    EditText space_password;
    EditText space_age;
    EditText space_experience;
    String space_speciality;
    Button btn_signup;
    TextView textview;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_specialist);
        changeStatusBarColor();


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



        space_name = findViewById(R.id.space_name);
        space_age = findViewById(R.id.space_age);
        space_experience = findViewById(R.id.space_experience);
//        space_speciality = findViewById(R.id.space_speciality);
        space_email = findViewById(R.id.space_email);
        space_password = findViewById(R.id.space_password);
        btn_signup = findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();
        btn_signup.setOnClickListener(view ->
                {
                    createUser();
                }
        );

    }

    private void createUser() {
        String  name = space_name.getText().toString();
        String  age = space_age.getText().toString();
        String  experience = space_experience.getText().toString();
        String  speciality = this.space_speciality;
        String email = space_email.getText().toString();
        String password = space_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email ne peut etre vide", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(name)){
            Toast.makeText(this, " name ne peut etre vide", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(age)){
            Toast.makeText(this, "age ne peut etre vide", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(experience)){
            Toast.makeText(this, "experience ne peut etre vide", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "password ne peut etre vide", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    addTodb(name,age,experience,speciality,email,password,user.getUid());
                    Toast.makeText(RegisterSpecialistActivity.this, "Inscrit", Toast.LENGTH_SHORT).show();
                }else  {
                    Toast.makeText(RegisterSpecialistActivity.this, "Echec" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addTodb(String name, String age, String experience, String speciality, String email, String password, String UID) {


        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name",  name);
        user.put("email", email);
        user.put("password", password);
        user.put("age", age);
        user.put("experience", experience);
        user.put("type", speciality);
        user.put("date", new Date());
        user.put("uid", UID);

// Add a new document with a generated ID Toast.makeText(this,   documentReference.getId(), Toast.LENGTH_SHORT).show())
        db.collection("users").document(UID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(RegisterSpecialistActivity.this, "BIENVENUE", Toast.LENGTH_SHORT).show();
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




    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id ) {
        textview =  findViewById(R.id.textView);
        String s = categories[position];
        this.space_speciality = s;
        textview.setText(s);
        Toast.makeText(getApplicationContext(),categories[position] , Toast.LENGTH_LONG).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
