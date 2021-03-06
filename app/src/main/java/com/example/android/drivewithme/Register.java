package com.example.android.drivewithme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private static final String TAG = Register.class.getSimpleName();
    private EditText name, surname, email, password, confirmpassword;
    private TextView login,loginError;
    private Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }

        firebaseAuth = ((FirebaseApplication)getApplication()).getFirebaseAuth();
        ((FirebaseApplication)getApplication()).checkUserLogin(Register.this);

        loginError = findViewById(R.id.loginerror);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        final Button login =  findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String enterEmail = email.getText().toString();
                String enterPassword = password.getText().toString();

                if(TextUtils.isEmpty(enterEmail) || TextUtils.isEmpty(enterPassword))
                {
                    Helper.displayMessageToast(Register.this, "Email address and password should be entered");
                    return;
                }
                if(!Helper.isEmailValid(enterEmail))
                {
                    Helper.displayMessageToast(Register.this, "invalid email address entered");
                    return;
                }
                ((FirebaseApplication)getApplication()).createNewUser(Register.this, enterEmail, enterPassword,loginError);
            }
        });

        //firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register_Click(View v)
    {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }
}
