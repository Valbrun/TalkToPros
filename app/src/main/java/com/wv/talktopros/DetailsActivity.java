package com.wv.talktopros;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    TextView screen;
    TextView name,type,experience_space,age,uid;
    RecyclerView recyclerView;
    LinearLayout ll;
    ArrayList<Specialist> userArrayList;
    DetailsAdapter userAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        screen = findViewById(R.id.screen_pp);
        String tampon = getIntent().getStringExtra("REFE");
        screen.setText(tampon);
        this.userid = getIntent().getStringExtra("UID");

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching Data");
//        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview_message);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<Specialist>();
        userAdapter = new DetailsAdapter(DetailsActivity.this,userArrayList);
        recyclerView.setAdapter(userAdapter);

        EventChangeListener( tampon);


    }
//.orderBy("fname",Query.Direction.ASCENDING)
    private void EventChangeListener(String Value) {

        db.collection("users").whereEqualTo("type",Value)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException error) {


                if (error != null) {
//                    if(progressDialog.isShowing())
//                        progressDialog.dismiss();
//                    int d = Log.e("firebaseError" + error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.getDocument().toObject(Specialist.class));
                    }
                    userAdapter.notifyDataSetChanged();
//                    if(progressDialog.isShowing())
//                        progressDialog.dismiss();
                }


            }
        });
    }

    public void shoo(View view) {
        uid = findViewById(R.id.uid);
        name =  view.findViewById(R.id.name);
        String reference = name.getText().toString();
        String reference2 = uid.getText().toString();
        Intent intent = new Intent(DetailsActivity.this, Conversation.class);
        intent = intent.putExtra("REFE", reference);
        intent = intent.putExtra("UUID", reference2);
        intent = intent.putExtra("MYUID", this.userid);
        startActivity(intent);
    }


}