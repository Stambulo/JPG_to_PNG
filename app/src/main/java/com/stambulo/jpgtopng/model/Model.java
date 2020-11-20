package com.stambulo.jpgtopng.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Model {
    private static final File sourceFile = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("/Download/jpg-to-png.jpg/")));
    private static final File convertedFile = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("/Download/jpg-to-png.png/")));

    public Model(){
    }

    public Bitmap getSourceImage(){
        return BitmapFactory.decodeFile(String.valueOf(sourceFile));
    }

    public void convertAndSave(){
        Bitmap bmp = BitmapFactory.decodeFile(String.valueOf(sourceFile));
        try {
            FileOutputStream out = new FileOutputStream(convertedFile);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch(IOException e){
                e.printStackTrace();
        }
    }

    public Bitmap showConvertedFile(){
        return BitmapFactory.decodeFile(String.valueOf(convertedFile));
    }
}
