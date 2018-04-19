package com.lak.core.dagger2.components;

import com.lak.core.dagger2.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lawrence on 2018/4/17.
 * <p>
 * 全局 component
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

}
