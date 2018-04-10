package com.lak.prj.mvp.presenter;

import android.content.Context;

import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.prj.mvp.model.MainModel;
import com.lak.prj.mvp.view.MainView;

/**
 * Created by lawrence on 2018/4/10.
 */

public class MainPresenter extends MvpPresenterImpl<MainView> {

    private MainModel data = new MainModel();

    public MainPresenter(Context context) {
        super(context);
    }

    public void doAdd() {
        data.test++;
        mvpView().add(data);
    }

}
