package com.lambton.lab2_isharae_c0852812_android.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductDatabaseAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddNewRecord, btnSearch;
    TextView tvProdTitle, tvProdDescription, tvProdPrice;
    ProductDatabaseAdapter productdbAdapter;

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        productdbAdapter = new ProductDatabaseAdapter(this);
        addStaticData();

        // get 1st record and display it
        Cursor c = productdbAdapter.getFirstProduct();

        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                String column4 = c.getString(3);
                // Do something Here with values
                Log.d("DB_DEBUG", "col1: " + column1 + ", col2:" +
                        column2 + ", col3:" + column3 + ", col4:" + column4);
                tvProdTitle.setText(column2);
                tvProdDescription.setText(column3);
                tvProdPrice.setText(column4);
            } while(c.moveToNext());
        }
        c.close();

    }

    private void connectView(){
        btnAddNewRecord = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnView);
        tvProdTitle = findViewById(R.id.tv_prod_name);
        tvProdDescription = findViewById(R.id.tv_prod_description);
        tvProdPrice = findViewById(R.id.tv_prod_price);
        btnSearch.setOnClickListener(this);
        btnAddNewRecord.setOnClickListener(this);
    }
    private void addStaticData(){
        productdbAdapter.addProduct("Book","Story Book",15.00);
        productdbAdapter.addProduct("Pen","Apple bulk",15.00);
        productdbAdapter.addProduct("Pencil","Apple bulk",15.00);
        productdbAdapter.addProduct("Art","Apple bulk",10.00);
        productdbAdapter.addProduct("Bag","Apple bulk",15.00);
        productdbAdapter.addProduct("Bed","Apple bulk",15.00);
        productdbAdapter.addProduct("Pillow","Apple bulk",15.00);
        productdbAdapter.addProduct("Carpet","Apple bulk",15.00);
        productdbAdapter.addProduct("Door","Apple bulk",15.00);
        productdbAdapter.addProduct("Window","Apple bulk",15.00);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdd){
            Intent addProductIntent = new Intent(this, AddProductActivity.class);
            startActivity(addProductIntent);
        }else if(v.getId() == R.id.btnView){
            Intent viewProductsIntent = new Intent(this, ViewProductsActivity.class);
            startActivity(viewProductsIntent);
        }
    }
}