package com.example.taefinalproject1.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class MyService extends IntentService {

    public static final String TAG = MyService.class.getSimpleName();
    /*
    Following the single responsibility principle, this class handles two classes, IntentMaker and IntentHandler.

    It can't interact directly with your user interface. To put its results in the UI, you have to send them to an Activity.

    Work requests run sequentially. If an operation is running in an IntentService, and you send it another request, the request waits until the first operation is finished.

    An operation running on an IntentService can't be interrupted.
     */

    public MyService() {
        super(TAG);
    }



    public static Intent makeIntent(Context context, String paramss){
        // TODO: 20/01/2016 handle the creation of Intents; defer responsibility to the appropriate class.
        Intent intent = new Intent(context, MyService.class);
        // TODO: 20/01/2016 Implement make intent properly
        intent.setAction("dwdawda");
        intent.putExtra(paramss, "awdawda");
        return intent;
    }

    public static Intent makeIntent(Context context, String key1, String val1, String key2, String val2){
        Intent intent = new Intent(context, MyService.class);
        // TODO: 20/01/2016 Implement make intent properly
        intent.setAction("awdawdadw");
        intent.putExtra(key1, val1);
        intent.putExtra(key2, val2);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO: 20/01/2016 handle the handling of Intents; defer responsibility to the appropriate class.
        IntentHandler intentHandler = new IntentHandler(intent, getApplicationContext());
    }
}
