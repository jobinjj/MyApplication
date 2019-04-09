package com.techpakka.pscquestionbank;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private static final String TAG = "tag";
    private FirebaseFirestore db;
    private Button btn_submit;
    private EditText editText,editText2,editText3,editText4,editText5;
    private String category;
    private ImageView img_category_add;
    private String category_name;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        initializeFireStore();
        initViews();
        setUpSpinner();
        onClick();

    }



    private void initializeFireStore() {
        db = FirebaseFirestore.getInstance();
    }

    private void onClick() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("") && !editText2.getText().toString().equals("") && !editText3.getText().toString().equals("") && !editText4.getText().toString().equals("") && !editText5.getText().toString().equals("")){
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.show();
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    Map<String, Object> data1 = new HashMap<>();
                    data1.put("data", editText.getText().toString());
                    data1.put("data1", editText2.getText().toString());
                    data1.put("data2", editText3.getText().toString());
                    data1.put("data3", editText4.getText().toString());
                    data1.put("data4", editText5.getText().toString());

                    db.collection("App").document("Categories").collection(category)
                            .add(data1)
                      .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                          @Override
                          public void onSuccess(DocumentReference documentReference) {
                              progressDialog.dismiss();
                              Toast.makeText(MainActivity.this, "added succesfully", Toast.LENGTH_SHORT).show();
                          }
                      });
                }else {
                    Toast.makeText(MainActivity.this, "enter values!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initViews() {
        btn_submit = findViewById(R.id.btn_submit);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
    }

    private void setUpSpinner() {

        final List<String> plantsList = new ArrayList<>();
                db.collection("categoryname")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               // Log.d(TAG, document.getId() + " => " + document.getData());
                                plantsList.add(String.valueOf(document.getId()));
                                Spinner spinner = findViewById(R.id.spinner_category);

                                spinner.setOnItemSelectedListener(MainActivity.this);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                        MainActivity.this ,android.R.layout.simple_spinner_item,plantsList);

                                adapter.setDropDownViewResource
                                        (android.R.layout.simple_spinner_dropdown_item);

                                spinner.setAdapter(adapter);
                                Log.d("data", String.valueOf(document.getString("af")));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){
            case R.id.spinner_category:
                category = parent.getItemAtPosition(position).toString();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
