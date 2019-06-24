package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class IncomeExpenditureHomepageActivity extends AppCompatActivity {
    private ImageButton incomeButton, expenditureButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expenditure_homepage);

        setUpBtn();
        clickBack();
        clickIncome();
        clickExpenditure();
    }

    public void setUpBtn() {
        incomeButton = findViewById(R.id.btnIncome);
        expenditureButton = findViewById(R.id.btnExpenditure);
        backButton = findViewById(R.id.btnBack);
    }

    public void clickBack() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
    }

    public void openHomePage() {
        Intent i = new Intent(IncomeExpenditureHomepageActivity.this,
                HomePageActivity.class);
        startActivity(i);
    }

    public void clickIncome() {
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeEntryPage();
            }
        });
    }

    public void openIncomeEntryPage() {
        Intent i = new Intent(IncomeExpenditureHomepageActivity.this,
                IncomeEntryActivity.class);
        startActivity(i);
    }

    public void clickExpenditure() {
        expenditureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpenditureEntryPage();
            }
        });
    }

    public void openExpenditureEntryPage() {
        Intent i = new Intent(IncomeExpenditureHomepageActivity.this,
                ExpenditureEntryActivity.class);
        startActivity(i);
    }

}
