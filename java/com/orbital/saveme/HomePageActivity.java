package com.orbital.saveme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class HomePageActivity extends AppCompatActivity {
    private ImageButton petButton, expenditureButton, menuBarButton, statisticsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        setUpBtn();

        clickPet();
        clickExpenditure();
        clickMenuBar();
        clickStatistics();
    }

    public void setUpBtn() {
        petButton = findViewById(R.id.btnRabbit);
        expenditureButton = findViewById(R.id.btnIncomeExpenditure);
        menuBarButton = findViewById(R.id.btnMenuBar);
        statisticsButton = findViewById(R.id.btnStatistics);
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

    public void clickExpenditure() {
        expenditureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeExpenditure();
            }
        });
    }

    public void openIncomeExpenditure() {
        Intent i = new Intent(HomePageActivity.this, ExpenditureActivity.class);
        startActivity(i);
    }

    public void clickMenuBar() {
        menuBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuBar();
            }
        });
    }

    public void openMenuBar() {
        Intent i = new Intent(HomePageActivity.this, MenuBarActivity.class);
        startActivity(i);
    }

    public void clickStatistics() {
        statisticsButton.setOnClickListener(new View.OnClickListener() {
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
}
