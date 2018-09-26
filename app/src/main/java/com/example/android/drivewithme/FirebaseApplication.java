package com.example.android.drivewithme;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseApplication extends Application {
    private static final String TAG = FirebaseApplication.class.getSimpleName();
    public FirebaseAuth firebaseAuth;
    public FirebaseAuth.AuthStateListener firebaseListerner;

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth = FirebaseAuth.getInstance();
    }

   public String getFirebaseUserAuthenticatedId()
    {
        String userId = null;
        /*if(firebaseAuth.getCurrentUser() != null)
        {
            userId = firebaseAuth.getCurrentUser().getUid();
        }*/
        return userId;
    }

    public void checkUserLogin(final Context context)
    {
       /* if(firebaseAuth.getCurrentUser() != null)
        {
            Intent i = new Intent(context, HomeScreen.class);
            context.startActivity(i);
        }*/
    }

    public void isUserCurrentlyLogin(final Context context)
    {
        firebaseListerner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(null != user)
                {
                    Intent i = new Intent(context, HomeScreen.class);
                    context.startActivity(i);
                }
                else
                {
                    Intent i = new Intent(context, Login.class);
                    context.startActivity(i);
                }
            }
        };
    }

    public void createNewUser(Context context, String email, String password, final TextView errorMessage)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(!task.isSuccessful())
                        {
                            errorMessage.setText("Failed to login. Invalid user");
                        }
                    }
                });
    }

    public void loginAUser(final Context context, String email, String password, final TextView errorMessage)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            errorMessage.setText("Failed to login");
                        }
                        else
                        {
                            Helper.displayMessageToast(context, "User has been login");
                            Intent i = new Intent(context, HomeScreen.class);
                            context.startActivity(i);
                        }
                    }
                });
    }
}
