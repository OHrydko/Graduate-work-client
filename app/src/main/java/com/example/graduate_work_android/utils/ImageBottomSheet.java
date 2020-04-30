package com.example.graduate_work_android.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ImageBottomSheet implements BottomFragment.ItemClickListener {

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;
    private static final int IMAGE_MAX_SIZE_WIDTH = 1980;
    private FragmentActivity activity;
    private Uri imageUri;
    private StartActivityForResultCallback startActivity;
    private static final int STORAGE_PERMISSION_CODE = 100;
    private boolean isCamera = false;

    public ImageBottomSheet(FragmentActivity activity,
                            StartActivityForResultCallback startActivity) {
        this.activity = activity;
        this.startActivity = startActivity;
    }


    @SuppressLint("CheckResult")
    public File getParam(int requestCode, int resultCode, @Nullable Intent data,
                         FragmentActivity activity) {
        File file = null;
        if (resultCode == RESULT_OK) {


            if (requestCode == REQUEST_CODE_GALLERY) {
                Uri selectedImage = null;
                isCamera = false;
                if (data != null) {
                    selectedImage = CapturePhotoUtils.insertImage(activity.getContentResolver(),
                            decodeFile(data.getData()));
                }
                if (selectedImage != null) {
                    file = new File(Objects.requireNonNull(FileUtil.getPath(selectedImage, activity)));

                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                isCamera = true;
                if (imageUri != null) {
                    imageUri = CapturePhotoUtils.insertImage(activity.getContentResolver(),
                            decodeFile(imageUri));
                    file = new File(Objects.requireNonNull(FileUtil.getPath(imageUri, activity)));
                }
            }


        }
        return file;
    }


    public void startBottomSheet() {
        BottomFragment bottomFragment = new BottomFragment(this);
        bottomFragment.show(activity.getSupportFragmentManager(), bottomFragment.getTag());
    }

    @Override
    public void onItemClick(String item) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {

            switch (item) {
                case "camera":
                    sendCamera();
                    break;
                case "picture":
                    sendIntent();
                    break;

            }
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            }

        }

    }

    private void sendIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivity.start(intent, REQUEST_CODE_GALLERY);
    }

    private void sendCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = activity.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivity.start(intent, REQUEST_CODE_CAMERA);
    }


    private Bitmap decodeFile(Uri selectedImages) {
        Bitmap b = null;
        //Decode image size
        //get width and height without creating bitmap
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        InputStream fis;
        try {
            fis = activity.getContentResolver().openInputStream(selectedImages);
            BitmapFactory.decodeStream(fis, null, o);
            if (fis != null) {
                fis.close();
            }

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        //calculate scale
        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE_WIDTH || o.outWidth > IMAGE_MAX_SIZE_WIDTH) {
            scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE_WIDTH /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        //inSampleSize - scale coefficient
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        try {
            fis = activity.getContentResolver().openInputStream(selectedImages);
            b = BitmapFactory.decodeStream(fis, null, o2);
            if (fis != null) {
                fis.close();
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        Matrix matrix = new Matrix();
        if (isCamera)
            matrix.postRotate(90);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, b.getWidth(), b.getHeight(), true);

        return Bitmap.createBitmap(scaledBitmap, 0, 0,
                scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
    }


//    private boolean checkSize(List<String> path) {
//        long size = 0;
//        for (String row : path) {
//            size += new File(row).length();
//        }
//        return size < 5242880;
//    }

}


