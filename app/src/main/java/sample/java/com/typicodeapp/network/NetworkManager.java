package sample.java.com.typicodeapp.network;

import javax.inject.Singleton;

import sample.java.com.typicodeapp.constants.URLConstants;
import sample.java.com.typicodeapp.listener.ApiResponseListener;
import sample.java.com.typicodeapp.network.apimodels.ItemListContainerApiModel;

@Singleton
public class NetworkManager {

    ApiManager apiManager;

    public NetworkManager(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public void fetchItems(ApiResponseListener apiResponseListener) {
        apiManager.get(URLConstants.GET_ITEMS, apiResponseListener, ItemListContainerApiModel.class);
    }

}
