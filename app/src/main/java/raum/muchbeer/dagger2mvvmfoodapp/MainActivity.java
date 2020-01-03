package raum.muchbeer.dagger2mvvmfoodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import raum.muchbeer.dagger2mvvmfoodapp.adapter.FoodAdapter;
import raum.muchbeer.dagger2mvvmfoodapp.databinding.ActivityMainBinding;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFood;
import raum.muchbeer.dagger2mvvmfoodapp.view.App;
import raum.muchbeer.dagger2mvvmfoodapp.view.MainActivityViewModel;
import raum.muchbeer.dagger2mvvmfoodapp.view.MainActivityViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<TzFood> foodperiodsList;
    private ArrayList<FoodType> foodsList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private TzFood selectedFoodPeriod;
    private RecyclerView foodsRecyclerView;
    private FoodAdapter foodAdapter;
    private int selectedFoodId;

    public static final int ADD_FOOD_REQUEST_CODE = 1;
    public static final int EDIT_FOOD_REQUEST_CODE = 2;

    @Inject
    public MainActivityViewModelFactory mainActivityViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

        App.getApp().foodComponent.inject(this);
        mainActivityViewModel = ViewModelProviders.of(this, mainActivityViewModelFactory).get(MainActivityViewModel.class);

        mainActivityViewModel.getAllTzFoodPeriod().observe(this, foodperiod -> {

            foodperiodsList = (ArrayList<TzFood>) foodperiod;
            for (TzFood f : foodperiod) {
                Log.d("LOG_TAG", "ratiba ya chakula: " + f.getPeriodName());
            }
            showOnSpinner();
        });

    }

    private void showOnSpinner() {
        ArrayAdapter<TzFood> foodperiodArrayAdapter = new ArrayAdapter<TzFood>(this, R.layout.spinner_item, foodperiodsList);
        foodperiodArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(foodperiodArrayAdapter);   }

    private void loadFoodList(int foodperiod) {
        mainActivityViewModel.getFoodsOfASelectedPeriod(foodperiod).observe(this, selectedFood -> {
            foodsList = (ArrayList<FoodType>) selectedFood;
            Log.d(LOG_TAG, "tHE food selected is: " + selectedFood);
            loadRecyclerView();
        });
    }

    private void loadRecyclerView() {

        foodsRecyclerView = activityMainBinding.secondaryLayout.rvFoods;
        foodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodsRecyclerView.setHasFixedSize(true);

        foodAdapter = new FoodAdapter();
        foodsRecyclerView.setAdapter(foodAdapter);

        foodAdapter.setFoods(foodsList);

        foodAdapter.setListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FoodType foodType) {
                selectedFoodId = foodType.getFoodId();

                Intent intent = new Intent(MainActivity.this, AddAndEditActivity.class);
                intent.putExtra(AddAndEditActivity.FOOD_ID, selectedFoodId);
                intent.putExtra(AddAndEditActivity.FOOD_NAME, foodType.getFoodName());
                intent.putExtra(AddAndEditActivity.UNIT_PRICE, foodType.getUnitPrice());
                startActivityForResult(intent, EDIT_FOOD_REQUEST_CODE);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                FoodType foodToDelete = foodsList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteFood(foodToDelete);
            }
        }).attachToRecyclerView(foodsRecyclerView);

    }

    public class MainActivityClickHandlers {

        public void onFABClicked(View view) {
            Intent intent = new Intent(MainActivity.this, AddAndEditActivity.class);
            startActivityForResult(intent, ADD_FOOD_REQUEST_CODE);  }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedFoodPeriod = (TzFood) parent.getItemAtPosition(pos);
            String message = " id is " + selectedFoodPeriod.getId() + "\n period is " + selectedFoodPeriod.getPeriodName() + "\n maelezo is " + selectedFoodPeriod.getPeriodDescription();
            loadFoodList(selectedFoodPeriod.getId());
            Log.d(LOG_TAG, "tHE value clicked is : "+ message);
            Log.d(LOG_TAG, "tHE position requested for the food: " + selectedFoodPeriod.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedCategoryId = selectedFoodPeriod.getId();
        if (requestCode == ADD_FOOD_REQUEST_CODE && resultCode == RESULT_OK) {

            FoodType foodType = new FoodType();
            foodType.setPeriodId(selectedCategoryId);
            foodType.setFoodName(data.getStringExtra(AddAndEditActivity.FOOD_NAME));
            foodType.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
            mainActivityViewModel.addNewFood(foodType);
        }

        else if (requestCode == EDIT_FOOD_REQUEST_CODE && resultCode == RESULT_OK) {

            FoodType foodType = new FoodType();
            foodType.setPeriodId(selectedCategoryId);
            foodType.setFoodName(data.getStringExtra(AddAndEditActivity.FOOD_NAME));
            foodType.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));

            foodType.setFoodId(selectedFoodId);
            mainActivityViewModel.updateFood(foodType);   }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityViewModel.clear();
    }
}
