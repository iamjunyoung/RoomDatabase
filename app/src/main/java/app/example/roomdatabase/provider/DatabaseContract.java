package app.example.roomdatabase.provider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    //Database schema information
    public static int DB_VERSION = 5;
    public static final String POI_LIST_DB_FILE_NAME = "poilist.db";
    public static final String HOME_CHARGER_DB_FILE_NAME = "homecharger.db";
    public static final String TABLE_POI_LIST = "poilisttable";
    public static final String TABLE_HOME_CHARGER_POI = "homechargertable";

    public static String DATABASE_CREATE_TABLE_POI_LIST = "create table IF NOT EXISTS " +
            TABLE_POI_LIST + " (" +
            POIColumns.ID + " integer primary key autoincrement, " +
            POIColumns.POI_ID + " text, " +
            POIColumns.FLOOR_CODE + " text, " +
            POIColumns.FLOOR_NAME_EN + " text, " +
            POIColumns.FLOOR_NAME_KR + " text, " +
            POIColumns.FLOOR_INDEX + " text, " +
            POIColumns.ATTRIBUTE_EL_ID + " text, " +
            POIColumns.ATTRIBUTE_EL_VENDOR + " text, " +
            POIColumns.ATTRIBUTE_EL_FLOOR_LIST + " text, " +
            POIColumns.ATTRIBUTE_DESC + " text, " +
            POIColumns.ATTRIBUTE_TEL + " text, " +
            POIColumns.RADIUS + " real, " +
            POIColumns.IS_RESTRICTED + " integer, " +
            POIColumns.NAME_EN + " text, " +
            POIColumns.NAME_KR + " text, " +
            POIColumns.POSITION_X + " real, " +
            POIColumns.POSITION_Y + " real, " +
            POIColumns.THETA + " integer, " +
            POIColumns.TYPE + " integer, " +
            POIColumns.CANDIDATE_POI_ID + " text, " +
            POIColumns.ATTRIBUTE_DOOR + " text, " +
            POIColumns.ATTRIBUTE_IN_OUT + " text, " +
            POIColumns.ATTRIBUTE_MAC_ADDRESS + " text, " +
            POIColumns.ATTRIBUTE_COMPANY_NAME + " text);";

    public static String DATABASE_CREATE_TABLE_HOME_CHARGER_POI = "create table IF NOT EXISTS " +
            TABLE_HOME_CHARGER_POI + " (" +
            POIColumns.ID + " integer primary key autoincrement, " +
            POIColumns.POI_ID + " text, " +
            POIColumns.FLOOR_CODE + " text, " +
            POIColumns.FLOOR_NAME_EN + " text, " +
            POIColumns.FLOOR_NAME_KR + " text, " +
            POIColumns.FLOOR_INDEX + " text, " +
            POIColumns.ATTRIBUTE_EL_ID + " text, " +
            POIColumns.ATTRIBUTE_EL_VENDOR + " text, " +
            POIColumns.ATTRIBUTE_EL_FLOOR_LIST + " text, " +
            POIColumns.ATTRIBUTE_DESC + " text, " +
            POIColumns.ATTRIBUTE_TEL + " text, " +
            POIColumns.RADIUS + " real, " +
            POIColumns.IS_RESTRICTED + " integer, " +
            POIColumns.NAME_EN + " text, " +
            POIColumns.NAME_KR + " text, " +
            POIColumns.POSITION_X + " real, " +
            POIColumns.POSITION_Y + " real, " +
            POIColumns.THETA + " integer, " +
            POIColumns.TYPE + " integer, " +
            POIColumns.CANDIDATE_POI_ID + " text, " +
            POIColumns.ATTRIBUTE_DOOR + " text, " +
            POIColumns.ATTRIBUTE_IN_OUT + " text, " +
            POIColumns.ATTRIBUTE_MAC_ADDRESS + " text, " +
            POIColumns.ATTRIBUTE_COMPANY_NAME + " text);";

    public static final class POIColumns implements BaseColumns {
        public static final String ID = "_id";
        public static final String POI_ID = "poi_id";
        public static final String FLOOR_CODE = "floor_code";
        public static final String FLOOR_NAME_EN = "floor_name_en";
        public static final String FLOOR_NAME_KR = "floor_name_kr";
        public static final String FLOOR_INDEX = "floor_index";
        public static final String ATTRIBUTE_EL_ID = "attribute_el_id";
        public static final String ATTRIBUTE_EL_VENDOR = "attribute_el_vendor";
        public static final String ATTRIBUTE_EL_FLOOR_LIST = "attribute_el_floor_list";
        public static final String ATTRIBUTE_DESC = "attribute_desc";
        public static final String ATTRIBUTE_TEL = "attribute_tel";
        public static final String RADIUS = "radius";
        public static final String IS_RESTRICTED = "is_restricted";
        public static final String NAME_EN = "name_en";
        public static final String NAME_KR = "name_kr";
        public static final String POSITION_X = "position_x";
        public static final String POSITION_Y = "position_y";
        public static final String THETA = "theta";
        public static final String TYPE = "type";
        public static final String CANDIDATE_POI_ID = "candidate_poi_id";
        public static final String ATTRIBUTE_DOOR = "attribute_door";
        public static final String ATTRIBUTE_IN_OUT = "attribute_in_out";
        public static final String ATTRIBUTE_MAC_ADDRESS = "attribute_mac_address";
        public static final String ATTRIBUTE_COMPANY_NAME = "attribute_company_name";
    }

    public static final String[] COLUMNS = new String[] {POIColumns.ID, POIColumns.POI_ID, POIColumns.FLOOR_CODE, POIColumns.FLOOR_NAME_EN, POIColumns.FLOOR_NAME_KR
            , POIColumns.FLOOR_INDEX, POIColumns.ATTRIBUTE_EL_ID, POIColumns.ATTRIBUTE_EL_VENDOR, POIColumns.ATTRIBUTE_EL_FLOOR_LIST
            , POIColumns.ATTRIBUTE_DESC, POIColumns.ATTRIBUTE_TEL, POIColumns.RADIUS, POIColumns.IS_RESTRICTED, POIColumns.NAME_EN, POIColumns.NAME_KR
            , POIColumns.POSITION_X, POIColumns.POSITION_Y, POIColumns.THETA, POIColumns.TYPE, POIColumns.CANDIDATE_POI_ID, POIColumns.ATTRIBUTE_DOOR
            , POIColumns.ATTRIBUTE_IN_OUT, POIColumns.ATTRIBUTE_MAC_ADDRESS, POIColumns.ATTRIBUTE_COMPANY_NAME};

    public static final String CONTENT_AUTHORITY = "com.lge.robot.platform.poi";
    //Base content Uri for accessing the provider
    public static final Uri POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_POI_LIST)
            .build();

    public static final Uri HOME_CHARGER_POI_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_HOME_CHARGER_POI)
            .build();

    /* Helpers to retrieve column values */
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }

}
