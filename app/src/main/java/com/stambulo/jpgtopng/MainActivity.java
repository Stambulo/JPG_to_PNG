package com.stambulo.jpgtopng;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.stambulo.jpgtopng.presenter.Presenter;
import com.stambulo.jpgtopng.view.MainView;

import java.io.File;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView, View.OnClickListener {
    @InjectPresenter
    Presenter presenter;

    private ImageView pngImage;
    private static final int PERMISSION_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        pngImage = findViewById(R.id.pngImageView);
    }

    private void convertFile(){
        presenter.convertFileAndSave();
    }

    @Override
    public void showConvertedImage(File convertedFile) {
        BackgroundConverter bgConverter = new BackgroundConverter();
        bgConverter.execute(convertedFile);
    }

    @Override
    public void onClick(View view) {
        requestPermissions();
    }

    private void requestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            convertFile();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 2 && (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                convertFile();
            }
        }
    }

    class BackgroundConverter extends AsyncTask<File, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(File... files) {
            Bitmap bmp = BitmapFactory.decodeFile(String.valueOf(files[0]));
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            super.onPostExecute(bmp);
            pngImage.setImageBitmap(bmp);
        }
    }
}
