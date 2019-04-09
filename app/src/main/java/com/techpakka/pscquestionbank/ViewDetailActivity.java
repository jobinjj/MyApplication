package com.techpakka.pscquestionbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techpakka.pscquestionbank.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ViewDetailActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    List<Data> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        db = FirebaseFirestore.getInstance();
        db.collection("App").document("india").collection("india")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        //Toast.makeText(ViewActivity.this, "suuccess", Toast.LENGTH_SHORT).show();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            Data data = queryDocumentSnapshot.toObject(Data.class);
                            Toast.makeText(ViewDetailActivity.this, data.getQuestion()+data.getAnswer(), Toast.LENGTH_SHORT).show();
                            list.add(data);
                            Log.d("serverdata",data.getQuestion()+data.getAnswer());
                        }
                    }
                });
    }
}
