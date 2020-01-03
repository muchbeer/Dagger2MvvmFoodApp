package raum.muchbeer.dagger2mvvmfoodapp.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;

public class FoodsDiffCallback extends DiffUtil.Callback {
    ArrayList<FoodType> oldFoodsList;
    ArrayList<FoodType> newFoodsList;

    public FoodsDiffCallback(ArrayList<FoodType> oldFoodsList, ArrayList<FoodType> newFoodsList) {
        this.oldFoodsList = oldFoodsList;
        this.newFoodsList = newFoodsList;
    }

    @Override
    public int getOldListSize() {
        return oldFoodsList == null ? 0 : oldFoodsList.size();
    }

    @Override
    public int getNewListSize() {
        return newFoodsList == null ? 0 :newFoodsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldFoodsList.get(oldItemPosition).getFoodId()==newFoodsList.get(newItemPosition).getFoodId();

    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldFoodsList.get(oldItemPosition).equals(newFoodsList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition) {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }

}
