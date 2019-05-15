package com.hsuaxo.tubeup.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.hsuaxo.tubeup.AppDatabase;
import com.hsuaxo.tubeup.rxtube.RxTube;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    RxTube provideRxTube(@Named("apiKey") String key) {
        return new RxTube(key);
    }

    @Provides
    @Named("apiKey")
    String provideApiKey() {
        return "AIzaSyDQSsQW8dB0MNyzuc2ItsxG0jXdBIEXY9s";
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, "APP_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}