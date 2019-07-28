package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView welcome, registerActivity;
    private EditText email, password;
    private Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();

        firebaseAuth = FirebaseAuth.getInstance();
        // if user has already logged in then go straight to homepage
        FirebaseUser user = firebaseAuth.getCurrentUser();
        updateUI(user);

        // login button go to homepage activity if data entered is correct
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signingIn();
            }
        });

        // registerActivity goes to RegisterActivity if clicked for users to register
        registerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpViews() {
        welcome = findViewById(R.id.tvWelcome);
        registerActivity = findViewById(R.id.tvRegisterActivity);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            final Intent i = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void signingIn() {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { //if signin successful then toast and bring user to homepage
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT);
                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                    updateUI(currentUser);
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT);
                    updateUI(null);
                }
            }
        });
    }
}
