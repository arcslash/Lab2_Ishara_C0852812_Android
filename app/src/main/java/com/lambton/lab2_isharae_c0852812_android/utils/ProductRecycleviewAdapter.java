package com.lambton.lab2_isharae_c0852812_android.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.controller.EditProductActivity;
import com.lambton.lab2_isharae_c0852812_android.model.ProductModel;

import java.util.ArrayList;

public class ProductRecycleviewAdapter extends RecyclerView.Adapter<ProductRecycleviewAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<ProductModel> productDataArrayList;
    private Context mcontext;
    ProductDatabaseAdapter dbAdapter;

    // creating a constructor class.
    public ProductRecycleviewAdapter(ArrayList<ProductModel> recyclerDataArrayList, Context mcontext) {
        this.productDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        ProductModel recyclerData = productDataArrayList.get(position);
        dbAdapter=new ProductDatabaseAdapter(mcontext);
        holder.prod_name.setText(recyclerData.getProductName());
        holder.prod_desc.setText(recyclerData.getProductDescription());
        holder.prod_price.setText(String.valueOf(recyclerData.getProductPrice()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(mcontext, EditProductActivity.class);
                i.putExtra("prod_id",String.valueOf(recyclerData.getProductId()));
                i.putExtra("prod_name",recyclerData.getProductName());
                i.putExtra("prod_desc",recyclerData.getProductDescription());
                i.putExtra("prod_price",String.valueOf(recyclerData.getProductPrice()));
                mcontext.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.deleteProduct(recyclerData.getProductId());
                notifyDataSetChanged();
                productDataArrayList = new ArrayList<>();
                // mcontext.startActivity(new Intent(mcontext, ViewProductsActivity.class));
                Cursor c = dbAdapter.getAllProducts();

                if (c != null && c.moveToFirst()){
                    do {
                        // Passing values
                        String column1 = c.getString(0);
                        String column2 = c.getString(1);
                        String column3 = c.getString(2);
                        String column4 = c.getString(3);
                        // Do something Here with values
//                Log.d("DB_DEBUG_SEARCH", "col1: " + column1 + ", col2:" +
//                        column2 + ", col3:" + column3 + ", col4:" + column4);
                        ProductModel data = new ProductModel(Integer.parseInt(column1), column2,column3,Double.parseDouble(column4));
                        productDataArrayList.add(data);
                    } while(c.moveToNext());
                }
                filterList(productDataArrayList);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns
        // the size of recyclerview
        return productDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // creating a variable for our text view.
        private TextView prod_name,prod_desc,prod_price;
        private Button edit,delete;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_name = itemView.findViewById(R.id.et_prod_name);
            prod_desc = itemView.findViewById(R.id.et_prod_description);
            prod_price = itemView.findViewById(R.id.et_prod_price);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
        }
    }
    public void filterList(ArrayList<ProductModel> filteredList) {
        productDataArrayList = filteredList;
        notifyDataSetChanged();
    }
}
