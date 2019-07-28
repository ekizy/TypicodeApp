package sample.java.com.typicodeapp.scenes.itemlist;

import javax.inject.Inject;

import sample.java.com.typicodeapp.listener.ApiResponseListener;
import sample.java.com.typicodeapp.listener.MessageReceiver;
import sample.java.com.typicodeapp.network.NetworkManager;
import sample.java.com.typicodeapp.network.WebSocketManager;
import sample.java.com.typicodeapp.network.apimodels.BaseApiModel;
import sample.java.com.typicodeapp.network.apimodels.ItemListContainerApiModel;

public class ItemListInteractor implements ItemListContract.InteractorInterface, MessageReceiver {

    private ItemListContract.PresenterInteractorInterface presenter;

    @Inject
    NetworkManager networkManager;
    @Inject
    WebSocketManager webSocketManager;

    @Inject
    ItemListInteractor() {

    }

    //region Interactor interface

    @Override
    public void setPresenter(ItemListContract.PresenterInteractorInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fetchItems() {
        networkManager.fetchItems(new ApiResponseListener() {
            @Override
            public void onSuccess(BaseApiModel model) {
                if (model instanceof ItemListContainerApiModel) {
                    presenter.showItems((ItemListContainerApiModel) model);
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                presenter.showError(errorMessage);
            }
        });
    }

    @Override
    public void connectToSocket() {
        webSocketManager.connectToWebSocket();
        webSocketManager.setReceiver(this);
    }

    @Override
    public void closeSocketConnection() {
        webSocketManager.closeWebSocketConnection();
    }

    //endregion

    //region MessageReceiver interface

    @Override
    public void onMessageReceived(String message) {
        presenter.messageReceived(message);
    }

    @Override
    public void sendMessage(String message) {
        webSocketManager.sendMessage(message);
    }

    //endregion

}
