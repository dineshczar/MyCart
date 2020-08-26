package com.example.mycart.utils;

import android.content.Intent;

public interface ActivityForResultListner {

    public void getActivityForResult(int requestCode, int resultCode, Intent data);
    public void SetImageBitmapGallery(Intent intent, int value);

    public void SetImageBitmapCamera(Intent intent, String imagePath, int value);
}
