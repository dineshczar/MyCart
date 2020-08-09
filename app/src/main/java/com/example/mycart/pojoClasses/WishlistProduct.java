package com.example.mycart.pojoClasses;

import java.io.Serializable;

public class WishlistProduct implements Serializable {
    private int imgname;
    private String shoeName;
    private String shoePrice;


    public WishlistProduct(int imageResourceId, String productName, String productPrice) {
        this.imgname = imageResourceId;
        this.shoeName = productName;
        this.shoePrice = productPrice;

    }





    public int getImgname() {
        return imgname;
    }

    public String getShoeName() {
        return shoeName;
    }

    public String getShoePrice() {
        return shoePrice;
    }



}

