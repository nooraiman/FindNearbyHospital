package com.example.findnearbyhospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        home = (Button) findViewById(R.id.btnHome2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });
    }

    public void openDashboard(){
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }
}
