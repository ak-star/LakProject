package com.lak.prj.mvp.view;

import com.lak.core.mvp.base.MvpView;
import com.lak.prj.mvp.model.MainModel;

/**
 * Created by lawrence on 2018/4/10.
 */

public interface MainView extends MvpView {

    void add(MainModel data);

}
