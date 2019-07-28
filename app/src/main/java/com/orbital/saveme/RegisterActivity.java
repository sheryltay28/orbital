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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orbital.saveme.model.User;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvRegister, tvLoginActivity;
    private EditText etName, etRegisterPhoneNumber, etRegisterEmail, etRegisterPassword, etRegisterPasswordAgain;
    private Button btnRegister;
    private FirebaseAuth firebaseAuth;
    private String name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();

        // if all boxes are filled up, register button goes to MainActivity, else make toast
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
                                uploadUserData();
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                // direct user to MainActivity
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String username = user.getDisplayName();
                                Intent i = new Intent(RegisterActivity.this, BasicInfomationActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        // loginActivity button goes to login page
        tvLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpViews() {
        tvRegister = findViewById(R.id.tvRegister2);
        tvLoginActivity = findViewById(R.id.tvLoginActivity);
        etName = findViewById(R.id.etName);
        etRegisterPhoneNumber = findViewById(R.id.etRegisterPhoneNumber);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterPasswordAgain = findViewById(R.id.etRegisterPasswordAgain);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private boolean validate() {
        Boolean result = false;

        name = etName.getText().toString();
        number = etRegisterPhoneNumber.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();

        if (name.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please make sure all boxes are filled", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(RegisterActivity.this, "Please make sure password has at least 6 characters", Toast.LENGTH_SHORT).show();
        } else if (!samePassword()) {
            Toast.makeText(RegisterActivity.this, "Please make sure both passwords entered are the same", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    private boolean samePassword() {
        Boolean result = false;

        String password = etRegisterPassword.getText().toString();
        String secondPassword = etRegisterPasswordAgain.getText().toString();

        if (password.equals(secondPassword)) {
            result = true;
        }

        return result;
    }

    private void uploadUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        User user = new User(name, number);
        myRef.child("INFORMATION").setValue(user);
    }
}
