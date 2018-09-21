package com.example.android.drivewithme;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private static final String TAG = Login.class.getSimpleName();
    private EditText email, password;
    private TextView register;
    private TextView loginError;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }

        firebaseAuth = ((FirebaseApplication)getApplication()).getFirebaseAuth();
        ((FirebaseApplication)getApplication()).checkUserLogin(Login.this);

        loginError = findViewById(R.id.loginerror);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Login.this, Register.class);
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
                    Helper.displayMessageToast(Login.this, "Email address and password should be entered");
                    return;
                }
                if(!Helper.isEmailValid(enterEmail))
                {
                    Helper.displayMessageToast(Login.this, "invalid email address entered");
                    return;
                }
                ((FirebaseApplication)getApplication()).loginAUser(Login.this, enterEmail, enterPassword,loginError);
            }
        });

        //firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart()
    {
        super.onStart();
    }
    @Override
    public void onStop()
    {
        super.onStop();
        if(((FirebaseApplication)getApplication()).firebaseListerner != null)
        {

        }
    }

    public void login_Click(View v)
    {
        final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please wait...", "Processing", true);

        (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, HomeScreen.class);
                            i.putExtra("Name", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        }
                        else
                        {
                            Log.e("User not Registered ", task.getException().toString());
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
