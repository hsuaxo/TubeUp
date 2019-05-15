package com.hsuaxo.tubeup;

import com.hsuaxo.tubeup.di.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class TubeUpApp extends DaggerApplication {
    protected AndroidInjector<TubeUpApp> applicationInjector() {
        return DaggerAppComponent
                .builder()
                .create(this)
                .build();
    }
}