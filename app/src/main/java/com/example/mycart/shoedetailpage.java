package com.example.mycart;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mycart.utils.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class shoedetailpage extends AppCompatActivity implements Serializable, View.OnClickListener {
    TextView mTextView,tvProductDetailPrice;
    ImageView mImageView;
    Button mButtonbtnaddtocart,buynow;
    private Users currentProduct;
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();

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
        currentProduct = (Users) i.getSerializableExtra("ShoeModel");
        Picasso.with(this).load(currentProduct.getState()).into(mImageView);
        mTextView.setText(currentProduct.getName());
        tvProductDetailPrice.setText(""+currentProduct.getCity());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnaddtocart:
                WishlistProduct product = new WishlistProduct(currentProduct.getState(),
                        currentProduct.getName(),
                        currentProduct.getCity());
                Utility.products.add(product);
                Toast.makeText(this,"Product Added To WishList Successfully",Toast.LENGTH_SHORT).show();
                break;
                case R.id.buynow:
                    try {
//                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.paypal.com/ca/home")));
                        Map<String, Object> buyProduct = new HashMap<>();
                        buyProduct.put("Item Name", currentProduct.getName());
                        buyProduct.put("Price", currentProduct.getCity());
                        fstore.collection("Orders")
                                .document("OID").set(buyProduct)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(),
                                                "Your order has been placed. " +
                                                        "Thank you for shopping with us.",  Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("order", "Error writing document", e);
                            }
                        });
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, "No application can handle this request."
                                + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    break;
        }
    }
}

