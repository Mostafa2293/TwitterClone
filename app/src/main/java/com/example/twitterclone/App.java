package com.example.twitterclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("7wG3nsGC6KObAyDooF2xgRoZsYZ8Dn11s2bWosPE")
                // if defined
                .clientKey("4Tys8sFI1LRIi57PdaireYSK0ZgkDAfiHXf1DEXJ")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
