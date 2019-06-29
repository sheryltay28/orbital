package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orbital.saveme.model.Expenditure;

import java.util.Date;

public class ExpenditureEntryActivity extends AppCompatActivity {

    private EditText etExpenditureType;
    private EditText etAmount;
    private Button btnSubmit;

    private FirebaseUser mUser;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_entry);
        etExpenditureType = findViewById(R.id.etExpenditureType);
        etAmount = findViewById(R.id.etAmount);
        btnSubmit = findViewById(R.id.btnSubmit);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        String expenditureType = etExpenditureType.getText().toString();
        double amount = Double.parseDouble(etAmount.getText().toString());
        String date = new Date().toString();
        Expenditure expenditure = new Expenditure(expenditureType, amount);
        mReference.child("expenditures").child(mUser.getUid()).child(date).setValue(expenditure);
        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }
}
