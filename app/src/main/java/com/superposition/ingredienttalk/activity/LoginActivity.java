package com.superposition.ingredienttalk.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.superposition.ingredienttalk.R;
import com.superposition.ingredienttalk.data.UserData;
import com.superposition.ingredienttalk.dialog.DialogProgress;
import com.superposition.ingredienttalk.util.Constants;
import com.superposition.ingredienttalk.util.ToastHelper;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView txtSignUp;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUp = findViewById(R.id.txtLoginSignUp);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    new ToastHelper().makeToast(LoginActivity.this, "All fields are mandatory!", Toast.LENGTH_LONG);
                } else {
                    DialogFragment progressDialog = new DialogProgress("Verifying user...");
                    progressDialog.setCancelable(false);
                    progressDialog.show(getSupportFragmentManager(), "Dialog Progress");

                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SharedPreferences sharedPreferences = getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users/" + task.getResult().getUser().getUid());
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        com.superposition.ingredienttalk.data.UserData userData = snapshot.getValue(UserData.class);
                                        sharedPreferences.edit().putString(Constants.NAME, userData.getName()).apply();
                                        sharedPreferences.edit().putString(Constants.EMAIL, userData.getEmail()).apply();
                                        sharedPreferences.edit().putString(Constants.MOBILE, userData.getMobile()).apply();
                                        sharedPreferences.edit().putString(Constants.UID, userData.getUid()).apply();
                                        sharedPreferences.edit().putString(Constants.IMAGE_LINK, userData.getImage_link()).apply();
                                        sharedPreferences.edit().putBoolean(Constants.IS_LOGGED_IN, true).apply();

                                        progressDialog.dismiss();

                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        progressDialog.dismiss();
                                        new ToastHelper().makeToast(LoginActivity.this, "Something went wrong! Please try again later.", Toast.LENGTH_LONG);
                                    }
                                });

                            } else {
                                progressDialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    new ToastHelper().makeErrorToastForEditText(LoginActivity.this, "User not registered!", "Email", Toast.LENGTH_LONG, etEmail);
                                    new ToastHelper().makeErrorToastForEditText(LoginActivity.this, "User not registered!", "Password", Toast.LENGTH_LONG, etPassword);

                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    new ToastHelper().makeErrorToastForEditText(LoginActivity.this, "Invalid Password!", "Invalid password", Toast.LENGTH_LONG, etPassword);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    new ToastHelper().makeToast(LoginActivity.this, "Something went wrong! Please try again later.", Toast.LENGTH_LONG);
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}