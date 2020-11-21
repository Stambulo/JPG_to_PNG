package com.stambulo.jpgtopng.presenter;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.stambulo.jpgtopng.model.Model;
import com.stambulo.jpgtopng.view.MainView;

import moxy.MvpPresenter;

public class Presenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }


    public void convertFileAndSave(){
        new Consumer(new Model.Producer()).execSingle();
    }


    public class Consumer{
        Model.Producer producer;

        public Consumer(Model.Producer producer){
            this.producer = producer;
        }

        public void execSingle() {
            producer.single().subscribe((s) -> {
                getViewState().showConvertedImage((Bitmap) s);
                Log.i("--->", "onSuccess  -  " + s);
            }, (e) -> Log.i("--->", "onError"));
        }
    }
}
