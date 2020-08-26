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
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.paypal.com/ca/home")));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(WishList.this, "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


    }
}
