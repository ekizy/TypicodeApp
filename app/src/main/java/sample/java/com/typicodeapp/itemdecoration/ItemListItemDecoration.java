package sample.java.com.typicodeapp.itemdecoration;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemListItemDecoration extends RecyclerView.ItemDecoration {

    private int listTopPadding;
    private int listBottomPadding;
    private int itemVerticalOffset;
    private int leftrightMargin;

    public ItemListItemDecoration(int listTopPadding, int listBottomPadding, int itemVerticalOffset,
                                  int leftrightMargin) {
        this.listTopPadding = listTopPadding;
        this.listBottomPadding = listBottomPadding;
        this.itemVerticalOffset = itemVerticalOffset;
        this.leftrightMargin = leftrightMargin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(leftrightMargin, listTopPadding, leftrightMargin, 0);
        } else if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
            outRect.set(leftrightMargin, itemVerticalOffset, leftrightMargin, listBottomPadding);
        } else {
            outRect.set(leftrightMargin, itemVerticalOffset, leftrightMargin, 0);
        }
    }

}
