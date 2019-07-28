package sample.java.com.typicodeapp.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.java.com.typicodeapp.constants.ErrorConstants;
import sample.java.com.typicodeapp.listener.ApiResponseListener;
import sample.java.com.typicodeapp.network.apimodels.BaseApiModel;

@Singleton
public class ApiManager {

    private TypiApiInterface apiInterface;
    private Gson gson;

    public ApiManager(TypiApiInterface apiInterface, Gson gson) {
        this.apiInterface = apiInterface;
        this.gson = gson;
    }

    public <RM extends BaseApiModel> void get(String url, @NonNull final ApiResponseListener listener, Class<RM> responseType) {
        apiInterface.getJSONData(url).enqueue(produceCallback(listener, responseType));
    }

    private <RM extends BaseApiModel> Callback<ResponseBody> produceCallback(final ApiResponseListener listener, final Class<RM> responseType) {
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) try {
                    if (response.body() != null) {
                        String responseBody = response.body().string();
                        RM responseModel = gson.fromJson(responseBody, responseType);
                        listener.onSuccess(responseModel);
                    } else {
                        listener.onFailure(ErrorConstants.PARSING_ERROR);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                else {
                    listener.onFailure(ErrorConstants.SERVICE_FAILURE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        };
    }

}
