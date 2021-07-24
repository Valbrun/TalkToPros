package com.wv.talktopros;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Conversation extends AppCompatActivity {
    TextView screen;
    RecyclerView recyclerView;
    EditText text_send;
    String myuid;
    String uuid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<MessageFormat> messageArrayList;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        screen = findViewById(R.id.screen_conversation);
        String tampon = getIntent().getStringExtra("REFE");
        this.myuid = getIntent().getStringExtra("MYUID");
        this.uuid = getIntent().getStringExtra("UUID");
        screen.setText(tampon);

        recyclerView = findViewById(R.id.recyclerview_message);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageArrayList = new ArrayList<MessageFormat>();
        messageAdapter = new MessageAdapter(Conversation.this,messageArrayList);
        recyclerView.setAdapter(messageAdapter);
        EventChangeListener();

    }


    public void sendMessage(View view) {
        text_send = findViewById(R.id.text_send);
        String reference = text_send.getText().toString();
        Map<String, Object> msg = new HashMap<>();
        msg.put("message", reference);
        msg.put("uid", this.myuid);
        msg.put("proid", this.uuid);


        db.collection("messages")
                .add(msg)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(Conversation.this, "Sent", Toast.LENGTH_SHORT).show();
                        text_send.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    private void EventChangeListener() {

        db.collection("messages").whereEqualTo("uid",this.myuid)
                .whereEqualTo("proid", this.uuid)
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
                             messageArrayList.add(dc.getDocument().toObject(MessageFormat.class));
                            }
                          messageAdapter.notifyDataSetChanged();
//                    if(progressDialog.isShowing())
//                        progressDialog.dismiss();
                        }


                    }
                });
    }

}