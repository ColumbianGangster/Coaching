package com.example.taefinalproject1.utils;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

/**
 * Created by mouse on 13/12/2015.
 */
public class Validator {
    // TODO: 18/01/2016 Refactor and implement validator for this project 
    public boolean validateFirstName(String firstname)
    {
        boolean isvalid = false;
        if(!firstname.isEmpty() && !(firstname.length() > 20) && firstname.matches("[a-zA-Z]+")) { isvalid = true; }
        return isvalid;
    }

    public boolean validateLastName(String lastname)
    {
        return validateFirstName(lastname);
    }

    public boolean validateAge(int age)
    {
        boolean isvalid = true;
        if(age < 0 || age > 130) isvalid = false;
        return isvalid;
    }

    /*
    This method provides a false sense of security
     */
    public boolean validateAddress(String address)
    {
        return true;
    }

    public boolean validatePhone(int phonenumber) {
        return PhoneNumberUtils.isGlobalPhoneNumber(Integer.toString(phonenumber));
    }

    public boolean validateEmail(String email)
    {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
