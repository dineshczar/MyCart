package com.mycart;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mycart.pojoClasses.WishlistProduct;
import com.mycart.pojoClasses.homemodel;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class shoedetailpage extends AppCompatActivity implements Serializable, View.OnClickListener {
    TextView mTextView,tvProductDetailPrice;
    ImageView mImageView;
    Button mButtonbtnaddtocart,buynow;
    private homemodel currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoedetail);
        mImageView=(ImageView)findViewById(R.id.ivProductDetailImage) ;
        mTextView=(TextView) findViewById(R.id.tvProductDetailName) ;
        tvProductDetailPrice=(TextView) findViewById(R.id.tvProductDetailPrice) ;
        mButtonbtnaddtocart=(Button) findViewById(R.id.btnaddtocart) ;
        buynow=(Button) findViewById(R.id.buynow) ;


        mButtonbtnaddtocart.setOnClickListener(this);
        buynow.setOnClickListener(this);
        Intent i = getIntent();
        currentProduct = (homemodel)i.getSerializableExtra("ShoeModel");
        Picasso.with(this).load(currentProduct.getImgname()).into(mImageView);
        mTextView.setText(currentProduct.getShoeName());
        tvProductDetailPrice.setText(""+currentProduct.getShoePrice());
        // mButton=(Button)findViewById(R.id.btnLogin);
       /* mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mIntent);
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnaddtocart:
                WishlistProduct product = new WishlistProduct(currentProduct.getImgname(),
                        currentProduct.getShoeName(),
                        currentProduct.getShoePrice());
                Utility.products.add(product);
                Toast.makeText(this,"Product Added To WishList Successfully",Toast.LENGTH_SHORT).show();
                break;

                case R.id.buynow:
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.paypal.com/ca/home")));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, "No application can handle this request."
                                + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    break;
        }
    }
}

