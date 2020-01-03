package raum.muchbeer.dagger2mvvmfoodapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodTypeDao {


    @Insert
    void insert(FoodType foodType);

    @Update
    void update(FoodType foodType);

    @Delete
    void delete(FoodType foodType);

    @Query("SELECT * FROM food_type")
    LiveData<List<FoodType>> getAllFoods();

    @Query("SELECT * FROM food_type WHERE food_period_id==:periodId")
    LiveData<List<FoodType>> getFoodsAfterSelection(int periodId);
}
