package com.stambulo.jpgtopng.view;

import android.graphics.Bitmap;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface MainView extends MvpView {
    void showConvertedImage(Bitmap convertedFile);
}
