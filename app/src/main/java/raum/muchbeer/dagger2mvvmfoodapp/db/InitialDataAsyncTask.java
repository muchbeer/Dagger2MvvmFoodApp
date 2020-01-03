package raum.muchbeer.dagger2mvvmfoodapp.db;

import android.os.AsyncTask;

import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodTypeDao;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFood;
import raum.muchbeer.dagger2mvvmfoodapp.model.TzFoodDao;

public class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {

    private TzFoodDao tzFoodDao;
    private FoodTypeDao foodTypeDao;


    public InitialDataAsyncTask(FoodDatabase instance) {
        tzFoodDao= instance.tzFoodDao();
        foodTypeDao= instance.foodTypeDao();
    }


    @Override
    protected Void doInBackground(Void... voids) {

        TzFood tzFood1=new TzFood();
        tzFood1.setPeriodName("Chakula Asubui");
        tzFood1.setPeriodDescription("Aina ya Chakula cha Asubui");

        TzFood tzFood2=new TzFood();
        tzFood2.setPeriodName("Chakula Mchana");
        tzFood2.setPeriodDescription("Aina ya Chakula cha Mchana");

        TzFood tzFood3=new TzFood();
        tzFood3.setPeriodName("Chakula Jion");
        tzFood3.setPeriodDescription("Aina ya Chakula cha Jion");

        tzFoodDao.insert(tzFood1);
        tzFoodDao.insert(tzFood2);
        tzFoodDao.insert(tzFood3);

        FoodType foodType1=new FoodType();
        foodType1.setFoodName("Chai Chapati ");
        foodType1.setUnitPrice("TZS1000");
        foodType1.setPeriodId(1);

        FoodType foodType2=new FoodType();
        foodType2.setFoodName("Chai Maandazi ");
        foodType2.setUnitPrice("TZS800");
        foodType2.setPeriodId(1);

        FoodType foodType3=new FoodType();
        foodType3.setFoodName("Chai Viaz ");
        foodType3.setUnitPrice("TZS600");
        foodType3.setPeriodId(1);

        FoodType foodType4=new FoodType();
        foodType4.setFoodName("Chai Chapati ");
        foodType4.setUnitPrice("TZS1000");
        foodType4.setPeriodId(1);

        FoodType foodType5=new FoodType();
        foodType5.setFoodName("Ugali Nyama ");
        foodType5.setUnitPrice("TZS2500");
        foodType5.setPeriodId(2);

        FoodType foodType6=new FoodType();
        foodType6.setFoodName("Ugali Samaki ");
        foodType6.setUnitPrice("TZS4500");
        foodType6.setPeriodId(2);

        FoodType foodType7=new FoodType();
        foodType7.setFoodName("Ndizi Samaki ");
        foodType7.setUnitPrice("TZS5000");
        foodType7.setPeriodId(2);

        FoodType foodType8=new FoodType();
        foodType8.setFoodName("Wali Mchicha ");
        foodType8.setUnitPrice("TZS1500");
        foodType8.setPeriodId(2);

        FoodType foodType9=new FoodType();
        foodType9.setFoodName("Kitimoto Ugali ");
        foodType9.setUnitPrice("TZS8000");
        foodType9.setPeriodId(2);

        FoodType foodType10=new FoodType();
        foodType10.setFoodName("Wali Kisamvu ");
        foodType10.setUnitPrice("TZS2000");
        foodType10.setPeriodId(2);

        FoodType foodType11=new FoodType();
        foodType11.setFoodName("Tambi ");
        foodType11.setUnitPrice("TZS1000");
        foodType11.setPeriodId(3);

        FoodType foodType12=new FoodType();
        foodType12.setFoodName("Kacholi ");
        foodType12.setUnitPrice("TZS3000");
        foodType12.setPeriodId(3);

        FoodType foodType13=new FoodType();
        foodType13.setFoodName("Mchemsho Kuku ");
        foodType13.setUnitPrice("TZS3000");
        foodType13.setPeriodId(3);

        FoodType foodType14=new FoodType();
        foodType14.setFoodName("Mchemsho Samaki ");
        foodType14.setUnitPrice("TZS1000");
        foodType14.setPeriodId(3);

        foodTypeDao.insert(foodType1);
        foodTypeDao.insert(foodType2);
        foodTypeDao.insert(foodType3);
        foodTypeDao.insert(foodType4);
        foodTypeDao.insert(foodType5);
        foodTypeDao.insert(foodType6);
        foodTypeDao.insert(foodType7);
        foodTypeDao.insert(foodType8);
        foodTypeDao.insert(foodType9);
        foodTypeDao.insert(foodType10);
        foodTypeDao.insert(foodType11);
        foodTypeDao.insert(foodType14);
        foodTypeDao.insert(foodType12);
        foodTypeDao.insert(foodType13);
        return null;
    }
}
