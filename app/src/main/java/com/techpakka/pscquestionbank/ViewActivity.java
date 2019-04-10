package com.techpakka.pscquestionbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techpakka.pscquestionbank.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    ArrayList<Data> list = new ArrayList<>();
    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        sampleData();
        init();
        recyclerView();
    }

    private void init() {
        recycler_view = findViewById(R.id.recycler_view);

    }

    private void recyclerView() {

        Adapter adapter = new Adapter(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ViewActivity.this, 2);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(adapter);
    }

    private void sampleData() {
        Data data = new Data("India", "1");
        list.add(data);
        Data data2 = new Data("Kerala", "2");
        list.add(data2);
    }





    public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
        private List<Data> list;

        public Adapter(List<Data> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.content_home, viewGroup, false);

            return new Adapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.MyViewHolder myViewHolder, int i) {
            final Data data = list.get(i);
            myViewHolder.category_name.setText(data.getCategory());
            myViewHolder.imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_taj_mahal));
            myViewHolder.card_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewActivity.this, ViewListActivity.class);
                    intent.putExtra("category_name", data.getCategory());
                    intent.putExtra("category_id", data.getCategory_id());
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
            private ImageView imageView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                category_name = itemView.findViewById(R.id.category_name);
                imageView = itemView.findViewById(R.id.imageView);
                card_container = itemView.findViewById(R.id.card_container);
            }
        }
    }
}
