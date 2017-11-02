package com.example.rynel.analytics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean isDownloaded;
    private String MY_TIME_EVENT;


    public static final String MIXPANEL_TOKEN = "9c59401f63988624be46413bed4e8153";

    // Initialize the library with your
// Mixpanel project token, MIXPANEL_TOKEN, and a reference
// to your application context.
    MixpanelAPI mixpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        // TODO: Move this to where you establish a user session
        logUser();


        setContentView(R.layout.activity_main);



        mixpanel = MixpanelAPI.getInstance(this, MIXPANEL_TOKEN);

    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }


    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }


    public void onJump(View view) {

        try {
            JSONObject props = new JSONObject();
            props.put("Gender", "Male");
            props.put("Logged in", true);
            props.put("activity", "Attack");
            mixpanel.track("MainActivity - onAttack called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }

    }


    public void onAttack(View view) {

        try {
            JSONObject props = new JSONObject();
            props.put("Gender", "Female");
            props.put("Logged in", true);
            props.put("activity", "Attack");
            mixpanel.track("MainActivity - onAttack called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }
    }

    public void onStart(View view) {
        mixpanel.timeEvent(MY_TIME_EVENT);
        isDownloaded = true;
    }



    public void onDestroy(View view) {
        mixpanel.flush();
        super.onDestroy();
    }

    public void onRoll(View view) {

        try {
            JSONObject props = new JSONObject();
            props.put("Gender", "Female");
            props.put("Logged in", true);
            props.put("activity", "Attack");
            mixpanel.track("MainActivity - onAttack called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }

    }

    public void onSprint(View view) {

        try {
            JSONObject props = new JSONObject();
            props.put("Gender", "Male");
            props.put("Logged in", true);
            props.put("activity", "Attack");
            mixpanel.track("MainActivity - onAttack called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }

    }

    public void onTrackTime(View view) {

        if(isDownloaded){
            mixpanel.track(MY_TIME_EVENT);
            Log.d(TAG, "onTrackTime: ");
        }

    }
}

