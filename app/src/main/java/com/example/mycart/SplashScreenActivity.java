package com.example.mycart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mycart.utils.PrefsKeys;
import com.example.mycart.utils.PrefsManager;


public class SplashScreenActivity extends Activity {
    boolean isLoggedIn;
    private PrefsManager prefsManager;
    //private MarshmallowPermissions marshmallowPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        prefsManager = new PrefsManager(this);
        //setUp();
        handleSplashThread();
    }


    public void handleSplashThread() {
      /*  final boolean isAppOpenFirstTime = prefsManager.getBoolean(PrefsKeys.IS_APP_OPENED_FIRST_TIME, true);
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
                    checkLogin();

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

    public void checkLogin() {
        isLoggedIn = prefsManager.getBoolean(PrefsKeys.LoginStatue, false);
        if (isLoggedIn==false) {
            this.startActivity(new Intent(this, Login.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            this.finish();
            return;
        }
        this.startActivity(new Intent(this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        this.finish();
    }
}
