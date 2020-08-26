package com.example.mycart.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Commonparams {

    public final static SimpleDateFormat api_sdf_from = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public final static SimpleDateFormat api_sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    public final static SimpleDateFormat Value_sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
    public static final String RESULT = "result";

    public static final String YES = "YES";
    public static final String NO = "NO";


    public static String FirebasePassword="a@12345";
    public static boolean mRetrofitbool=true;
    public static String IMAGE="Image";
    public static String APP_NAME="Mantors";
    public static int CAMERA=1;
    public static final int CAMERA_REQUEST = 1888;
    public static int GALLARY=2;
    public static int REQUEST_IMAGE_CAPTURE =1111;





    public static String Column_Institute="Institute";
    public static String Column_City="City";
    public static String UserType="";


    public static class Tables {
        public static String Users="users";

        }

}
