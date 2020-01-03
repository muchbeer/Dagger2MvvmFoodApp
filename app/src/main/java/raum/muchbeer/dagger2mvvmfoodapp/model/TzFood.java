package raum.muchbeer.dagger2mvvmfoodapp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tz_food_period")
public class TzFood  extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "food_period")
    private String periodName;

    @ColumnInfo(name = "food_period_description")
    private String periodDescription;

    public TzFood() {
    }

    public TzFood(int id, String periodName, String periodDescription) {
        this.id = id;
        this.periodName = periodName;
        this.periodDescription = periodDescription;
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getPeriodName() {
        return periodName;
    }

    @Bindable
    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.id);
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.periodName);
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
        notifyPropertyChanged(raum.muchbeer.dagger2mvvmfoodapp.BR.periodDescription);
    }


    @Override
    public String toString() {
        return this.periodName;
    }

}
