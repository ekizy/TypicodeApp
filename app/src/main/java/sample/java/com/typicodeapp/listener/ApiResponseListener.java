package sample.java.com.typicodeapp.listener;

import sample.java.com.typicodeapp.network.apimodels.BaseApiModel;

public interface ApiResponseListener <RM extends BaseApiModel> {

    void onSuccess(RM model);

    void onFailure(String errorMessage);

}
