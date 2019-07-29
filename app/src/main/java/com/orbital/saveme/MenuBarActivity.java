package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuBarActivity extends AppCompatActivity {
    private Button btnLogout, btnUpdate;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);

        firebaseAuth = FirebaseAuth.getInstance();
        setUpViews();

        // logout button goes to MainActivity
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MenuBarActivity.this, MainActivity.class));
            }
        });

        clickUpdate();
    }

    private void setUpViews() {
        btnLogout = findViewById(R.id.btnLogout);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void clickUpdate() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openBasicInfoActivity();
            }
        });
    }

    public void openBasicInfoActivity() {
        Intent i = new Intent(MenuBarActivity.this, BasicInfomationActivity.class);
        startActivity(i);
    }
}
