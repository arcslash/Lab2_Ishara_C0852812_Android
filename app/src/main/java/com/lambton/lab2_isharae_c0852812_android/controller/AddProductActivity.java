package com.lambton.lab2_isharae_c0852812_android.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lambton.lab2_isharae_c0852812_android.R;
import com.lambton.lab2_isharae_c0852812_android.utils.ProductDatabaseAdapter;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    ProductDatabaseAdapter productDatabaseAdapter = new ProductDatabaseAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        connectView();

    }
    private void connectView(){
        etProductName = findViewById(R.id.et_product_name);
        etProductDescription = findViewById(R.id.et_product_description);
        etProductPrice = findViewById(R.id.et_product_price);
        btnAddToDb = findViewById(R.id.btn_add);
        btnAddToDb.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add){
            String productName = etProductName.getText().toString();
            String productDescription = etProductDescription.getText().toString();
            Double productPrice = Double.parseDouble(etProductPrice.getText().toString());
            productDatabaseAdapter.addProduct(productName,
                    productDescription,
                    productPrice);
            Toast.makeText(this, "Product:" + productName + " added successfully!", Toast.LENGTH_SHORT).show();

        }
    }
}