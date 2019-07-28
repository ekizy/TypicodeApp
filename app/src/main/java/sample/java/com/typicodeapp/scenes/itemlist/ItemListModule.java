package sample.java.com.typicodeapp.scenes.itemlist;

import dagger.Module;
import dagger.Provides;
import sample.java.com.typicodeapp.injection.context.ActivityScope;

@Module
public class ItemListModule {

    @Provides
    @ActivityScope
    ItemListContract.PresenterInteractorInterface providesItemListPresenterInteractorInterface (
            ItemListPresenter itemListPresenter) {
        return itemListPresenter;
    }

    @Provides
    @ActivityScope
    ItemListContract.PresenterViewInterface providesItemListPresenterViewInterface (
            ItemListPresenter itemListPresenter) {
        return itemListPresenter;
    }

    @Provides
    @ActivityScope
    ItemListContract.InteractorInterface providesItemListInteractor(
            ItemListInteractor itemListInteractor) {
        return itemListInteractor;
    }

}
