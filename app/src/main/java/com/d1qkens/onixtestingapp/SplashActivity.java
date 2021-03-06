package com.d1qkens.onixtestingapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.d1qkens.onixtestingapp.Utils.ParseJson;
import com.d1qkens.onixtestingapp.Utils.SaveSharedPreferences;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;


public class SplashActivity extends Activity {
    private static final String URL = "https://www.dropbox.com/s/hid3nun8jbpmcxn/batman.json?dl=1";
    public static final String IMAGES = "images";
    public static final String TITLES = "titles";
    private ArrayList<String> carsList;
    private ArrayList<String> titlesList;
    private static boolean splashScreenLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (!splashScreenLoaded) {
            setContentView(R.layout.activity_splash);
            int SPLASH_SCREEN_LENGTH = 3000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                }
            }, SPLASH_SCREEN_LENGTH);
            splashScreenLoaded = true;
        } else {
            if (SaveSharedPreferences.getUserName(this).length() == 0) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(URL, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        carsList = ParseJson.getCars(response);
                        titlesList = ParseJson.getTitles(response);

                        Intent intent = new Intent(SplashActivity.this, ListItemsActivity.class);
                        intent.putStringArrayListExtra(IMAGES, carsList);
                        intent.putStringArrayListExtra(TITLES, titlesList);
                        startActivity(intent);
                    }
                });
            }


        }
    }
}


