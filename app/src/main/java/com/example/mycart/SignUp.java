package com.example.mycart;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUp  extends Activity implements AdapterView.OnItemSelectedListener {

        FirebaseAuth mauth;
        EditText etdisplayname,etemail,etpassword,edconpassword;
        Button buttonsubmit;
        ProgressDialog progressDialog;
private DatabaseReference mDatabase;

        String[] country = { "Admin", "User"};
private String mString="";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        //this.setTitle("Register");

        etemail=(EditText)findViewById(R.id.edemail);
        etpassword=(EditText)findViewById(R.id.edpassword);
    edconpassword=(EditText)findViewById(R.id.edconpassword);
        buttonsubmit=(Button)findViewById(R.id.btnRegister);
        // edServiseType=(EditText) findViewById(R.id.servicetype);
        progressDialog=new ProgressDialog(SignUp.this);

        mauth= FirebaseAuth.getInstance();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

        //setspinner();
        }
@Override
public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        mString=country[position];

        }
@Override
public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        }



//-----REGISTER BUTTON IS PRESSED---
public void buttonIsClicked(View view){

        if(view.getId()==R.id.btnRegister){

       // String displayname=etdisplayname.getText().toString().trim();
        String email=etemail.getText().toString().trim();
        String password=etpassword.getText().toString().trim();

        //----CHECKING THE EMPTINESS OF THE EDITTEXT-----


        if(email.equals("")){
        Toast.makeText(SignUp.this, "Please Fill the email", Toast.LENGTH_SHORT).show();
        return ;
        }

        if(password.length()<6){
        Toast.makeText(SignUp.this, "Password is too short", Toast.LENGTH_SHORT).show();
        return;
        }


        progressDialog.setTitle("Registering User");
        progressDialog.setMessage("Please wait while we are creating your account... ");
        progressDialog.setCancelable(false);
        progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        register_user("Abc",email,password);

        }
        }

private void savedata() {
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
final String uid = current_user.getUid();
        // String token_id = FirebaseInstanceId.getInstance().getToken();
        Map userMap = new HashMap();

        userMap.put("name", etdisplayname.getText().toString());
        userMap.put("status", etemail.getText().toString());
        userMap.put("image", etpassword.getText().toString());


        mDatabase.child(uid).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task1) {
        if (task1.isSuccessful()) {

        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "New User is created", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUp.this, MainActivity.class);

        //----REMOVING THE LOGIN ACTIVITY FROM THE QUEUE----
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        } else {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "ERROR REGISTERING USER....", Toast.LENGTH_SHORT).show();
        }
        }
        });

        }
//-----REGISTERING THE NEW USER------
private void register_user(final String displayname, String email, String password) {

        mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {

        //------IF USER IS SUCCESSFULLY REGISTERED-----
        if(task.isSuccessful()){

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
final String uid=current_user.getUid();
        // String token_id = FirebaseInstanceId.getInstance().getToken();
        Map userMap=new HashMap();
        userMap.put("UserType",mString);
        userMap.put("name",displayname);
        userMap.put("status","Hello ");
        userMap.put("image","default");
        userMap.put("online","true");

        mDatabase.child(uid).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task1) {
        if(task1.isSuccessful()){

        progressDialog.dismiss();
        //Toast.makeText(getApplicationContext(), "New User is created", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(SignUp.this, MainActivity.class);

        //----REMOVING THE LOGIN ACTIVITY FROM THE QUEUE----
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();



        }
        else{

        Toast.makeText(SignUp.this, "YOUR NAME IS NOT REGISTERED... MAKE NEW ACCOUNT-- ", Toast.LENGTH_SHORT).show();

        }

        }
        });


        }
        //---ERROR IN ACCOUNT CREATING OF NEW USER---
        else{
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "ERROR REGISTERING USER....", Toast.LENGTH_SHORT).show();
        }
        }
        });
        }

        }


