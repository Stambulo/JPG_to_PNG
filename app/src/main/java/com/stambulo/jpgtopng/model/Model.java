package com.stambulo.jpgtopng.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Model {
    private static final File sourceFile = new File(String.valueOf(Environment.
            getExternalStoragePublicDirectory("/Download/jpg-to-png.jpg/")));

    private static final File convertedFile = new File(String.valueOf(Environment.
            getExternalStoragePublicDirectory("/Download/jpg-to-png.png/")));


    public File convertAndSave() {
        Bitmap bmp = BitmapFactory.decodeFile(String.valueOf(sourceFile));
        try {
            Thread.sleep(3000);
            FileOutputStream out = new FileOutputStream(convertedFile);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }


    public static class Producer{

        public Single single(){
            return Single.fromCallable(() -> new Model().convertAndSave()).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread());
        }
    }
}
