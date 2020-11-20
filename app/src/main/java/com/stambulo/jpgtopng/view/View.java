package com.stambulo.jpgtopng.view;

import android.graphics.Bitmap;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface View extends MvpView {
    void showOriginalImage(Bitmap sourceFile);
    void showConvertedImage(Bitmap convertedFile);
}
