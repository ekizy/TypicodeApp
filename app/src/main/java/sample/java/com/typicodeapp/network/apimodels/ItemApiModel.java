package sample.java.com.typicodeapp.network.apimodels;

import com.google.gson.annotations.SerializedName;

import sample.java.com.typicodeapp.uimodels.ItemModel;

public class ItemApiModel extends BaseApiModel implements ItemModel {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int ID) {
        this.id = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
