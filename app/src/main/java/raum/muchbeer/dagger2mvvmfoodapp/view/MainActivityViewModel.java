package raum.muchbeer.dagger2mvvmfoodapp.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFood;
import raum.muchbeer.dagger2mvvmfoodapp.repository.FoodRepository;

public class MainActivityViewModel extends ViewModel {

    private FoodRepository foodRepository;
    private LiveData<List<TzFood>> allFoodPeriod;
    private LiveData<List<FoodType>> foodsOfASelectedPeriod;

    public MainActivityViewModel(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public LiveData<List<TzFood>> getAllTzFoodPeriod() {
        allFoodPeriod=foodRepository.getFoodsPeriod();
        return allFoodPeriod;
    }

    public LiveData<List<FoodType>> getFoodsOfASelectedPeriod(int tzfoodId) {
        foodsOfASelectedPeriod=foodRepository.getFoods(tzfoodId);
        return foodsOfASelectedPeriod;
    }

    public void addNewFood(FoodType foodType){ foodRepository.insertFoodType(foodType); }

    public void updateFood(FoodType foodType){ foodRepository.updateFoodType(foodType); }

    public void deleteFood(FoodType foodType){ foodRepository.deleteFoodType(foodType); }

    public void clear() {  foodRepository.clear();  }
}
