package sample.java.com.typicodeapp.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sample.java.com.typicodeapp.injection.context.AppContext;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @AppContext
    Context providesApplicationContext() {
        return application;
    }

}
