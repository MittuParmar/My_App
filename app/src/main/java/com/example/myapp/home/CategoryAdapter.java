package com.example.myapp.home;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List <CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        if (position==0){
            holder.categoryImage.setImageResource(categoryModelList.get(position).getImage());
            holder.categoryImage.setImageTintList(ColorStateList.valueOf(R.color.colorPrimary));
            holder.categoryName.setText(categoryModelList.get(position).getCategoryName());
        }
        holder.categoryImage.setImageResource(categoryModelList.get(position).getImage());
        holder.categoryName.setText(categoryModelList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage=itemView.findViewById(R.id.categoryImage);
            categoryName=itemView.findViewById(R.id.categoryName);

        }
    }
}
