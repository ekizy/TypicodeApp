package sample.java.com.typicodeapp.application;

import android.app.Application;

import sample.java.com.typicodeapp.injection.component.AppComponent;
import sample.java.com.typicodeapp.injection.component.DaggerAppComponent;
import sample.java.com.typicodeapp.injection.module.AppModule;

public class TypicodeApp extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDependencyInjectionLibrary();
    }

    private void initDependencyInjectionLibrary() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }

}
