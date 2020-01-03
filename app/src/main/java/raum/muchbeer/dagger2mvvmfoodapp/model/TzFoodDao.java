package raum.muchbeer.dagger2mvvmfoodapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TzFoodDao {

    @Insert
    void insert(TzFood tzFood);

    @Update
    void update(TzFood tzFood);

    @Delete
    void delete(TzFood tzFood);

    @Query("SELECT * FROM tz_food_period")
    LiveData<List<TzFood>> getAllFoodPeriod();
}
