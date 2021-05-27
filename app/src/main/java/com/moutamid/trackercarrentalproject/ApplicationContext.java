package com.moutamid.trackercarrentalproject;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApplicationContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {

                    databaseReference.child("requests")
                            .child(auth.getCurrentUser().getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!snapshot.exists())
                                        return;

                                    if (!snapshot.child("pushKey").exists())
                                        return;

                                    String keyStr = snapshot.child("pushKey").getValue(String.class);

                                    if (snapshot.child("status").exists()) {
                                        String statusStr = snapshot.child("status").getValue(String.class);

                                        databaseReference.child("booking_history").child(keyStr)
                                                .child("status").setValue(statusStr);
                                    }

                                    if (snapshot.child("currentMileages").exists()) {
                                        double currentMileagesStr = snapshot.child("currentMileages").getValue(Double.class);

                                        databaseReference.child("booking_history").child(keyStr)
                                                .child("currentMileages")
                                                .setValue(currentMileagesStr);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                }
            }
        });
    }
}
