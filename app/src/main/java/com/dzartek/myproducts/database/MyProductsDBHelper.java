package com.dzartek.myproducts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dzarrillo on 11/27/2017.
 */

public class MyProductsDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME =  ProductContract.DB_NAME;
    private final static int DB_VERSION = 1;
    private final static String TABLENAME = ProductContract.MyProduct.TABLE_NAME;

    public MyProductsDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Column names
    private final static String ID = ProductContract.MyProduct.ID;
    private final static String NAME = ProductContract.MyProduct.NAME;
    private final static String DESCRIPTION = ProductContract.MyProduct.DESCRIPTION;
    private final static String REG_PRICE = ProductContract.MyProduct.REG_PRICE;
    private final static String SALE_PRICE = ProductContract.MyProduct.SALE_PRICE;
    private final static String PRODUCT_PHOTO = ProductContract.MyProduct.PRODUCT_PHOTO;
    private final static String COLOR = ProductContract.MyProduct.COLOR;
    private final static String STORE = ProductContract.MyProduct.STORE;

    // SQL statement to create myproduct table
    private final static String MYPRODUCT_TABLE_CREATE =
            "CREATE TABLE " + TABLENAME + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + NAME + " TEXT NOT NULL,"
                    + DESCRIPTION + " TEXT NOT NULL,"
                    + REG_PRICE + " TEXT NOT NULL,"
                    + SALE_PRICE + " TEXT NOT NULL,"
                    + DESCRIPTION + " TEXT NOT NULL,"
                    + PRODUCT_PHOTO + " TEXT NOT NULL,"
                    + COLOR + " TEXT NOT NULL,"
                    + STORE + " TEXT NOT NULL"
                    + ");";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create MYPRODUCT table
        sqLiteDatabase.execSQL(MYPRODUCT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // Just in case if I create another table with foreign key:
        // - By default foreign_key=off - cascading deletes will not work
        db.execSQL("PRAGMA foreign_keys=ON");
    }
}
