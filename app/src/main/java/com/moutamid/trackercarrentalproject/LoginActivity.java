package com.moutamid.trackercarrentalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginBtn;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabaseUsers;
    private Boolean isOnline = false;
    //        private SharedPreferences sharedPreferences;
    private EditText userNameEditText_RSD;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("users");
        mDatabaseUsers.keepSynced(true);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Signing you in...");

        // Initializing Views
        initViews();

    }

    private View.OnClickListener loginBtnListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(LoginActivity.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
//                        Toast.makeText(LoginActivity.this, "Permission granted successfully!", Toast.LENGTH_SHORT).show();

                                Dexter.withContext(LoginActivity.this)
                                        .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                                        .withListener(new PermissionListener() {
                                            @Override
                                            public void onPermissionGranted(PermissionGrantedResponse response) {
//                        Toast.makeText(LoginActivity.this, "Permission granted successfully!", Toast.LENGTH_SHORT).show();

                                                String emailStr = emailEditText.getText().toString();
                                                String passwordStr = passwordEditText.getText().toString();

                                                if (!TextUtils.isEmpty(emailStr) && !TextUtils.isEmpty(passwordStr)) {
                                                    mProgressDialog.show();

                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                                    databaseReference.child("cars").child(emailStr).addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                            if (snapshot.exists()) {

                                                                mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                                                        if (task.isSuccessful()) {
                                                                            mProgressDialog.dismiss();
                                                                            new Utils().storeString(LoginActivity.this, "currentKey", emailStr);

                                                                            finish();
                                                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                            startActivity(intent);

                                                                            Toast.makeText(LoginActivity.this, "You are logged in!", Toast.LENGTH_SHORT).show();

                                                                        } else {
                                                                            mProgressDialog.dismiss();
                                                                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                        }

                                                                    }
                                                                });

                                                            } else {
                                                                mProgressDialog.dismiss();
                                                                Toast.makeText(LoginActivity.this, "Car key is invalid!", Toast.LENGTH_SHORT).show();
                                                            }

                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            mProgressDialog.dismiss();
                                                            Toast.makeText(LoginActivity.this, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

//                    signInUserWithNameAndPassword(emailStr, passwordStr);

                                                } else if (TextUtils.isEmpty(emailStr)) {

                                                    emailEditText.setError("Please enter emailStr");
                                                    emailEditText.requestFocus();

                                                } else if (TextUtils.isEmpty(passwordStr)) {

                                                    passwordEditText.setError("Please enter password");
                                                    passwordEditText.requestFocus();

                                                }

                                            }

                                            @Override
                                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                                if (response.isPermanentlyDenied()) {
                                                    // open device settings when the permission is
                                                    // denied permanently
                                                    Toast.makeText(LoginActivity.this, "You need to provide permission!", Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent();
                                                    intent.setAction(
                                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    Uri uri = Uri.fromParts("package",
                                                            BuildConfig.APPLICATION_ID, null);
                                                    intent.setData(uri);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                                token.continuePermissionRequest();
                                            }
                                        }).check();

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    // open device settings when the permission is
                                    // denied permanently
                                    Toast.makeText(LoginActivity.this, "You need to provide permission!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent();
                                    intent.setAction(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package",
                                            BuildConfig.APPLICATION_ID, null);
                                    intent.setData(uri);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        };
    }

    private void signInUserWithNameAndPassword(final String emailStr, final String passwordStr) {

        mProgressDialog.show();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            //if Email Address is Invalid..

            mProgressDialog.dismiss();
            emailEditText.setError("Email is not valid. Make sure no spaces and special characters are included");
            emailEditText.requestFocus();
        } else {

            mAuth.signInWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        getUserdetails();

                    } else {

                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    private void getUserdetails() {
        mDatabaseUsers.child(mAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (!snapshot.exists()) {
                            return;
                        }

                        new Utils().storeString(LoginActivity.this,
                                "nameStr", snapshot.child("name").getValue(String.class));

                        new Utils().storeString(LoginActivity.this,
                                "emailStr", snapshot.child("email").getValue(String.class));

                        new Utils().storeString(LoginActivity.this,
                                "licenseStr", snapshot.child("licenseNumber").getValue(String.class));

                        mProgressDialog.dismiss();

                        finish();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, "You are logged in!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initViews() {
        emailEditText = findViewById(R.id.email_edittext_activity_login);
        passwordEditText = findViewById(R.id.password_edittext_activity_login);
        loginBtn = findViewById(R.id.login_btn_activity_login);
        loginBtn.setOnClickListener(loginBtnListener());
    }

}