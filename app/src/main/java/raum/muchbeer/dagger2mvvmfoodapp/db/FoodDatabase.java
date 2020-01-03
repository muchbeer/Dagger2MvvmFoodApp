package raum.muchbeer.dagger2mvvmfoodapp.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodTypeDao;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFood;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFoodDao;


@Database(entities= {FoodType.class, TzFood.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {

    public abstract TzFoodDao tzFoodDao();
    public abstract FoodTypeDao foodTypeDao();

    private static FoodDatabase instance;

    public static synchronized FoodDatabase getInstance(Context context) {
        if(instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FoodDatabase.class, "food_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };
}
