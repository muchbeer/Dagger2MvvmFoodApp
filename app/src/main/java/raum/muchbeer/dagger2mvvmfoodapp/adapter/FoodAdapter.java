package raum.muchbeer.dagger2mvvmfoodapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import raum.muchbeer.dagger2mvvmfoodapp.R;
import raum.muchbeer.dagger2mvvmfoodapp.databinding.FoodItemBinding;
import raum.muchbeer.dagger2mvvmfoodapp.model.FoodType;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private OnItemClickListener listener;
    private ArrayList<FoodType> foodTypes=new ArrayList<>();

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FoodItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.food_item,
                parent,
                false);
        return new FoodViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodType food=foodTypes.get(position);

        holder.foodItemBinding.setFood(food);

    }

    @Override
    public int getItemCount() {
        return foodTypes.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private FoodItemBinding foodItemBinding;

        public FoodViewHolder(@NonNull FoodItemBinding  foodItemBinding) {
            super(foodItemBinding.getRoot());
            this.foodItemBinding = foodItemBinding;

            foodItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition=getAdapterPosition();

                    if(listener!=null && clickedPosition!=RecyclerView.NO_POSITION) {
                        listener.onItemClick(foodTypes.get(clickedPosition));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(FoodType food);
    }

    public void setListener(OnItemClickListener listener) { this.listener = listener; }

    public void setFoods(ArrayList<FoodType> newFoods) {
        final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new FoodsDiffCallback(foodTypes,newFoods),false);
        foodTypes=newFoods;
        result.dispatchUpdatesTo(FoodAdapter.this); }

}
