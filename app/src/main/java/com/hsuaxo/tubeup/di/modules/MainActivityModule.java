package com.hsuaxo.tubeup.di.modules;

import com.hsuaxo.tubeup.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract SearchFragment provideSearchFragment();
}
