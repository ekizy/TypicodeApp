package sample.java.com.typicodeapp.scenes.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import sample.java.com.typicodeapp.R;
import sample.java.com.typicodeapp.constants.MessageConstants;
import sample.java.com.typicodeapp.scenes.itemlist.ItemListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openItemListFragment();
            setInitialTitle();
        }
    }

    private void openItemListFragment() {
        ItemListFragment itemListFragment = ItemListFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main_container, itemListFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setInitialTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(MessageConstants.CURRENT_USER);
        }
    }

}
