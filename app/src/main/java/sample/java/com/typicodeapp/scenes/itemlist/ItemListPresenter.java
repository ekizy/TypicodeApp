package sample.java.com.typicodeapp.scenes.itemlist;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import sample.java.com.typicodeapp.constants.ErrorConstants;
import sample.java.com.typicodeapp.constants.MessageConstants;
import sample.java.com.typicodeapp.uimodels.ItemListContainerModel;
import sample.java.com.typicodeapp.uimodels.ItemModel;

public class ItemListPresenter implements ItemListContract.PresenterInteractorInterface,
        ItemListContract.PresenterViewInterface {

    private ItemListContract.ViewInterface view;
    private ItemListContract.InteractorInterface interactor;
    private List<ItemModel> itemModels;

    @Inject
    ItemListPresenter(ItemListContract.InteractorInterface interactor) {
        this.interactor = interactor;
        this.interactor.setPresenter(this);
    }

    //region PresenterView interface

    @Override
    public void attachView(ItemListContract.ViewInterface view) {
        this.view = view;
    }

    @Override
    public void detachComponents() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void getItems() {
        interactor.fetchItems();
    }

    @Override
    public void connectToSocket() {
        interactor.connectToSocket();
    }

    @Override
    public void closeSocketConnection() {
        interactor.closeSocketConnection();
    }

    @Override
    public void sendMessage(String message) {
        interactor.sendMessage(message);
    }

    //endregion

    //region PresentorInteractor interface

    @Override
    public void messageReceived(String message) {
        Matcher matcher = validatePattern(message);

        if (message.equals(MessageConstants.LOGIN_MESSAGE) || message.equals(MessageConstants.LOGOUT_MESSAGE)) {
            view.changeToolbarTitle(message);
        } else if(matcher != null) {
            int id = Integer.parseInt(matcher.group(1));
            String newName = matcher.group(2);
            boolean isFound = changeItemData(id, newName);
            sortItems();

            if (isFound) {
                view.updateItemTable(itemModels);            }
            else {
                view.showError(ErrorConstants.NO_ITEM_FOUND);
            }
        } else {
            view.showError(ErrorConstants.INVALID_RECEIVING_MESSAGE_FORMAT);
        }
    }

    @Override
    public void showError(String errorMessage) {
        view.showError(errorMessage);
    }

    @Override
    public void showItems(ItemListContainerModel model) {
        itemModels = model.getItemList();
        sortItems();
        view.updateItemTable(itemModels);
    }

    //endregion

    //region Private methods

    private void sortItems() {
        Collections.sort(itemModels, new Comparator<ItemModel>() {
            @Override
            public int compare(ItemModel itemModel1, ItemModel itemModel2) {
                return itemModel1.getName().compareToIgnoreCase(itemModel2.getName());
            }
        });
    }

    private Matcher validatePattern(String input) {
        Pattern p = Pattern.compile(MessageConstants.MESSAGE_FORMAT);
        Matcher matcher = p.matcher(input);

        if(matcher.matches()) {
            return matcher;
        } else {
            return null;
        }
    }

    private boolean changeItemData(int id, String newName) {
        boolean isFound = false;
        int listSize = itemModels.size();

        for (int index = 0; index < listSize ; index++) {
            ItemModel itemModel = itemModels.get(index);

            if (itemModel.getID() == id) {
                isFound = true;
                itemModel.setName(newName);
            }
        }

        return isFound;
    }

    //endregion

}
