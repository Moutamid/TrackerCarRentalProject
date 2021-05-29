package com.moutamid.trackercarrentalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.LayoutInflater.from;
import static com.moutamid.trackercarrentalproject.R.id.tracking_history_recycler_view;

public class TrackingHistoryActivity extends AppCompatActivity {
    private static final String TAG = "TrackingHistoryActivity";

    private ArrayList<String> tasksArrayList = new ArrayList<>();
    private ArrayList<Hitem> modelArrayList = new ArrayList<>();

    private RecyclerView conversationRecyclerView;
    private RecyclerViewAdapterMessages adapter;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_history);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String currentCarKey = new Utils().getStoredString(TrackingHistoryActivity.this, "currentKey");

        databaseReference
                .child("cars")
                .child(currentCarKey)
//                .child("booking")
//                .child("requests")
//                .child(mAuth.getCurrentUser().getUid())
                .child("tracking_history").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    progressDialog.dismiss();
                    Toast.makeText(TrackingHistoryActivity.this, "No data exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                tasksArrayList.clear();
                modelArrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    modelArrayList.add(dataSnapshot.getValue(Hitem.class));
                    tasksArrayList.add(dataSnapshot.child("long").getValue(String.class));
                }

                initRecyclerView();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(TrackingHistoryActivity.this, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecyclerView() {

        conversationRecyclerView = findViewById(tracking_history_recycler_view);
        //conversationRecyclerView.addItemDecoration(new DividerItemDecoration(conversationRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapterMessages();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        conversationRecyclerView.setLayoutManager(linearLayoutManager);
        conversationRecyclerView.setHasFixedSize(true);
        conversationRecyclerView.setNestedScrollingEnabled(false);

        conversationRecyclerView.setAdapter(adapter);

        //    if (adapter.getItemCount() != 0) {

        //        noChatsLayout.setVisibility(View.GONE);
        //        chatsRecyclerView.setVisibility(View.VISIBLE);

        //    }

    }

    private static class Hitem {

        private String time, lat;

        public Hitem(String time, String lat) {
            this.time = time;
            this.lat = lat;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        Hitem() {
        }
    }

    private class RecyclerViewAdapterMessages extends Adapter
            <RecyclerViewAdapterMessages.ViewHolderRightMessage> {

        @NonNull
        @Override
        public ViewHolderRightMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = from(parent.getContext()).inflate(R.layout.layout_tracking_history, parent, false);
            return new ViewHolderRightMessage(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderRightMessage holder, int position) {

            holder.time.setText(modelArrayList.get(position).getTime());
            holder.lat.setText(modelArrayList.get(position).getLat());
            holder.longi.setText(tasksArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            if (tasksArrayList == null)
                return 0;
            return tasksArrayList.size();
        }

        public class ViewHolderRightMessage extends ViewHolder {

            TextView time, lat, longi;

            public ViewHolderRightMessage(@NonNull View v) {
                super(v);
                time = v.findViewById(R.id.timeTxt);
                lat = v.findViewById(R.id.latTxt);
                longi = v.findViewById(R.id.longTxt);

            }
        }

    }
}