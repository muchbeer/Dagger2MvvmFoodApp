package raum.muchbeer.dagger2mvvmfoodapp.view;

import android.app.Application;

import raum.muchbeer.dagger2mvvmfoodapp.dagger.ApplicationModule;
import raum.muchbeer.dagger2mvvmfoodapp.dagger.DaggerFoodComponent;
import raum.muchbeer.dagger2mvvmfoodapp.dagger.FoodComponent;

public class App extends Application {

    private  static App app;
    public FoodComponent foodComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        foodComponent = DaggerFoodComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static App getApp() {
        return app;
    }
}
