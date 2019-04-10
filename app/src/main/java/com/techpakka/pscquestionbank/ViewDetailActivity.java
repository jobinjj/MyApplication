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
    List<String> list = new ArrayList<>();
    private String category_name;
    private String sub_categoryname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        db = FirebaseFirestore.getInstance();
        sub_categoryname = getIntent().getStringExtra("subcategory_name");
        category_name = getIntent().getStringExtra("category_name");
        db.collection(category_name).document(sub_categoryname).collection(sub_categoryname)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            list.add(queryDocumentSnapshot.getId());
                        }
                    }
                });

    }
}
