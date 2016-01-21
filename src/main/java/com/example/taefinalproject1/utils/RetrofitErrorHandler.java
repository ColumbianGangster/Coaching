package com.example.taefinalproject1.utils;

import android.content.Context;
import android.widget.Toast;

import retrofit.RetrofitError;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class RetrofitErrorHandler {
    /*
    This class supports single responsibility design.
     */
    Context mContext;
    public RetrofitErrorHandler(Context context, RetrofitError error){
        mContext = context;
        handleError(error);
        /*
        Coding it this way means construction is behaviour
         */
    }
    private void handleError(RetrofitError error){
        CharSequence text = "Unknown Error";
        int duration = Toast.LENGTH_SHORT;
        if (error.getKind().equals(retrofit.RetrofitError.Kind.HTTP)) {
            text = "RetrofitErrorHandler.Kind.Http";
        } else if (error.getKind().equals(retrofit.RetrofitError.Kind.CONVERSION)) {
            text = "RetrofitErrorHandler.Kind.Conversion";
        } else if (error.getKind().equals(retrofit.RetrofitError.Kind.NETWORK)) {
            text = "RetrofitErrorHandler.Kind.Network";
        } else if (error.getKind().equals(retrofit.RetrofitError.Kind.UNEXPECTED)) {
            text = "RetrofitErrorHandler.Kind.Unexpected";
        }
        Toast.makeText(mContext, text, duration).show();
    }
}
