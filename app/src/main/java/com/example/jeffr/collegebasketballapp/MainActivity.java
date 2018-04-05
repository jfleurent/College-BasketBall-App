package com.example.jeffr.collegebasketballapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.start_button);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_gray));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BasketballLeagueHomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
