package org.greenfroyo.androidmvp_bind.driver.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by fchristysen on 7/27/16.
 */

public class ResolverDatabase implements DatabaseDriver {
    private final ContentResolver mResolver;

    public ResolverDatabase(Context context) {
        mResolver = context.getContentResolver();
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues){
        return mResolver.insert(uri, contentValues);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String where, String[] selectionArgs){
        return mResolver.update(uri, contentValues, where, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String where, String[] selectionArgs){
        return mResolver.delete(uri, where, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] selectionArgs, String sortOrder){
        return mResolver.query(uri, projection, where, selectionArgs, sortOrder);
    }

    @Override
    public void bulkInsert(Uri contentUri, ContentValues[] contentValues) {
        mResolver.bulkInsert(contentUri, contentValues);
    }
}
