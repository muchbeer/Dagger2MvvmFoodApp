package raum.muchbeer.dagger2mvvmfoodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import raum.muchbeer.dagger2mvvmfoodapp.databinding.ActivityAddEditBinding;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;

public class AddAndEditActivity extends AppCompatActivity {

    private FoodType foodType;
    public static final String FOOD_ID="foodId";
    public static final String FOOD_NAME="foodName";
    public static final String UNIT_PRICE="unitPrice";
    private ActivityAddEditBinding activityAddAndEditBinding;
    private FoodClickHandlers foodClickHandlers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        foodType=new FoodType();
        activityAddAndEditBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_edit);
        activityAddAndEditBinding.setFood(foodType);

        foodClickHandlers = new FoodClickHandlers(this);
        activityAddAndEditBinding.setClickHandler(foodClickHandlers);

        Intent intent=getIntent();
        if(intent.hasExtra(FOOD_ID)){

            setTitle("Edit Food");
            foodType.setFoodName(intent.getStringExtra(FOOD_NAME));
            foodType.setUnitPrice(intent.getStringExtra(UNIT_PRICE));  }
        else{
            setTitle("Add New Food");  }
    }

    public class FoodClickHandlers {
        Context context;

        private FoodClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if(foodType.getFoodName()==null){
                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_LONG).show();
            }else{
                Intent intent=new Intent();
                intent.putExtra(FOOD_NAME, foodType.getFoodName());
                intent.putExtra(UNIT_PRICE, foodType.getUnitPrice());
                setResult(RESULT_OK,intent);
                finish();
                Toast.makeText(context,"Successful add Food to the app",Toast.LENGTH_LONG).show();
            }
        }
    }
}
