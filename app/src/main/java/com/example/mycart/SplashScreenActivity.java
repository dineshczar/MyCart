package com.example.mycart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class SplashScreenActivity extends Activity {
    boolean isLoggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        handleSplashThread();
    }



    private void handleSplashThread() {
       /* final boolean isAppOpenFirstTime = prefsManager.getBoolean(PrefsKeys.IS_APP_OPENED_FIRST_TIME, true);
        if (isAppOpenFirstTime) {
            prefsManager.saveBoolean(PrefsKeys.IS_APP_OPENED_FIRST_TIME, false);
            if (!marshmallowPermission.requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION)) {
                return;
            }
        }*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    /*if (!isInActivity) {
                        return;
                    }*/

                    Intent mIntent=new Intent(SplashScreenActivity.this,

                            Utility.LoginSymbol==false? Login.class: MainActivity.class);
                    startActivity(mIntent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        isInActivity = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
//        isInActivity = false;
    }


}
