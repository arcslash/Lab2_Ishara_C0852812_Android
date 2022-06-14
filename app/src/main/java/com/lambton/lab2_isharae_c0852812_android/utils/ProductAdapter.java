package com.lambton.lab2_isharae_c0852812_android.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.model.ProductModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    public List<ProductModel> mProducts;

    public ProductAdapter(List<ProductModel> mProducts) {
        this.mProducts = mProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.product_list_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        ProductModel product = mProducts.get(position);
        Log.d("DB", product.toString());
        // Set item views based on your views and data model


        holder.etProductName.setText(product.getProductName());
        holder.etProductDescription.setText(product.getProductDescription());
        holder.etProductPrice.setText(String.valueOf(product.getProductPrice()));

    }

    @Override
    public int getItemCount() {
        return this.mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         public EditText etProductName, etProductDescription, etProductPrice;
         Button btnProductEdit, btnProductDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            etProductName = itemView.findViewById(R.id.et_product_name);
            etProductDescription = itemView.findViewById(R.id.et_product_description);
            etProductPrice = itemView.findViewById(R.id.et_product_price);
            btnProductEdit = itemView.findViewById(R.id.btn_edit);
            btnProductDelete = itemView.findViewById(R.id.btn_delete);

        }
    }
}


