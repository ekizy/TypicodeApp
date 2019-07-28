package sample.java.com.typicodeapp.injection.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import sample.java.com.typicodeapp.network.ApiManager;
import sample.java.com.typicodeapp.network.TypiApiInterface;
import sample.java.com.typicodeapp.network.NetworkManager;
import sample.java.com.typicodeapp.constants.URLConstants;
import sample.java.com.typicodeapp.network.WebSocketManager;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URLConstants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    TypiApiInterface providesApiInterface(Retrofit retrofit) {
        return retrofit.create(TypiApiInterface.class);
    }

    @Provides
    @Singleton
    ApiManager providesApiManager(TypiApiInterface apiInterface, Gson gson) {
        return new ApiManager(apiInterface, gson);
    }

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(ApiManager apiManager) {
        return new NetworkManager(apiManager);
    }

    @Provides
    @Singleton
    WebSocketManager providesWebSocketManager() {
        return new WebSocketManager();
    }

}
