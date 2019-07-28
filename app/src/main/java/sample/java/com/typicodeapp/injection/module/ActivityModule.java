package sample.java.com.typicodeapp.injection.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import sample.java.com.typicodeapp.injection.context.ActivityContext;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context providesActivityContext() {
        return activity;
    }

}
