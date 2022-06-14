package com.lambton.lab2_isharae_c0852812_android.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductDatabaseAdapter;

public class ViewProductsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSearchProducts;
    Button btnSearchByDesc, btnSearchByName;
    ListView lvProductList;
    ProductDatabaseAdapter productDatabaseAdapter = new ProductDatabaseAdapter(this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        connectView();
    }

    private void connectView(){
        etSearchProducts = findViewById(R.id.et_search_product);
        btnSearchByDesc = findViewById(R.id.btn_search_desc);
        btnSearchByName = findViewById(R.id.btn_search_name);
        //lvProductList = findViewById(R.id.lv_productList);
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
                Log.d("DB_DEBUG_SEARCH", "col1: " + column1 + ", col2:" +
                        column2 + ", col3:" + column3 + ", col4:" + column4);

            } while(cursor.moveToNext());
        }
        cursor.close();
    }
}