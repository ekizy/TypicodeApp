package sample.java.com.typicodeapp.injection.component;

import dagger.Component;
import sample.java.com.typicodeapp.injection.context.ActivityScope;
import sample.java.com.typicodeapp.injection.module.ActivityModule;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
interface ActivityComponent {

}
