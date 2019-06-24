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

public class RegisterActivity extends AppCompatActivity {
    private TextView tvRegister, tvLoginActivity;
    private EditText etName, etRegisterPhoneNumber, etRegisterEmail, etRegisterPassword;
    private Button btnRegister;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();
        //if all boxes are filled up, register button goes to MainActivity, else make toast
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String email = etRegisterEmail.getText().toString().trim();
                    String password = etRegisterPassword.getText().toString().trim();

                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT);
                                // direct user to MainActivity
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
            }
        });

        //loginActivity button goes to login page
        tvLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpViews() {
        tvRegister = findViewById(R.id.tvRegister);
        tvLoginActivity = findViewById(R.id.tvLoginActivity);
        etName = findViewById(R.id.etName);
        etRegisterPhoneNumber = findViewById(R.id.etRegisterPhoneNumber);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private boolean validate() {
        Boolean result = false;

        String name = etName.getText().toString();
        String number = etRegisterPhoneNumber.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();

        if (name.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please make sure all boxes are filled", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
