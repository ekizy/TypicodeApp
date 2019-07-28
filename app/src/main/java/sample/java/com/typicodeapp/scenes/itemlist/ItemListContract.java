package sample.java.com.typicodeapp.scenes.itemlist;

import java.util.List;

import sample.java.com.typicodeapp.uimodels.ItemListContainerModel;
import sample.java.com.typicodeapp.uimodels.ItemModel;

public interface ItemListContract {

    interface ViewInterface {

        void updateItemTable(List<ItemModel> items);

        void showError(String errorMessage);

        void changeToolbarTitle(String message);

    }

    interface PresenterInteractorInterface {

        void showItems(ItemListContainerModel model);

        void showError(String errorMessage);

        void messageReceived(String message);

    }

    interface PresenterViewInterface {

        void attachView(ItemListContract.ViewInterface view);

        void detachComponents();

        void getItems();

        void connectToSocket();

        void closeSocketConnection();

        void sendMessage(String message);
    }

    interface InteractorInterface {

        void setPresenter(ItemListContract.PresenterInteractorInterface presenter);

        void fetchItems();

        void connectToSocket();

        void closeSocketConnection();

        void sendMessage(String message);

    }

}
