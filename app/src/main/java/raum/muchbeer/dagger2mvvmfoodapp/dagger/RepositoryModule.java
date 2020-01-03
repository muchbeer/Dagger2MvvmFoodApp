package raum.muchbeer.dagger2mvvmfoodapp.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import raum.muchbeer.dagger2mvvmfoodapp.repository.FoodRepository;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    FoodRepository providesFoodRepository(Application application){

        return new FoodRepository(application);
    }

}
