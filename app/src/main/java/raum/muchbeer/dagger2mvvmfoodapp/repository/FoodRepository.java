package raum.muchbeer.dagger2mvvmfoodapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import raum.muchbeer.dagger2mvvmfoodapp.db.FoodDatabase;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodTypeDao;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFood;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFoodDao;

public class FoodRepository {

    private TzFoodDao tzFoodDao;
    private FoodTypeDao foodTypeDao;
    private LiveData<List<TzFood>> tzFoodsPeriod;
    private LiveData<List<FoodType>> foodType;

    private static final String LOG_TAG = FoodRepository.class.getSimpleName();
    private CompositeDisposable disposable=new CompositeDisposable();

    public FoodRepository(Application application) {

        FoodDatabase foodDatabase = FoodDatabase.getInstance(application);
        tzFoodDao = foodDatabase.tzFoodDao();
        foodTypeDao = foodDatabase.foodTypeDao();
    }

    public LiveData<List<TzFood>> getFoodsPeriod() { return tzFoodDao.getAllFoodPeriod(); }

    public LiveData<List<FoodType>> getFoods(int periodId) {return foodTypeDao.getFoodsAfterSelection(periodId); }

    // public void insertTzFood(TzFood tzFood){ new InsertTzFoodAsyncTask(tzFoodDao).execute(tzFood);  }

    public void insertFoodType(final FoodType foodType){

        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                foodTypeDao.insert(foodType);
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d(LOG_TAG, "The food is added successful");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "tHE new error on create the new food is: " + e.getMessage());
                    }
                }));
    }


    public void deleteFoodType(final FoodType foodType){

        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                foodTypeDao.delete(foodType);
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d(LOG_TAG, "The food is deleted successful");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "tHE new error on delete food which is: " + e.getMessage());

                    }
                }));
    }


    public void updateFoodType(final FoodType foodType){

        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                foodTypeDao.update(foodType);
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d(LOG_TAG, "The food is updated successful");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "tHE new error on delete food which is: " + e.getMessage());
                    }
                }));

    }

    public void clear() { disposable.clear();  }

}
