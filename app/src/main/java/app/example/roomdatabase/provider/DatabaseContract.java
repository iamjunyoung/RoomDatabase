package app.example.roomdatabase.provider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_POI_LIST = "poilisttable";
    public static final String TABLE_HOME_POI_LIST = "hometable";
    public static final String TABLE_CHARGER_POI_LIST = "chargertable";
    public static final String TABLE_RETURNTRAY_POI_LIST = "returntraytable";
    public static final String TABLE_EL_POI_LIST = "eltable";
    public static final String TABLE_DOOR_POI_LIST = "hometable";

    public static final class POIColumns implements BaseColumns {
        public static final String ID = "_id";
        public static final String POI_ID = "poi_id";
        public static final String FLOOR_CODE = "floor_code";
        public static final String FLOOR_NAME_EN = "floor_name_en";
        public static final String FLOOR_NAME_KR = "floor_name_kr";
        public static final String FLOOR_INDEX = "floor_index";
        public static final String RADIUS = "radius";
        public static final String IS_RESTRICTED = "is_restricted";
        public static final String NAME_EN = "name_en";
        public static final String NAME_KR = "name_kr";
        public static final String POSITION_X = "position_x";
        public static final String POSITION_Y = "position_y";
        public static final String THETA = "theta";
        public static final String TYPE = "type";
        public static final String CANDIDATE_POI_ID = "candidate_poi_id";
        public static final String CURRENT_SET = "current_set";

        //For
        public static final String ATTRIBUTE_DESC = "attribute_desc";
        public static final String ATTRIBUTE_TEL = "attribute_tel";

        //For elevator attribute feature
        public static final String ATTRIBUTE_EL_ID = "attribute_el_id";
        public static final String ATTRIBUTE_EL_VENDOR = "attribute_el_vendor";
        public static final String ATTRIBUTE_EL_FLOOR_LIST = "attribute_el_floor_list";
        public static final String ATTRIBUTE_DOOR = "attribute_door";
        public static final String ATTRIBUTE_IN_OUT = "attribute_in_out";

        //For auto door attribute feature
        public static final String ATTRIBUTE_MAC_ADDRESS = "attribute_mac_address";
        public static final String ATTRIBUTE_COMPANY_NAME = "attribute_company_name";

    }

    public static final String CONTENT_AUTHORITY = "com.lge.robot.platform.poi";
    //Base content Uri for accessing the provider
    public static final Uri POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_POI_LIST)
            .build();

    public static final Uri HOME_POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_HOME_POI_LIST)
            .build();

    public static final Uri CHARGER_POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_CHARGER_POI_LIST)
            .build();

    public static final Uri RETURNTRAY_POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_RETURNTRAY_POI_LIST)
            .build();

    public static final Uri EL_POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_EL_POI_LIST)
            .build();

    public static final Uri DOOR_POI_LIST_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_DOOR_POI_LIST)
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
