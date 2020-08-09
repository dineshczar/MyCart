package com.example.mycart.pojoClasses;

import java.io.Serializable;

public class homemodel implements Serializable {
    private int imgname;
    private String shoeName;
    private String shoePrice;


    public homemodel(int imageResourceId, String productName, String productPrice) {
        this.imgname = imageResourceId;
        this.shoeName = productName;
        this.shoePrice = productPrice;

    }

    public homemodel() {
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
