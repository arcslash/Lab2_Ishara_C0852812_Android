package com.lambton.lab2_isharae_c0852812_android.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ProductDatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products_inventory";


    private static final String TABLE_NAME = "PRODUCT";
    private static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    private static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    private static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";


    public ProductDatabaseAdapter(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_PRODUCT_ID + " INTEGER NOT NULL CONSTRAINT product_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_PRODUCT_DESCRIPTION + " VARCHAR(50) NOT NULL, " +
                COLUMN_PRODUCT_PRICE + " DOUBLE NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table and then create it
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // insert product
    public boolean addProduct(String productName, String productDescription, double productPrice) {
        // we need a writeable instance of SQLite database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // we need to define a content values in order to insert data into our database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, productName);
        contentValues.put(COLUMN_PRODUCT_DESCRIPTION, productDescription);
        contentValues.put(COLUMN_PRODUCT_PRICE, String.valueOf(productPrice));

        // the insert method associated to SQLite database instance returns -1 if nothing is inserted
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }



    /**
     * Query database - select all the product
     * @return cursor
     * */
    public Cursor getAllProducts() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY product_id DESC", null);
    }


    /**
     * Query database - select all the product
     * @return cursor
     * */
    public Cursor getFirstProduct() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " LIMIT 1", null);
    }

    /**
     * Query database - select all the product name
     * @return cursor
     * */
    public Cursor findProductByName(String productName) {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE PRODUCT_NAME LIKE'%" + productName + "%'", null);
    }


    /**
     * Query database - select all the product description
     * @return cursor
     * */
    public Cursor findProductByDescription(String productDescription) {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE PRODUCT_DESCRIPTION LIKE '%" + productDescription + "%'", null);
    }
    /**
     * Update product in database
     * @param productId
     * @param productName
     * @param productDescription
     * @param productPrice
     * @return boolean value - true (successful)
     * */
    public boolean updateProduct(int productId, String productName, String productDescription, double productPrice) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, productName);
        contentValues.put(COLUMN_PRODUCT_DESCRIPTION, productDescription);
        contentValues.put(COLUMN_PRODUCT_PRICE, String.valueOf(productPrice));

        // update method associated to SQLite database instance returns number of rows affected
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)}) > 0;
    }

    /**
     * Delete product from database table
     * @param productId
     * @return true if is successful
     * */
    public boolean deleteProduct(int productId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // the delete method associated to the SQLite database instance returns the number of rows affected
        return sqLiteDatabase.delete(TABLE_NAME,
                COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)}) > 0;
    }
}
