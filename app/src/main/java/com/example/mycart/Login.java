package com.example.mycart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycart.utils.PrefsKeys;
import com.example.mycart.utils.PrefsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Login Class for the application
 * Authentication using firebase authentication using email and password.
 *
 */
public class  Login extends Activity {

    ProgressDialog progressDialog;
    EditText emailTextInputLayout, passTextInputLayout;
    DatabaseReference mDatabaseReference;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actovity_login);
        //this.setTitle("Login");

        emailTextInputLayout = findViewById(R.id.tvuserId);
        passTextInputLayout = findViewById(R.id.tvpassword);
        progressDialog = new ProgressDialog(this);
        mauth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
       new  PrefsManager(this).saveBoolean(PrefsKeys.LoginStatue, true);
    }

    //----SHOWING ALERT DIALOG FOR EXITING THE APP----

    /**
     * onBackPressed() method to logout when you press the back button
     * @params none
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setMessage("Really Exit ??");
        builder.setTitle("Exit");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new MyListener());
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

    public void buttonIsClicked(View view) {

        switch (view.getId()) {

            case R.id.buttonSign:

                String email = emailTextInputLayout.getText().toString().trim();
                String password = passTextInputLayout.getText().toString().trim();

                //---CHECKING IF EMAIL AND PASSWORD IS NOT EMPTY----
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Please Fill all blocks", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setTitle("Logging in");
                progressDialog.setMessage("Please wait while we are checking the credentials..");
                progressDialog.setCancelable(false);
                progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                login_user(email, password);
                break;

            case R.id.buttonRegister:

                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /**
     * Method name login_user utilise the firebase authentication method for authenticating the email and password
     * @param email
     * @param password
     */
    private void login_user(String email, String password) {

        //---SIGN IN FOR THE AUTHENTICATE EMAIL-----
        mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            //---ADDING DEVICE TOKEN ID AND SET ONLINE TO BE TRUE---
                            //---DEVICE TOKEN IS USED FOR SENDING NOTIFICATION----
                            String user_id = mauth.getCurrentUser().getUid();
                            // String token_id = FirebaseInstanceId.getInstance().getToken();
                            Map addValue = new HashMap();
                            // addValue.put("device_token", token_id);
                            addValue.put("online", "true");

                            //---IF UPDATE IS SUCCESSFULL , THEN OPEN MAIN ACTIVITY---
                            mDatabaseReference.child(user_id).updateChildren(addValue, new DatabaseReference.CompletionListener() {

                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                                    if (databaseError == null) {

                                        //---OPENING MAIN ACTIVITY---
                                        Log.e("Login : ", "Logged in Successfully");
                                        Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, databaseError.toString()  , Toast.LENGTH_SHORT).show();
                                        Log.e("Error is : ", databaseError.toString());

                                    }
                                }
                            });


                        } else {
                            //---IF AUTHENTICATION IS WRONG----
                            Toast.makeText(Login.this, "Wrong Credentials" +
                                    "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public class MyListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    }
}