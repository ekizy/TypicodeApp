package sample.java.com.typicodeapp.network.apimodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sample.java.com.typicodeapp.uimodels.ItemListContainerModel;
import sample.java.com.typicodeapp.uimodels.ItemModel;

public class ItemListContainerApiModel extends BaseApiModel implements ItemListContainerModel {

    @SerializedName("data")
    private List<ItemApiModel> itemList;

    @Override
    public List<ItemModel> getItemList() {
        return new ArrayList<ItemModel>(itemList);
    }

}
