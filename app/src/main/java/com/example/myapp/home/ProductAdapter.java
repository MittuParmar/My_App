package com.example.myapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<ProductItemModel> productItemModelList;

    public ProductAdapter(List<ProductItemModel> productItemModelList) {
        this.productItemModelList=productItemModelList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.productImage.setImageResource(productItemModelList.get(position).getImage());
        holder.productTitle.setText(productItemModelList.get(position).productTitle);

    }

    @Override
    public int getItemCount() {
        return productItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage=itemView.findViewById(R.id.productImage);
            productTitle=itemView.findViewById(R.id.productTitle);
        }
    }
}
