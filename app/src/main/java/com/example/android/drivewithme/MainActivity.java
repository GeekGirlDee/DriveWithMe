package com.example.android.drivewithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button TnC;
    private TextView term;
    private TextView privacy;
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        privacy = (TextView) findViewById(R.id.privacy);
        privacy.setMovementMethod(LinkMovementMethod.getInstance());

        TnC = (Button) findViewById(R.id.tc);
        TnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreen();
            }
        });
    }

    //term = (TextView) findViewById(R.id.terms);
    public void HomeScreen()
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
