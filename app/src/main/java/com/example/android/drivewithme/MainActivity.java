package com.example.android.drivewithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button TnC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TnC = (Button) findViewById(R.id.tc);
        TnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreen();
            }
        });
    }

    public void HomeScreen()
    {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
