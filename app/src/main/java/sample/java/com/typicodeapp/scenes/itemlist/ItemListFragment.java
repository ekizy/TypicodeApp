package sample.java.com.typicodeapp.scenes.itemlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import sample.java.com.typicodeapp.R;
import sample.java.com.typicodeapp.adapter.ItemListAdapter;
import sample.java.com.typicodeapp.application.TypicodeApp;
import sample.java.com.typicodeapp.constants.MessageConstants;
import sample.java.com.typicodeapp.itemdecoration.ItemListItemDecoration;
import sample.java.com.typicodeapp.scenes.main.MainActivity;
import sample.java.com.typicodeapp.uimodels.ItemModel;

public class ItemListFragment extends Fragment implements ItemListContract.ViewInterface {

    public final static String TAG = "ItemListFragment";
    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Inject
    ItemListContract.PresenterViewInterface presenter;

    @BindView(R.id.recycler_view_item_list_items)
    RecyclerView itemsRecyclerView;
    @BindView(R.id.edit_text_item_list_message)
    AppCompatEditText messageEditText;
    @BindView(R.id.progress_bar_item_list_loading)
    ProgressBar loadingProgressBar;
    @BindDimen(R.dimen.margin_8)
    int listTopBottomPadding;
    @BindDimen(R.dimen.margin_16)
    int listItemLeftRightMargin;

    private ItemListAdapter listAdapter;
    private ItemListItemDecoration itemDecoration;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        setComponents();
        presenter.getItems();
        presenter.connectToSocket();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        if (getActivity() != null && getActivity().getApplication() instanceof TypicodeApp) {
            DaggerItemListComponent.builder()
                    .appComponent(((TypicodeApp) getActivity()
                            .getApplication()).getApplicationComponent())
                    .itemListModule(new ItemListModule())
                    .build()
                    .inject(this);
            presenter.attachView(this);
        }

        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        presenter.closeSocketConnection();
        presenter.detachComponents();

        super.onDestroyView();
    }

    //region View interface

    @Override
    public void updateItemTable(final List<ItemModel> items) {
        itemsRecyclerView.setVisibility(View.VISIBLE);
        messageEditText.setVisibility(View.VISIBLE);
        loadingProgressBar.setVisibility(View.INVISIBLE);

        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listAdapter.setItemModels(items);
                    listAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeToolbarTitle(String message) {
        final String title = message.equals(MessageConstants.LOGOUT_MESSAGE) ?
                MessageConstants.LOGOUT_MESSAGE : MessageConstants.CURRENT_USER;

        if (getActivity() instanceof MainActivity) {
            final MainActivity activity = (MainActivity) getActivity();
            activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.getSupportActionBar().setTitle(title);
                    }
                });
        }
    }

    //endregion

    //region Actions

    @OnEditorAction(R.id.edit_text_item_list_message)
    public boolean sendMessageAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (messageEditText.getText() != null) {
                presenter.sendMessage(messageEditText.getText().toString());
            }
        }

        return false;
    }

    //endregion

    //region Private methods

    private void setComponents() {
        listAdapter = new ItemListAdapter();
        itemDecoration = new ItemListItemDecoration(listTopBottomPadding, listTopBottomPadding,
                listItemLeftRightMargin, listItemLeftRightMargin);
        itemsRecyclerView.addItemDecoration(itemDecoration);
        itemsRecyclerView.setAdapter(listAdapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //endregion

}
