package com.example.mycart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.utils.Commonparams;
import com.example.mycart.utils.Sport;
import com.example.mycart.utils.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {
    RecyclerView shoeGridview;
    private homeadapter mAdapter;
    DatabaseReference mDatabaseReference;
    List<Users> homemodelArrayList = new ArrayList<>();
    int[] staticimages = {R.mipmap.formal, R.mipmap.addas, R.mipmap.newshoe, R.mipmap.sneekers};
Button wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shoeGridview = findViewById(R.id.recyclerViewProducts);
        wishlist = findViewById(R.id.wishlist);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        loadData();
        @SuppressLint("WrongConstant") GridLayoutManager gridLayoutManager = new GridLayoutManager(this,
                2,//span count no of items in single row
                GridLayoutManager.VERTICAL,//Orientation
                false);//reverse scrolling of recyclerview
        //set layout manager as gridLayoutManager
        shoeGridview.setLayoutManager(gridLayoutManager);
        shoeGridview.setAdapter(mAdapter);
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utility.products.size()>0) {
                    Intent mIntent = new Intent(MainActivity.this, WishList.class);
                    startActivity(mIntent);
                }
            }
        });
    }

    private void loadData() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {

                    DataSnapshot next = iterator.next();
                    // Cars pojo = next.getValue(Cars.class);
                    //Toast.makeText(getActivity(),pojo.getName(),Toast.LENGTH_SHORT).show();
                    // if(pojo.getInstitute().equalsIgnoreCase("Guru Nanak Dev University College Verka")){


                    Users mUsers =
                            new Users((String) next.child("name").getValue(),
                                    (String) next.child("image").getValue(),
                                    (String) next.child("status").getValue(),
                                    (String) next.child("online").getValue());
                    homemodelArrayList.add(mUsers);

                    // }
                }
                mAdapter = new homeadapter(MainActivity.this,homemodelArrayList);
                shoeGridview.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setMessage("Do You Want to Logout ?")
                .setCancelable(false)
                .setPositiveButton(Commonparams.YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //aemPrinter = null;
                        finish();
                        Intent mIntent=new Intent(MainActivity.this, Login.class);
                        startActivity(mIntent);
                    }
                }).setNegativeButton(Commonparams.NO, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
