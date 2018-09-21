package com.example.android.drivewithme;

import android.content.Context;
import android.widget.Toast;

public class Helper {

    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Email";
    public static final int ADD_PICTURE = 2000;

    public static boolean isEmailValid(String email)
    {
        if(email.contains("@"))
        {
            return true;
        }
        return false;
    }

    public static void displayMessageToast(Context context, String displayMessage)
    {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }


}
