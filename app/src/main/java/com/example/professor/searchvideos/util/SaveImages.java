/*
package com.example.professor.searchvideos.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.example.professor.searchvideos.R;

import java.io.File;
import java.io.FileOutputStream;

public class SaveImages {
    private static final String TAG = SaveImages.class.getSimpleName();
    public static void saveImage(ImageView image, String fileName, Context context){
String directory = Environment.getExternalStorageDirectory().toString();
        try{
            File file = new File(directory,fileName+".jpg");
            Log.d(TAG, "saveImage: "+file.exists());
            if(file.exists())return;
            Log.d(TAG, "saveImage: "+file.exists()+"  "+file.getAbsolutePath());
            FileOutputStream stream = new FileOutputStream(file);
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream);
            stream.flush();
            stream.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(),  file.getName());

        }catch (Exception e){
            Log.d(TAG, "saveImage: "+e.getMessage());
        }
    }
    public static Bitmap loadImage(String fileName){
        String directory = Environment.getExternalStorageDirectory().toString();
        Log.d(TAG, "loadImage: "+directory);
        File imgFile = new  File(directory,fileName+".jpg");
        Bitmap bitmap = null;
        Log.d(TAG, "loadImage: "+Constants.PICTURE_SD_ADRESS+fileName+".jpg"+"  "+imgFile.canRead());
        Log.d(TAG, "loadImage: "+imgFile.getPath()+"  "+imgFile.getName()+"  "+imgFile.exists());
        if(imgFile.exists()){
             bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            Log.d(TAG, "loadImage: "+bitmap.getByteCount());
                      }
        return bitmap;
    }
}
*/
