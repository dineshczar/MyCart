package com.example.mycart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    RecyclerView shoeGridview;
    private homeadapter mAdapter;
    int[] staticimages = {R.mipmap.formal, R.mipmap.addas, R.mipmap.newshoe, R.mipmap.sneekers};
Button wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shoeGridview = findViewById(R.id.recyclerViewProducts);
        wishlist = findViewById(R.id.wishlist);

        loadData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,
                2,//span count no of items in single row
                GridLayoutManager.VERTICAL,//Orientation
                false);//reverse scrolling of recyclerview
        //set layout manager as gridLayoutManager
        shoeGridview.setLayoutManager(gridLayoutManager);
        shoeGridview.setAdapter(mAdapter);
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if(Utility.products.size()>0) {
                 //   Intent mIntent = new Intent(MainActivity.this, WishList.class);
                   // startActivity(mIntent);
                //}
            }
        });
    }

    private void loadData() {
        String[] shoeName = {"Formal Wear", "Adidas Shoes", "ShoeTree", "Sneekers Shoes"};
        String[] shoePrice = {"$50", "$70", "$200", "$199"};
        List<homemodel> homemodelArrayList = new ArrayList<>();
        for (int i = 0; i < staticimages.length; i++) {
            homemodel mHomemodel = new homemodel(staticimages[i], shoeName[i], shoePrice[i]);
            homemodelArrayList.add(mHomemodel);
        }
        mAdapter = new homeadapter(this,homemodelArrayList);
    }
}
