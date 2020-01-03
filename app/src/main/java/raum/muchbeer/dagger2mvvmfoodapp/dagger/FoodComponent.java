package raum.muchbeer.dagger2mvvmfoodapp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import raum.muchbeer.dagger2mvvmfoodapp.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface FoodComponent {

    void inject(MainActivity mainActivity);
}
