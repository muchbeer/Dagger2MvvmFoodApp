package raum.muchbeer.dagger2mvvmfoodapp.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import javax.inject.Inject;
import javax.inject.Singleton;

import raum.muchbeer.dagger2mvvmfoodapp.repository.FoodRepository;

@Singleton
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final FoodRepository foodRepository;

    @Inject
    public MainActivityViewModelFactory(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(foodRepository);
    }
}
