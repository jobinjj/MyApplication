package com.techpakka.pscquestionbank;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    private String categoryname;
    private ProgressDialog progressDialog;
    private String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        progressDialog = new ProgressDialog(ViewListActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        db = FirebaseFirestore.getInstance();
        categoryname = getIntent().getStringExtra("categoryname");
        final List<String> questionList = new ArrayList<>();
        db.collection(categoryname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                                questionList.add(String.valueOf(document.getId()));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
