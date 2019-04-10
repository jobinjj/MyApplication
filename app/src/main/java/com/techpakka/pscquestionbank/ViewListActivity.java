package com.techpakka.pscquestionbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techpakka.pscquestionbank.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {
    List<String> list = new ArrayList<>();
    private FirebaseFirestore db;
    private String category_name;
    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        category_name = getIntent().getStringExtra("category_name");
        initViews();
        db = FirebaseFirestore.getInstance();
        db.collection(category_name)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            list.add(queryDocumentSnapshot.getId());
                        }
                        recyclerView();
                    }
                });




    }
    private void initViews(){
        recycler_view = findViewById(R.id.recycler_view);
    }
    private void recyclerView() {

        Adapter adapter = new Adapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewListActivity.this);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(adapter);
    }
    public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
        private List<String> list;

        public Adapter(List<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.content_view, viewGroup, false);

            return new Adapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.MyViewHolder myViewHolder, final int i) {
            myViewHolder.category_name.setText(list.get(i));
            myViewHolder.card_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewListActivity.this,ViewDetailActivity.class);
                    intent.putExtra("subcategory_name",list.get(i));
                    intent.putExtra("category_name",category_name);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView category_name;
            private CardView card_container;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                category_name = itemView.findViewById(R.id.category_name);
                card_container = itemView.findViewById(R.id.card_container);
            }
        }
    }
}
