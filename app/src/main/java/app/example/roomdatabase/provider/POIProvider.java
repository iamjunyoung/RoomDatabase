package app.example.roomdatabase.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


public class POIProvider extends ContentProvider {
    private static final String TAG = "[RP]" + POIProvider.class.getSimpleName();

    private static final int CLEANUP_JOB_ID = 43;
    private static final int POIS = 100;
    private static final int POIS_WITH_ID = 101;
    private static final int HOMECHARGER_POIS = 102;
    private static final int HOMECHARGER_POIS_WITH_ID = 103;
    //POIListDBHelper mPOILISTOpenHelper = null;
    //HomeChargerDBHelper mHomeChargerDBHelper = null;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // content://com.lge.robot.platform.poi/poilisttable
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_POI_LIST,
                POIS);

        // content://com.lge.robot.platform.poi/poilisttable/id
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_POI_LIST + "/#",
                POIS_WITH_ID);

        // content://com.lge.robot.platform.poi/homechargertable
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_HOME_CHARGER_POI,
                HOMECHARGER_POIS);

        // content://com.lge.robot.platform.poi/homechargertable/id
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_HOME_CHARGER_POI + "/#",
                HOMECHARGER_POIS_WITH_ID);
    }

    public POIProvider() {
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "POIManager ContentProvider onCreate" );
        //mPOILISTOpenHelper = POIListDBHelper.getInstance(getContext());
        //mHomeChargerDBHelper = HomeChargerDBHelper.getInstance(getContext());
        //if (mPOILISTOpenHelper != null && mHomeChargerDBHelper != null) {
        //    return true;
        //}
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        String type = getType(uri);
        if ("homecharger".equals(type)) {
            //SQLiteDatabase db = mHomeChargerDBHelper.getWritableDatabase();
            //count = db.delete(TABLE_HOME_CHARGER_POI, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_HOME_CHARGER_POI" );
        } else {
            //SQLiteDatabase db = mPOILISTOpenHelper.getWritableDatabase();
            //count = db.delete(TABLE_POI_LIST, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_POI_LIST" );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case POIS:
                return "poilist";
            case POIS_WITH_ID:
                return "poilist/poi";
            case HOMECHARGER_POIS:
                return "homecharger";
            case HOMECHARGER_POIS_WITH_ID:
                return "homecharger/poi";
            default:
                return null;
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = 0;
        String type = getType(uri);
        if ("homecharger".equals(type)) {
            //SQLiteDatabase db = mHomeChargerDBHelper.getWritableDatabase();
            //rowId = db.insert(DatabaseContract.TABLE_HOME_CHARGER_POI, null, values);
            Log.d(TAG, "POIManager insert " + values + " in TABLE_HOME_CHARGER_POI" );
        } else {
            // Is there any cases to delete normal poi in poi list from json?
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String tableName = null;
        String type = getType(uri);
        if ("homecharger".equals(type)) {
            //db = mHomeChargerDBHelper.getWritableDatabase();
            tableName = DatabaseContract.TABLE_HOME_CHARGER_POI;
        } else {
            //db = mPOILISTOpenHelper.getWritableDatabase();
            tableName = DatabaseContract.TABLE_POI_LIST;
        }
        if (db != null) {
            c = db.query(tableName,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
        }
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update() poi id by " + values.getAsString("poi_id"));

        int count = 0;
        SQLiteDatabase db = null;
        String type = getType(uri);
        if ("homecharger".equals(type)) {
            //db = mHomeChargerDBHelper.getWritableDatabase();
            Log.d(TAG, "update() update db");

            count = db.update(DatabaseContract.TABLE_HOME_CHARGER_POI, values, selection, selectionArgs);
        } else {
            // Is there any cases to delete normal poi in poi list?
        }
        return count;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        if("dropTable()".equals(method) == true){
            return dropTable();
        }
        return null;
    }

    public Bundle dropTable() {
        //int removeRowCount = getAllTableCount();
        //Log.d(TAG, "dropTable() row count : " + removeRowCount);
        //SQLiteDatabase homeChargerDB = mHomeChargerDBHelper.getWritableDatabase();
        //SQLiteDatabase poiListDB = mHomeChargerDBHelper.getWritableDatabase();

        //homeChargerDB.execSQL("DROP TABLE IF EXISTS " + TABLE_HOME_CHARGER_POI);
        //poiListDB.execSQL("DROP TABLE IF EXISTS " + TABLE_POI_LIST);

        //homeChargerDB.execSQL(DATABASE_CREATE_TABLE_HOME_CHARGER_POI);
        //poiListDB.execSQL(DATABASE_CREATE_TABLE_POI_LIST);

        Bundle returnData = new Bundle();
        //returnData.putInt("count", removeRowCount);

        return returnData;
    }

    public Cursor getCusorFromUriBySelection(Uri uri, String[] projections, String selections, String[] selectionArgs) {
        return query(uri
                , projections
                , selections
                , selectionArgs
                , null);
    }

    /*
    public int getAllTableCount() {
        int count = 0;
        Cursor c = getCusorFromUriBySelection(POI_LIST_CONTENT_URI, DatabaseContract.COLUMNS, null, null);
        Cursor c2 = getCusorFromUriBySelection(HOME_CHARGER_POI_CONTENT_URI, DatabaseContract.COLUMNS, null, null);
        if (c != null) {
            count = c.getCount();
            c.close();
        }
        if (c2 != null) {
            count += c2.getCount();
            c2.close();
        }
        return count;
    }
     */

    /*
    public int getPOIListTableCount() {
        int count = 0;
        Cursor c = getCusorFromUriBySelection(POI_LIST_CONTENT_URI, DatabaseContract.COLUMNS, null, null);
        if (c != null) {
            count = c.getCount();
            c.close();
        }
        return count;
    }

    public long getAllDataSize() {
        long count = 0;
        long countHomeAndCharger = 0;
        mPOILISTOpenHelper = POIListDBHelper.getInstance(getContext());
        mHomeChargerDBHelper = HomeChargerDBHelper.getInstance(getContext());
        if (mPOILISTOpenHelper != null && mHomeChargerDBHelper != null) {
            count = DatabaseUtils.queryNumEntries(mPOILISTOpenHelper.getReadableDatabase(), TABLE_POI_LIST);
            countHomeAndCharger = DatabaseUtils.queryNumEntries(mHomeChargerDBHelper.getReadableDatabase(), TABLE_HOME_CHARGER_POI);
        }
        return count + countHomeAndCharger;
    }
     */
}
