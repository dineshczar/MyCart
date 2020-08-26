package com.example.mycart;

import java.io.Serializable;

public class WishlistProduct implements Serializable {
    private String imgname;
    private String shoeName;
    private String shoePrice;


    public WishlistProduct(String imageResourceId, String productName, String productPrice) {
        this.imgname = imageResourceId;
        this.shoeName = productName;
        this.shoePrice = productPrice;

    }





    public String getImgname() {
        return imgname;
    }

    public String getShoeName() {
        return shoeName;
    }

    public String getShoePrice() {
        return shoePrice;
    }



}

