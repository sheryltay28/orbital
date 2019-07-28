package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orbital.saveme.model.Budget;
import com.orbital.saveme.model.User;

public class HomePageActivity extends AppCompatActivity {
    private ImageButton petButton, entryButton, settingButton;
    private Button userNameText, entryText, amtSavedButton, amtSavedDataButton, amtRemainButton,
            amtRemainDataButton, incomeButton, incomeDataButton, expenditureButton,
            expenditureDataButton;

    private FirebaseUser mUser;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        setUpBtn();
        updateInformation();

        clickPet();
        clickDataEntry();
        clickStatistics();
        clickSetting();
    }

    public void setUpBtn() {
        petButton = findViewById(R.id.btnPet);
        userNameText = findViewById(R.id.btnUserName);
        entryButton = findViewById(R.id.btnEntry);
        entryText = findViewById(R.id.btnEntryText);
        amtSavedButton = findViewById(R.id.btnAmtSaved);
        amtSavedDataButton = findViewById(R.id.btnAmtSavedData);
        amtRemainButton = findViewById(R.id.btnAmtRemain);
        amtRemainDataButton = findViewById(R.id.btnAmtRemainData);
        incomeButton = findViewById(R.id.btnIncome);
        incomeDataButton = findViewById(R.id.btnIncomeData);
        expenditureButton = findViewById(R.id.btnExpenditure);
        expenditureDataButton = findViewById(R.id.btnExpenditureData);
        settingButton = findViewById(R.id.btnSetting);
    }

    public void updateInformation() {
        updateUserName();
        updateAmtSaved();
        updateAmtRemain();
    }

    public void updateUserName() {
        DatabaseReference name = mReference.child(mUser.getUid()).child("INFORMATION");
        name.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userNameText.setText(user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // wrong value
    public void updateAmtSaved() {
        DatabaseReference amtSaved = mReference.child(mUser.getUid()).child("BUDGET");
        amtSaved.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Budget budget = dataSnapshot.getValue(Budget.class);
                double savingBudget = budget.getSavingsBudget();
                amtSavedDataButton.setText(Double.toString(savingBudget));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateAmtRemain() {
        DatabaseReference amtSaved = mReference.child(mUser.getUid()).child("BUDGET");
        amtSaved.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Budget budget = dataSnapshot.getValue(Budget.class);
                amtRemainDataButton.setText(Double.toString(budget.getSpendingBudget()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void clickPet() {
        petButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetActivity();
            }
        });
    }

    public void openPetActivity() {
        Intent i = new Intent(HomePageActivity.this, PetActivity.class);
        startActivity(i);
    }

    public void clickDataEntry() {
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeExpenditure();
            }
        });

        entryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeExpenditure();
            }
        });
    }

    public void openIncomeExpenditure() {
        Intent i = new Intent(HomePageActivity.this,
                IncomeExpenditureHomepageActivity.class);
        startActivity(i);
    }

    public void clickStatistics() {
        amtSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        amtSavedDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        amtRemainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        amtRemainDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        incomeDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        expenditureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });

        expenditureDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });
    }

    public void openStatistics() {
        Intent i = new Intent(HomePageActivity.this, StatisticsActivity.class);
        startActivity(i);
    }

    public void clickSetting() {
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetting();
            }
        });
    }

    public void openSetting() {
        Intent i = new Intent(HomePageActivity.this, MenuBarActivity.class);
        startActivity(i);
    }

}