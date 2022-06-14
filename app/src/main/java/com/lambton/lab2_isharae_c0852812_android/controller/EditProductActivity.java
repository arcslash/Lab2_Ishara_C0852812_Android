package com.lambton.lab2_isharae_c0852812_android.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductDatabaseAdapter;

public class EditProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    String id,name, desc,price;
    ProductDatabaseAdapter productDatabaseAdapter = new ProductDatabaseAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        connectView();
        Intent i=getIntent();
        id = i.getStringExtra("prod_id");
        name=i.getStringExtra("prod_name");
        desc = i.getStringExtra("prod_desc");
        price= i.getStringExtra("prod_price");
        //Log.d("prod_id",prod_id);
        etProductName.setText(name);
        etProductDescription.setText(desc);
        etProductPrice.setText(price);

    }
    private void connectView(){
        etProductName = findViewById(R.id.et_product_name);
        etProductDescription = findViewById(R.id.et_product_description);
        etProductPrice = findViewById(R.id.et_product_price);
        btnAddToDb = findViewById(R.id.btn_add);
        btnAddToDb.setText("Edit Product");
        btnAddToDb.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add){
            productDatabaseAdapter.updateProduct(Integer.parseInt(id),etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString()));
            startActivity(new Intent(EditProductActivity.this, ViewProductsActivity.class));
        }
    }
}