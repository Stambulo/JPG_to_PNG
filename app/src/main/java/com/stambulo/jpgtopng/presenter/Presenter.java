package com.stambulo.jpgtopng.presenter;

import com.stambulo.jpgtopng.model.Model;
import com.stambulo.jpgtopng.view.View;

import moxy.MvpPresenter;

public class Presenter extends MvpPresenter<View> {
    private final Model model = new Model();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void getSourceFileAndConvert(){
        getViewState().showOriginalImage(model.getSourceImage());
        convertFileAndSave();
    }

    private void convertFileAndSave(){
        model.convertAndSave();
        showConvertedFile();
    }

    private void showConvertedFile(){
        getViewState().showConvertedImage(model.showConvertedFile());
    }
}
