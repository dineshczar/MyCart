package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
 Button mButton;
 TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actovity_login);
        mButton=(Button)findViewById(R.id.btnLogin);
        mTextView=(TextView) findViewById(R.id.tvSignUp);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent=new Intent(Login.this, MainActivity.class);
                startActivity(mIntent);
            //    Utility.LoginSymbol=true;
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          /*      Intent mIntent=new Intent(Login.this, SignUp.class);
                startActivity(mIntent);
*/

            }
        });
    }
}