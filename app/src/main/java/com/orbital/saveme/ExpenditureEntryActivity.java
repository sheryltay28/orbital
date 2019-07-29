package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orbital.saveme.model.Transaction;
import com.orbital.saveme.model.User;

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

        setUpBtn();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void setUpBtn() {
        etExpenditureType = findViewById(R.id.etExpenditureType);
        etAmount = findViewById(R.id.etAmount);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void submit() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();


        final String expenditureType = etExpenditureType.getText().toString();
        final double amount = Double.parseDouble(etAmount.getText().toString());
        final DatabaseReference eReference = mReference.child(mUser.getUid()).child("INFORMATION");
        eReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                double expenditure = user.getExpenditure();
                expenditure += amount;
                user.setExpenditure(expenditure);
                eReference.setValue(user);
                Date date = new Date();
                Transaction expenditureT = new Transaction("EXPENDITURE", expenditureType,
                        amount, date);

                mReference.child(mUser.getUid()).child("TRANSACTIONS").child(Long.toString(date.getTime()))
                        .setValue(expenditureT);
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
