package com.lambton.lab2_isharae_c0852812_android.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.model.ProductModel;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductAdapter;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductDatabaseAdapter;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductRecycleviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewProductsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSearchProducts;
    Button btnSearchByDesc, btnSearchByName;
    RecyclerView rvProductList;
    RecyclerView.LayoutManager layoutManager;
    ProductDatabaseAdapter productDatabaseAdapter = new ProductDatabaseAdapter(this);
    ArrayList<ProductModel> products = new ArrayList<>();
    ProductRecycleviewAdapter productAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        connectView();
        Cursor row = productDatabaseAdapter.getAllProducts();
        //row.moveToFirst();
        if (row.moveToFirst()){
            do {
                String column1 = row.getString(0);
                String column2 = row.getString(1);
                String column3 = row.getString(2);
                String column4 = row.getString(3);
                ProductModel data = new ProductModel(Integer.parseInt(column1), column2,column3,Double.parseDouble(column4));
                products.add(data);

            } while(row.moveToNext());
        }
        row.close();


        layoutManager = new LinearLayoutManager(ViewProductsActivity.this, LinearLayoutManager.VERTICAL, false);
        rvProductList.setLayoutManager(layoutManager);
        productAdapter = new ProductRecycleviewAdapter(products,ViewProductsActivity.this);
        rvProductList.setAdapter(productAdapter);

    }

    private void connectView(){
        etSearchProducts = findViewById(R.id.et_search_product);
        btnSearchByDesc = findViewById(R.id.btn_search_desc);
        btnSearchByName = findViewById(R.id.btn_search_name);
        rvProductList = findViewById(R.id.lv_productList);
        btnSearchByDesc.setOnClickListener(this);
        btnSearchByName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String searchText = etSearchProducts.getText().toString();
        Cursor cursor = null;
        if(v.getId() == R.id.btn_search_desc){
            cursor = productDatabaseAdapter.findProductByDescription(searchText);

        }else if(v.getId() == R.id.btn_search_name){
            cursor = productDatabaseAdapter.findProductByName(searchText);
        }

        if (cursor != null && cursor.moveToFirst()){
            do {
                // Passing values
                String column1 = cursor.getString(0);
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                String column4 = cursor.getString(3);
                // Do something Here with values
//                Log.d("DB_DEBUG_SEARCH", "col1: " + column1 + ", col2:" +
//                        column2 + ", col3:" + column3 + ", col4:" + column4);
                this.products.add(new ProductModel(Integer.parseInt(column1), column2, column3, Double.parseDouble(column4)));

            } while(cursor.moveToNext());
        }
        //productDatabaseAdapter.notifyDataSetChanged();
        Log.d("DB", products.toString());
        productAdapter.notifyDataSetChanged();
        cursor.close();

    }
}