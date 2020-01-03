package raum.muchbeer.dagger2mvvmfoodapp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "food_type" ,foreignKeys = @ForeignKey(entity = TzFood.class,
        parentColumns = "id",childColumns = "food_period_id",onDelete = CASCADE),
        indices = {@Index(value = {"food_type_id"}, unique = true)}
      /*  indices = {@Index("food_type_id")}*/)
public class FoodType extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_type_id")
    private int foodId;

    @ColumnInfo(name = "food_name")
    private String foodName;

    @ColumnInfo(name = "unit_price")
    private String unitPrice;

    @ColumnInfo(name="food_period_id")
    private int periodId;

@Ignore
    public FoodType() {
    }

    public FoodType(int foodId, String foodName, String unitPrice, int periodId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.unitPrice = unitPrice;
        this.periodId = periodId;
    }

    @Bindable
    public int getFoodId() {
        return foodId;
    }

    @Bindable
    public String getFoodName() {
        return foodName;
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    @Bindable
    public int getPeriodId() {
        return periodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.foodId);
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.foodName);
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.unitPrice);
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.periodId);
    }
}
