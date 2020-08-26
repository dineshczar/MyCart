package com.example.mycart.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;


public class StringUtils {
    public static CharSequence[] items;
    public static Uri requestedFileUri = null;
    private static int REQ_CAM;

    public String getEncodedString(String message) {
        String enCodedStr = message;
        try {
            byte[] data = message.getBytes("UTF-8");
            enCodedStr = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enCodedStr;
    }

    public String getDeCodeString(String message) {
        String decodedString = message;
        try {
            byte[] msgdata = Base64.decode(message, Base64.DEFAULT);
            decodedString = new String(msgdata, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedString;
    }

    public String getRandomImageName() {

        return UUID.randomUUID().toString() +
                "_a_" +
                System.currentTimeMillis() +
                ".png";
    }

    public boolean isUsernameValid(String username) {
        Pattern emailPattern = Pattern.compile("([A-Za-z0-9_-]{3,15})");
        return emailPattern.matcher(username).matches();
    }

    public static boolean isEmailValid(String email) {
        Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
        return emailPattern.matcher(email).matches();
    }

    public boolean isPasswordValid(String email) {
        Pattern passPattern = Pattern.compile("((?=.*[a-z])(?=.*\\d).{6,16})");
        return passPattern.matcher(email).matches();
    }

    public String FirstLetterOnly_UpperCase(String word) {
        String result = "";
        if (word.length() > 0) {
            char c = word.charAt(0);
            result = Character.toUpperCase(c) + "";
        }
        if (!result.equals("M") && !result.equals("F"))
            result = "N";

        return result;
    }

    public String FirstLetterInUpperCase(String word) {

        String result = "";
        if (word.length() > 0) {
            char c = word.charAt(0);
            String splitedString = word.substring(1, word.length());
            result = Character.toUpperCase(c) + splitedString;
        }
        return result;
    }

    private boolean checkStringEmpty(String word) {
        return word == null || word.trim().length() <= 0;
    }

    public int getIntFromString(String st) {
        return Integer.parseInt(st);
    }

    public String getStringTextAccordingCount(String text, int count) {
        String finalText = text;
        if (count > 1) {
            finalText = text + "s";
        }
        return finalText;
    }

//    public String getGenderFromValue(String gender, Context ctx) {
//        if (gender.equalsIgnoreCase("F")) {
//            return ctx.getResources().getString(R.string.female);
//        } else if (gender.equalsIgnoreCase("M")) {
//            return ctx.getResources().getString(R.string.male);
//        } else {
//            return ctx.getResources().getString(R.string.none);
//        }
//    }

    public boolean checkGenderValue(String gender) {
        return !checkStringEmpty(gender) && !gender.equals("N") && !gender.equals("None");
    }

    public boolean getJoiningOutMailListFromValue(String mailListVal) {
        return !mailListVal.equalsIgnoreCase("No");
    }

    public static boolean isValidString(String str) {
        boolean isValid = false;
        isValid = str != null && str.length() > 0;
        return isValid;

    }

    public String substringBetween(String main, String a, String b1) {
        StringBuilder b = new StringBuilder(main);
        int i = main.indexOf(a);
        int z = main.lastIndexOf(b1);
        return b.substring(i, z);
    }

//    public static String[] convertListToArray(Fragment mainfragment, ArrayList productlists) {
//
//        String[] array = FetchListFromModel(productlists, mainfragment).toArray(new String[productlists.size()]);
//        return array;
//    }

//    private static ArrayList<String> FetchListFromModel(ArrayList productlists, Fragment mainfragment) {
//        ArrayList<String> mStrings = new ArrayList<>();
//        if (mainfragment instanceof Mainfragment) {
//            ArrayList<ProductListModel.Productlist> list = productlists;
//            for (ProductListModel.Productlist name : list) {
//                mStrings.add(name.getmStringProductName());
//            }
//        }
//        return mStrings;
//    }

//    public static String[] getFilterListToArray(Fragment mainfragment, ArrayList productlists, int TAg, String str) {
//
//        String[] array = filterList(productlists, mainfragment, TAg,str).toArray(new String[productlists.size()]);
//        return array;
//    }

//    private static ArrayList<String> filterList(ArrayList productlists, Fragment mainfragment,
//                                                int Tag, String str) {
//        ArrayList<String> mStrings = new ArrayList<>();
//        if (mainfragment instanceof Mainfragment) {
//            switch (str) {
//                case Constants.Productlist:
//                ArrayList<ProductListModel.Productlist> list = productlists;
//                for (ProductListModel.Productlist name : list) {
//                    if (Tag == 1) {
//                        mStrings.add(name.getmStringProductName());
//                    } else {
//                        if (Integer.parseInt(name.getmStringServiceId()) == Tag) {
//                            mStrings.add(name.getmStringProductName());
//                        }
//                    }
//                }
//                break;
//                case Constants.Citylist:
//                    ArrayList<CityListModel.CityModel.Result> mResults = productlists;
//                    for (CityListModel.CityModel.Result mResult : mResults) {
//                        mStrings.add(mResult.getmStringCityName());
//                    }
//                    break;
//                case Constants.CurrencyList:
//                    ArrayList<CityListModel.CurrencyModel.Result> mCurrency = productlists;
//                    for (CityListModel.CurrencyModel.Result mResult : mCurrency) {
//                        mStrings.add(mResult.getmStringCurrencyName());
//                    }
//                    break;
//            }
//
//        }
//        else  if (mainfragment instanceof SetRateAlert) {
//            switch (str) {
//                case Constants.Productlist:
//                    ArrayList<ProductListModel.Productlist> list = productlists;
//                    for (ProductListModel.Productlist name : list) {
//                        if (Tag == 1) {
//                            mStrings.add(name.getmStringProductName());
//                        } else {
//                            if (Integer.parseInt(name.getmStringServiceId()) == Tag) {
//                                mStrings.add(name.getmStringProductName());}}}
//                    break;
//                case Constants.Citylist:
//                    ArrayList<CityListModel.CityModel.Result> mResults = productlists;
//                    for (CityListModel.CityModel.Result mResult : mResults) {
//                        mStrings.add(mResult.getmStringCityName());}
//                    break;
//                case Constants.CurrencyList:
//                    ArrayList<CityListModel.CurrencyModel.Result> mCurrency = productlists;
//                    for (CityListModel.CurrencyModel.Result mResult : mCurrency) {
//                        mStrings.add(mResult.getmStringCurrencyName());
//                    }
//                    break;}}
//        return mStrings;
//    }


    public static String[] convListToArray(ArrayList<String> productlists) {
        return productlists.toArray(new String[productlists.size()]);
    }

    public static Uri convertImagesIntoUri(Context context, Bitmap image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), image, "Title", null);
        return Uri.parse(path);
    }

//    /*public static boolean validatePhoneNumber(Context context, EditText phNumber) *//*{
//        boolean isValid =false;
//        if (phNumber.getText().toString().trim().length()!=11) {
//            return !isValid;
//        }
//        com.google.i18n.phonenumbers.PhoneNumberUtil phoneNumberUtil = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance();
//        //  String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
//        com.google.i18n.phonenumbers.Phonenumber.PhoneNumber phoneNumber = null;
//        try {
//            phoneNumber = phoneNumberUtil.parse(phNumber.getText().toString().trim(), "IN"); //if you want to pass region code
//            // phoneNumber = phoneNumberUtil.parse(phNumber, isoCode);
//        } catch (com.google.i18n.phonenumbers.NumberParseException e) {
//            e.printStackTrace();
//        }
//        isValid=phoneNumberUtil.isValidNumber(phoneNumber);
//        return !isValid;
//    }*/

    /*public static void dialogMultiplePhotoWithoutView(final Activity context, final Bitmap profileBitmap, final int value) {
        hideSoftKeyboard(context);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!isPermission(context, "CAMERA", REQUEST_CAM)) {
                return;
            }
            if (!isPermission(context, "STORAGE", SELECT_FILE)) {
                return;
            }
        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        if (profileBitmap == null) {
            items = new CharSequence[]{"Take Photo", "Choose from Library"};
        } else {
            items = new CharSequence[]{"Take Photo", "Choose from Library", "View Photo"};
        }
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    dispatchTakePictureIntent(context, value);
                } else if (items[item].equals("Choose from Library")) {
                    Matisse.from(context)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(5)
                            .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .forResult(SELECT_FILE);
                }
            }
        });
        builder.show();
    }*/

    public static void hideSoftKeyboard(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isPermission(Context context, String permission, int reqcode) {
        switch (permission) {
            case "CALENDER":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.CALENDAR)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.CALENDAR},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "CAMERA":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CAMERA},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "CONTACTS":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.CONTACTS},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "LOCATION":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.LOCATION},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "MICROPHONE":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.MICROPHONE)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.MICROPHONE},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "PHONE":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.PHONE},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "SENSORS":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.SENSORS)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.SENSORS},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "SMS":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission_group.SMS)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission_group.SMS},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "STORAGE":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            reqcode);
                } else {
                    return true;
                }
                break;
            case "STOREIMG":
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
// Check Permissions Now
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            reqcode);
                } else {
                    return true;
                }
                break;
        }

        return false;
    }

   /* public static void dispatchTakePictureIntent(Activity context, final int value) {
        File file = getPhotoFile(context, "IMG_" + System.currentTimeMillis() + ".png");
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivityForResult(intent, REQUEST_CAM);
        }
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        requestedFileUri = getOutputMediaFileUri(Commonparams.IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, requestedFileUri);
        context.startActivityForResult(intent, value);
    }*/

    public static Uri getOutputMediaFileUri(String type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public static File getOutputMediaFile(String type) {

        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Commonparams.APP_NAME);


        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(Commonparams.APP_NAME, "Oops! Failed create "
                        + Commonparams.APP_NAME + " directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type.equals(Commonparams.IMAGE))
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        else
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");

        return mediaFile;
    }

    public static boolean onRequestPermissions(Context context, String permission, int requestCode, int[] grantResults, int reqcode) {
        if (requestCode == reqcode) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                switch (permission) {
                    case "CALENDER":
                        showShortToast(context, "CALENDER Permission Cancelled");
                        break;
                    case "CAMERA":
                        showShortToast(context, "CAMERA Permission Cancelled");
                        break;
                    case "CONTACTS":
                        showShortToast(context, "CONTACTS Permission Cancelled");
                        break;
                    case "LOCATION":
                        showShortToast(context, "LOCATION Permission Cancelled");
                        break;
                    case "MICROPHONE":
                        showShortToast(context, "MICROPHONE Permission Cancelled");
                        break;
                    case "PHONE":
                        showShortToast(context, "PHONE Permission Cancelled");
                        break;
                    case "SENSORS":
                        showShortToast(context, "SENSORS Permission Cancelled");
                        break;
                    case "SMS":
                        showShortToast(context, "SMS Permission Cancelled");
                        break;
                    case "STORAGE":
                        showShortToast(context, "STORAGE Permission Cancelled");
                        break;
                    case "STOREIMG":
                        showShortToast(context, "STORAGE Permission Cancelled");
                        break;
                }
            }
        }
        return false;
    }

    public static void showShortToast(Context c, String text) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }

    /*public static void dialogSinglePictureView(final Activity context, final Bitmap profileBitmap, final int value, final int value1) {
        hideSoftKeyboard(context);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!isPermission(context, "CAMERA", REQUEST_CAM)) {
                return;
            }
            if (!isPermission(context, "STORAGE", SELECT_FILE)) {
                return;
            }

        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        if (profileBitmap == null) {
            items = new CharSequence[]{"Take Photo", "Choose from Library"};
        } else {
            items = new CharSequence[]{"Take Photo", "Choose from Library",*//* "View Photo"*//*};
        }
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    dispatchTakePictureIntent(context, value);
                } else if (items[item].equals("Choose from Library")) {
                    Matisse.from(context)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(1)
                            .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .forResult(value1);
                } else if (items[item].equals("View Photo")) {
                    if (profileBitmap == null) {
                        return;
                    }
                }
            }
        });
        builder.show();
    }*/

    public static String getUriImage(Context context, final Uri uri) {
        String img = "";
        String filePath = uri.getPath();

        String name = getFileName(context, uri);
        File file = new File(getCacheDir(context), name);

        int maxBufferSize = 1 * 1024 * 1024;

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            Log.e("InputStream Size", "Size " + inputStream);
            int bytesAvailable = inputStream.available();
//                    int bufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            final byte[] buffers = new byte[bufferSize];

            FileOutputStream outputStream = new FileOutputStream(file);
            int read = 0;
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            Log.e("File Size", "Size " + file.length());
            inputStream.close();
            outputStream.close();

            img = file.getPath();
            Log.e("File Path", "Path " + file.getPath());
            file.length();
            Log.e("File Size", "Size " + file.length());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;

    }

    public static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public static File getCacheDir(Context context) {
        File cache;
        cache = context.getCacheDir();
        if (!cache.exists())
            cache.mkdirs();
        return cache;
    }

    public static String getStringFile(File f) {
        InputStream inputStream;
        String encodedFile = "", lastVal;
        try {
            inputStream = new FileInputStream(f.getPath());
            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile = output.toString();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }


    private static void dispatchTakePicIntent(Activity context) {
        Intent intent;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        requestedFileUri = getOutputMediaFileUri(Commonparams.IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, requestedFileUri);
        context.startActivityForResult(intent, REQ_CAM);
    }

    public static String formatDate(String selectedDate) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
            Date date = dt.parse(selectedDate);
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
            return dt1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return selectedDate;
    }

}
