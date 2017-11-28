package com.dzartek.myproducts.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

public class ProductContentProvider extends ContentProvider {
    private static final int VERSION = 100;
    private static final int VERSION_ID = 200;
    private static final UriMatcher mUriMatcher = createUriMatcher();
    private MyProductsDBHelper mDbHelper;

    public ProductContentProvider() {
    }

    private static UriMatcher createUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ProductContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(authority, ProductContract.PATH_VERSION, VERSION);
        uriMatcher.addURI(authority, ProductContract.PATH_VERSION + "/#", VERSION_ID);

        return uriMatcher;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = mUriMatcher.match(uri);
        int deletionCount;

        switch (uriType) {
            case VERSION:
                deletionCount = db.delete(ProductContract.MyProduct.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        if (getContext()!=null){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deletionCount;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (mUriMatcher.match(uri)){
            case VERSION:
                return ProductContract.MyProduct.CONTENT_TYPE;
            case VERSION_ID:
                return ProductContract.MyProduct.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = mUriMatcher.match(uri);
        long rowId;

        switch (uriType) {
            case VERSION:
                rowId = db.insert(ProductContract.MyProduct.TABLE_NAME, null, values);
                if(getContext()!=null){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return ContentUris.withAppendedId(ProductContract.MyProduct.CONTENT_URI, rowId);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new MyProductsDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Use SQLiteQueryBuilder for querying db
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Set the tablename
        queryBuilder.setTables(ProductContract.MyProduct.TABLE_NAME);

        // record id
        String id;

        // match uri pattern
        int uriType = mUriMatcher.match(uri);

        switch (uriType){
            case VERSION:
                break;
            case VERSION_ID:

                selection = ProductContract.MyProduct.ID + " = ? ";
                id = uri.getLastPathSegment();
                selectionArgs = new String[] {id};
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if(getContext() != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
