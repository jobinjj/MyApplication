package com.techpakka.pscquestionbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techpakka.pscquestionbank.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Test")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ViewActivity.this, "success", Toast.LENGTH_SHORT).show();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("test", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("test", "Error getting documents.", task.getException());
                        }
                    }
                });
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this,ViewListActivity.class);
                intent.putExtra("categoryname","india");
                startActivity(intent);
            }
        });
        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this,ViewListActivity.class);
                intent.putExtra("categoryname","kerala");
                startActivity(intent);
            }
        });

    }
}
