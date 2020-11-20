package com.stambulo.jpgtopng;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.stambulo.jpgtopng.presenter.Presenter;
import com.stambulo.jpgtopng.view.View;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements View {
    @InjectPresenter
    Presenter presenter;

    private ImageView jpgImage;
    private ImageView pngImage;
    private static final int PERMISSION_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jpgImage = findViewById(R.id.jpgImageView);
        pngImage = findViewById(R.id.pngImageView);
        requestPermissions();
    }

    private void convertFile(){
        presenter.getSourceFileAndConvert();
    }

    @Override
    public void showOriginalImage(Bitmap sourceFile) {
        jpgImage.setImageBitmap(sourceFile);
    }

    @Override
    public void showConvertedImage(Bitmap convertedFile) {
        pngImage.setImageBitmap(convertedFile);
    }

    private void requestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
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
}
