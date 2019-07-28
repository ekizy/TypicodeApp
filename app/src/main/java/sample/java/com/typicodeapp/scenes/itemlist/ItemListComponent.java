package sample.java.com.typicodeapp.scenes.itemlist;

import dagger.Component;
import sample.java.com.typicodeapp.injection.component.AppComponent;
import sample.java.com.typicodeapp.injection.context.ActivityScope;

@ActivityScope
@Component(modules = ItemListModule.class, dependencies = AppComponent.class)
public interface ItemListComponent {

    void inject(ItemListFragment itemListFragment);

}
