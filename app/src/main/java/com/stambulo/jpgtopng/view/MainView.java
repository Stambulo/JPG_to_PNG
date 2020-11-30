package com.stambulo.jpgtopng.view;

import java.io.File;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface MainView extends MvpView {
    void showConvertedImage(File convertedFile);
}
