package sample.java.com.typicodeapp.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.java.com.typicodeapp.R;
import sample.java.com.typicodeapp.uimodels.ItemModel;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {

    private List<ItemModel> itemModels;

    public ItemListAdapter() {
        itemModels = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_list_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemModel model = itemModels.get(position);
        holder.bindViews(model);
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    //region Viewholders

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_item_list_item_id)
        AppCompatTextView itemIDTextView;
        @BindView(R.id.text_view_item_list_item_name)
        AppCompatTextView itemNameTextView;

        private ItemModel itemModel;

        private ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        private void bindViews(ItemModel model) {
            this.itemModel = model;

            if (itemModel != null) {
                String idText = Integer.toString(itemModel.getID());
                itemIDTextView.setText(idText);
                itemNameTextView.setText(itemModel.getName());
            }
        }

    }

    //endregion

}
