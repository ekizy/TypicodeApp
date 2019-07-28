package sample.java.com.typicodeapp.injection.component;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import sample.java.com.typicodeapp.application.TypicodeApp;
import sample.java.com.typicodeapp.injection.context.AppContext;
import sample.java.com.typicodeapp.injection.module.AppModule;
import sample.java.com.typicodeapp.injection.module.NetworkModule;
import sample.java.com.typicodeapp.network.ApiManager;
import sample.java.com.typicodeapp.network.NetworkManager;
import sample.java.com.typicodeapp.network.WebSocketManager;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {

    void inject(TypicodeApp app);

    @AppContext
    Context context();

    Application application();

    ApiManager apiManager();

    Gson gson();

    NetworkManager networkManager();

    WebSocketManager socketManager();

}
