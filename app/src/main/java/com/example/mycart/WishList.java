package com.example.mycart;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * This class is to add products to my wishlist page.
 * fetch the products to my wishlist page.
 *
 */
public class WishList extends AppCompatActivity {
    wishlistadapter wishlistadapter;
    Button mButton;
    FrameLayout mFrameLayoutmainlayout,fmContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.getWindow().setFlags(WindowManager.LayoutParams.FLA, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.wishlist);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        mButton = findViewById(R.id.pay);
        wishlistadapter = new wishlistadapter(this,Utility.products);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));
        recyclerViewProducts.setAdapter(wishlistadapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(WishList.this, "Please go to Product detail page and order from there.",  Toast.LENGTH_LONG).show();
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(WishList.this, "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


    }
}
