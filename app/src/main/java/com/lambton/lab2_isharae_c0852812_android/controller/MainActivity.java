package com.lambton.lab2_isharae_c0852812_android.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductDatabaseAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddNewRecord, btnSearch;
    ProductDatabaseAdapter productdbAdapter;

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        productdbAdapter = new ProductDatabaseAdapter(this);
        productdbAdapter.addProduct("Apple","Apple bulk",15.00);
        productdbAdapter.addProduct("Apple1","Apple bulk",15.00);
        productdbAdapter.addProduct("Apple2","Apple bulk",15.00);
        productdbAdapter.addProduct("Apple3","Apple bulk",15.00);
        productdbAdapter.addProduct("Apple4","Apple bulk",15.00);
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
            } while(c.moveToNext());
        }
        c.close();

    }

    private void connectView(){
        btnAddNewRecord = findViewById(R.id.btnAddNew);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        btnAddNewRecord.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddNew){
            Intent addProductIntent = new Intent(this, AddProductActivity.class);
            startActivity(addProductIntent);
        }else if(v.getId() == R.id.btnSearch){
            Toast.makeText(this, "Search for something", Toast.LENGTH_SHORT).show();
        }
    }
}