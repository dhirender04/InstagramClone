package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;


public class App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0bnBTVvIMTVp82v0BzGFY35XX9He9XD0fOdtv0Mp")
                .clientKey("lHBs3dOW4QADTcX49lTDCGEi74HCW4S1VRQMdvTJ")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
