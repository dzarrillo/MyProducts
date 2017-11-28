package com.dzartek.myproducts.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dzarrillo on 11/27/2017.
 */

public class ProductContract implements BaseColumns {
    // Name of the content provider,
    public static final String CONTENT_AUTHORITY = "com.dzartek.myproducts";
    //Path that point to the table
    public static final String PATH_VERSION = "product";
    // Construct the Base Content Uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Database name
    public static final String DB_NAME = "myproducts";

    /**
     Define table name
     */
    public static final class MyProduct {

        // Content Uri = Content Authority + Path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_VERSION).build();

        // Use MIME type prefix android.cursor.dir/ for returning multiple
        //items
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/com.dzartek.myproducts.myproduct";

        // Use MIME type prefix android.cursor.item/ for returning a
        // single item - tablename
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/com.dzartek.myproducts.myproduct";

        // Define table name
        public static final String TABLE_NAME = "myproduct";
        // Define table columns
        public static final String ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String REG_PRICE = "reg_price";
        public static final String SALE_PRICE = "sale_price";
        public static final String PRODUCT_PHOTO = "product_photo";
        public static final String COLOR = "color";
        public static final String STORE = "store";

        //  Define query for wine table
        public static final String[] PROJECTION = new String[] {
                ProductContract.MyProduct.ID,
                ProductContract.MyProduct.NAME,
                ProductContract.MyProduct.DESCRIPTION,
                ProductContract.MyProduct.REG_PRICE,
                ProductContract.MyProduct.SALE_PRICE,
                ProductContract.MyProduct.PRODUCT_PHOTO,
                ProductContract.MyProduct.COLOR,
                ProductContract.MyProduct.STORE
        };
    }
}
