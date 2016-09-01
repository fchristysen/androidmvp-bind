package org.greenfroyo.androidmvp_bind.driver.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by fchristysen on 7/27/16.
 */
public interface DatabaseDriver {
    Uri insert(Uri uri, ContentValues contentValues);

    int update(Uri uri, ContentValues contentValues, String where, String[] selectionArgs);

    int delete(Uri uri, String where, String[] selectionArgs);

    Cursor query(Uri uri, String[] projection, String where, String[] selectionArgs, String sortOrder);

    void bulkInsert(Uri contentUri, ContentValues[] contentValues);
}
