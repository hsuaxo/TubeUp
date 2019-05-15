package com.hsuaxo.tubeup.di.components;

import android.app.Application;

import com.hsuaxo.tubeup.TubeUpApp;
import com.hsuaxo.tubeup.di.modules.ActivityBindingModule;
import com.hsuaxo.tubeup.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<TubeUpApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder create(Application app);

        AppComponent build();
    }
}