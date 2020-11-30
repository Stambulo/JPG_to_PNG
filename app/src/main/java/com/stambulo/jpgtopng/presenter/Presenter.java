package com.stambulo.jpgtopng.presenter;

import com.stambulo.jpgtopng.model.Model;
import com.stambulo.jpgtopng.view.MainView;

import java.io.File;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.MvpPresenter;

public class Presenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }


    public void convertFileAndSave(){
        new Consumer(new Model()).execSingle();
    }


    public class Consumer{
        Model producer;

        public Consumer(Model producer){
            this.producer = producer;
        }

        public void execSingle() {
            producer.getConvertedFile()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((s) -> {
                getViewState().showConvertedImage((File) s);
                //Log.i("--->", "onSuccess  -  " + s);
            }, (e) -> {}); //Log.i("--->", "onError"));
        }
    }
}
