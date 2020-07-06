package app.example.roomdatabase.provider;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.example.roomdatabase.Attribute;
import app.example.roomdatabase.AttributeDoor;
import app.example.roomdatabase.AttributeEL;
import app.example.roomdatabase.AttributeNormal;
import app.example.roomdatabase.ChargerPOI;
import app.example.roomdatabase.ChargerPOIRepository;
import app.example.roomdatabase.DoorPOI;
import app.example.roomdatabase.DoorPOIRepository;
import app.example.roomdatabase.ELPOI;
import app.example.roomdatabase.ELPOIRepository;
import app.example.roomdatabase.FloorName;
import app.example.roomdatabase.HomePOI;
import app.example.roomdatabase.HomePOIRepository;
import app.example.roomdatabase.Name;
import app.example.roomdatabase.POI;
import app.example.roomdatabase.POIListPOI;
import app.example.roomdatabase.POIListPOIRepository;
import app.example.roomdatabase.Position;
import app.example.roomdatabase.ReturnTrayPOI;
import app.example.roomdatabase.ReturnTrayPOIRepository;

public class POIProvider extends ContentProvider {
    private static final String TAG = "[RP]" + POIProvider.class.getSimpleName();

    private static final int CLEANUP_JOB_ID = 43;
    private static final int POI_LIST_POIS = 100;
    private static final int POI_LIST_POIS_WITH_ID = 101;
    private static final int HOME_POIS = 102;
    private static final int HOME_POIS_WITH_ID = 103;
    private static final int CHARGER_POIS = 104;
    private static final int CHARGER_POIS_WITH_ID = 105;
    private static final int RETURNTRAY_POIS = 106;
    private static final int RETURNTRAY_POIS_WITH_ID = 107;
    private static final int EL_POIS = 108;
    private static final int EL_POIS_WITH_ID = 109;
    private static final int DOOR_POIS = 110;
    private static final int DOOR_POIS_WITH_ID = 111;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // content://com.lge.robot.platform.poi/poilisttable
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_POI_LIST,
                POI_LIST_POIS);

        // content://com.lge.robot.platform.poi/poilisttable/id
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_POI_LIST + "/#",
                POI_LIST_POIS_WITH_ID);

        // content://com.lge.robot.platform.poi/homechargertable
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_HOME_POI_LIST,
                HOME_POIS);

        // content://com.lge.robot.platform.poi/homechargertable/id
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_HOME_POI_LIST + "/#",
                HOME_POIS_WITH_ID);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_CHARGER_POI_LIST,
                CHARGER_POIS);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_CHARGER_POI_LIST + "/#",
                CHARGER_POIS_WITH_ID);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_RETURNTRAY_POI_LIST,
                RETURNTRAY_POIS);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_RETURNTRAY_POI_LIST + "/#",
                RETURNTRAY_POIS_WITH_ID);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_EL_POI_LIST,
                EL_POIS);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_EL_POI_LIST + "/#",
                EL_POIS_WITH_ID);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_DOOR_POI_LIST,
                DOOR_POIS);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_DOOR_POI_LIST + "/#",
                DOOR_POIS_WITH_ID);
    }

    private HomePOIRepository homePOIRepository;
    private ChargerPOIRepository chargerPOIRepository;
    private ReturnTrayPOIRepository returnTrayPOIRepository;
    private POIListPOIRepository poiListPOIRepository;
    private ELPOIRepository elPOIRepository;
    private DoorPOIRepository doorPOIRepository;

    private LiveData<List<HomePOI>> allHomePOIs;
    private LiveData<List<ChargerPOI>> allChargerPOIs;
    private LiveData<List<ReturnTrayPOI>> allReturnTrayPOIs;
    private LiveData<List<POIListPOI>> allPOIListPOIs;
    private LiveData<List<ELPOI>> allELPOIs;
    private LiveData<List<DoorPOI>> allDoorPOIs;


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

        homePOIRepository = new HomePOIRepository((Application)getContext());
        chargerPOIRepository = new ChargerPOIRepository((Application)getContext());
        returnTrayPOIRepository = new ReturnTrayPOIRepository((Application)getContext());
        poiListPOIRepository = new POIListPOIRepository((Application)getContext());
        elPOIRepository = new ELPOIRepository((Application)getContext());
        doorPOIRepository = new DoorPOIRepository((Application) getContext());

        allHomePOIs = homePOIRepository.getAllPOIs();
        allChargerPOIs = chargerPOIRepository.getAllPOIs();
        allReturnTrayPOIs = returnTrayPOIRepository.getAllPOIs();
        allPOIListPOIs = poiListPOIRepository.getAllPOIs();
        allELPOIs = elPOIRepository.getAllPOIs();
        allDoorPOIs = doorPOIRepository.getAllPOIs();

        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        String type = getType(uri);
        if ("poilist".equals(type)) {
            //SQLiteDatabase db = mHomeChargerDBHelper.getWritableDatabase();
            //count = db.delete(TABLE_HOME_CHARGER_POI, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_HOME_CHARGER_POI" );
        } else if ("home".equals(type)) {
            //SQLiteDatabase db = mPOILISTOpenHelper.getWritableDatabase();
            //count = db.delete(TABLE_POI_LIST, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_POI_LIST" );
        } else if ("charger".equals(type)) {
            //SQLiteDatabase db = mPOILISTOpenHelper.getWritableDatabase();
            //count = db.delete(TABLE_POI_LIST, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_POI_LIST" );
        } else if ("returntray".equals(type)) {
            //SQLiteDatabase db = mPOILISTOpenHelper.getWritableDatabase();
            //count = db.delete(TABLE_POI_LIST, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_POI_LIST" );
        } else if ("el".equals(type)) {
            //SQLiteDatabase db = mPOILISTOpenHelper.getWritableDatabase();
            //count = db.delete(TABLE_POI_LIST, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_POI_LIST" );
        } else if ("door".equals(type)) {
            //SQLiteDatabase db = mPOILISTOpenHelper.getWritableDatabase();
            //count = db.delete(TABLE_POI_LIST, selection, selectionArgs);
            Log.d(TAG, "POIManager delete " + selection + " in TABLE_POI_LIST" );
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
            case POI_LIST_POIS:
                return "poilist";
            case POI_LIST_POIS_WITH_ID:
                return "poilist/poi";
            case HOME_POIS:
                return "home";
            case HOME_POIS_WITH_ID:
                return "home/poi";
            case CHARGER_POIS:
                return "charger";
            case CHARGER_POIS_WITH_ID:
                return "charger/poi";
            case RETURNTRAY_POIS:
                return "returntray";
            case RETURNTRAY_POIS_WITH_ID:
                return "returntray/poi";
            case EL_POIS:
                return "el";
            case EL_POIS_WITH_ID:
                return "el/poi";
            case DOOR_POIS:
                return "door";
            case DOOR_POIS_WITH_ID:
                return "door/poi";
            default:
                return null;
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Get POI from contentValues from POIDBManager
        // after that, inert this.
        long rowId = 0;
        String type = getType(uri);
        if ("poilist".equals(type)) {
            poiListPOIRepository.insert(getPOIListPOIfromContentValues(values));
        } else if ("home".equals(type)) {
            homePOIRepository.insert(getHomePOIfromContentValues(values));
        } else if ("charger".equals(type)) {
            chargerPOIRepository.insert(getChargerPOIfromContentValues(values));
        } else if ("returntray".equals(type)) {
            returnTrayPOIRepository.insert(getReturnTrayPOIfromContentValues(values));
        } else if ("el".equals(type)) {
            elPOIRepository.insert(getELPOIfromContentValues(values));
        } else if ("door".equals(type)) {
            doorPOIRepository.insert(getDoorPOIfromContentValues(values));
        } else {

        }

        /*
        if (poi instanceof HomePOI) {

        } else if (poi instanceof ChargerPOI) {
            chargerPOIRepository.insert((ChargerPOI) poi);
        } else if (poi instanceof ReturnTrayPOI) {
            returnTrayPOIRepository.insert((ReturnTrayPOI) poi);
        } else if (poi instanceof POIListPOI) {
            poiListPOIRepository.insert((POIListPOI) poi);
        } else if (poi instanceof ELPOI) {
            Log.d("UserViewModel", "insert to EL Table " + poi);
            elPOIRepository.insert((ELPOI) poi);
        } else if (poi instanceof DoorPOI) {
            doorPOIRepository.insert((DoorPOI) poi);
        } else {

        }
         */

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
            //tableName = DatabaseContract.TABLE_HOME_CHARGER_POI;
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

            //count = db.update(DatabaseContract.TABLE_HOME_CHARGER_POI, values, selection, selectionArgs);
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

    public static POIListPOI getPOIListPOIfromContentValues(ContentValues contentValues) {
        POIListPOI poi = new POIListPOI();
        AttributeNormal attribute = new AttributeNormal();
        FloorName floorName = new FloorName();
        Position pos = new Position();
        Name name = new Name();

        if (contentValues.containsKey(DatabaseContract.POIColumns.POI_ID)) {
            poi.setPoiId(contentValues.getAsString(DatabaseContract.POIColumns.POI_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_CODE)) {
            poi.setFloorCode(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_CODE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_EN)) {
            floorName.setEn(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_EN));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_KR)) {
            floorName.setKr(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_KR));
        }
        poi.setFloorName(floorName);

        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_INDEX)) {
            poi.setFloorIndex(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_INDEX));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_X)) {
            pos.setX(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_X));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_Y)) {
            pos.setY(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_Y));
        }
        poi.setPosition(pos);

        if (contentValues.containsKey(DatabaseContract.POIColumns.NAME_KR)) {
            name.setKr(contentValues.getAsString(DatabaseContract.POIColumns.NAME_KR));
        }
        poi.setName(name);

        if (contentValues.containsKey(DatabaseContract.POIColumns.RADIUS)) {
            poi.setRadius(contentValues.getAsInteger(DatabaseContract.POIColumns.RADIUS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.TYPE)) {
            poi.setType(contentValues.getAsInteger(DatabaseContract.POIColumns.TYPE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.IS_RESTRICTED)) {
            poi.setRestricted(contentValues.getAsInteger(DatabaseContract.POIColumns.IS_RESTRICTED));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.THETA)) {
            poi.setTheta(contentValues.getAsInteger(DatabaseContract.POIColumns.THETA));
        }

        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DESC)) {
            attribute.setDesc(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DESC));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_TEL)) {
            attribute.setTel(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_TEL));
        }
        poi.setAttribute(attribute);

        return poi;
    }

    public static HomePOI getHomePOIfromContentValues(ContentValues contentValues) {
        HomePOI poi = new HomePOI();
        AttributeNormal attribute = new AttributeNormal();
        FloorName floorName = new FloorName();
        Position pos = new Position();
        Name name = new Name();

        if (contentValues.containsKey(DatabaseContract.POIColumns.POI_ID)) {
            poi.setPoiId(contentValues.getAsString(DatabaseContract.POIColumns.POI_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_CODE)) {
            poi.setFloorCode(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_CODE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_EN)) {
            floorName.setEn(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_EN));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_KR)) {
            floorName.setKr(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_KR));
        }
        poi.setFloorName(floorName);

        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_INDEX)) {
            poi.setFloorIndex(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_INDEX));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_X)) {
            pos.setX(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_X));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_Y)) {
            pos.setY(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_Y));
        }
        poi.setPosition(pos);

        if (contentValues.containsKey(DatabaseContract.POIColumns.NAME_KR)) {
            name.setKr(contentValues.getAsString(DatabaseContract.POIColumns.NAME_KR));
        }
        poi.setName(name);

        if (contentValues.containsKey(DatabaseContract.POIColumns.RADIUS)) {
            poi.setRadius(contentValues.getAsInteger(DatabaseContract.POIColumns.RADIUS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.TYPE)) {
            poi.setType(contentValues.getAsInteger(DatabaseContract.POIColumns.TYPE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.IS_RESTRICTED)) {
            poi.setRestricted(contentValues.getAsInteger(DatabaseContract.POIColumns.IS_RESTRICTED));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.THETA)) {
            poi.setTheta(contentValues.getAsInteger(DatabaseContract.POIColumns.THETA));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.CURRENT_SET)) {
            poi.setCurrentHome(contentValues.getAsBoolean(DatabaseContract.POIColumns.CURRENT_SET));
        }

        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DESC)) {
            attribute.setDesc(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DESC));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_TEL)) {
            attribute.setTel(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_TEL));
        }
        poi.setAttribute(attribute);

        return poi;
    }

    public static ChargerPOI getChargerPOIfromContentValues(ContentValues contentValues) {
        ChargerPOI poi = new ChargerPOI();
        AttributeNormal attribute = new AttributeNormal();
        FloorName floorName = new FloorName();
        Position pos = new Position();
        Name name = new Name();

        if (contentValues.containsKey(DatabaseContract.POIColumns.POI_ID)) {
            poi.setPoiId(contentValues.getAsString(DatabaseContract.POIColumns.POI_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_CODE)) {
            poi.setFloorCode(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_CODE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_EN)) {
            floorName.setEn(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_EN));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_KR)) {
            floorName.setKr(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_KR));
        }
        poi.setFloorName(floorName);

        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_INDEX)) {
            poi.setFloorIndex(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_INDEX));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_X)) {
            pos.setX(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_X));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_Y)) {
            pos.setY(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_Y));
        }
        poi.setPosition(pos);

        if (contentValues.containsKey(DatabaseContract.POIColumns.NAME_KR)) {
            name.setKr(contentValues.getAsString(DatabaseContract.POIColumns.NAME_KR));
        }
        poi.setName(name);

        if (contentValues.containsKey(DatabaseContract.POIColumns.RADIUS)) {
            poi.setRadius(contentValues.getAsInteger(DatabaseContract.POIColumns.RADIUS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.TYPE)) {
            poi.setType(contentValues.getAsInteger(DatabaseContract.POIColumns.TYPE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.IS_RESTRICTED)) {
            poi.setRestricted(contentValues.getAsInteger(DatabaseContract.POIColumns.IS_RESTRICTED));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.THETA)) {
            poi.setTheta(contentValues.getAsInteger(DatabaseContract.POIColumns.THETA));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.CURRENT_SET)) {
            poi.setCurrentCharger(contentValues.getAsBoolean(DatabaseContract.POIColumns.CURRENT_SET));
        }

        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DESC)) {
            attribute.setDesc(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DESC));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_TEL)) {
            attribute.setTel(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_TEL));
        }
        poi.setAttribute(attribute);

        return poi;
    }

    public static ReturnTrayPOI getReturnTrayPOIfromContentValues(ContentValues contentValues) {
        ReturnTrayPOI poi = new ReturnTrayPOI();
        AttributeNormal attribute = new AttributeNormal();
        FloorName floorName = new FloorName();
        Position pos = new Position();
        Name name = new Name();

        if (contentValues.containsKey(DatabaseContract.POIColumns.POI_ID)) {
            poi.setPoiId(contentValues.getAsString(DatabaseContract.POIColumns.POI_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_CODE)) {
            poi.setFloorCode(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_CODE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_EN)) {
            floorName.setEn(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_EN));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_KR)) {
            floorName.setKr(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_KR));
        }
        poi.setFloorName(floorName);

        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_INDEX)) {
            poi.setFloorIndex(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_INDEX));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_X)) {
            pos.setX(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_X));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_Y)) {
            pos.setY(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_Y));
        }
        poi.setPosition(pos);

        if (contentValues.containsKey(DatabaseContract.POIColumns.NAME_KR)) {
            name.setKr(contentValues.getAsString(DatabaseContract.POIColumns.NAME_KR));
        }
        poi.setName(name);

        if (contentValues.containsKey(DatabaseContract.POIColumns.RADIUS)) {
            poi.setRadius(contentValues.getAsInteger(DatabaseContract.POIColumns.RADIUS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.TYPE)) {
            poi.setType(contentValues.getAsInteger(DatabaseContract.POIColumns.TYPE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.IS_RESTRICTED)) {
            poi.setRestricted(contentValues.getAsInteger(DatabaseContract.POIColumns.IS_RESTRICTED));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.THETA)) {
            poi.setTheta(contentValues.getAsInteger(DatabaseContract.POIColumns.THETA));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.CURRENT_SET)) {
            poi.setCurrentReturnTray(contentValues.getAsBoolean(DatabaseContract.POIColumns.CURRENT_SET));
        }

        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DESC)) {
            attribute.setDesc(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DESC));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_TEL)) {
            attribute.setTel(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_TEL));
        }
        poi.setAttribute(attribute);

        return poi;
    }

    public static ELPOI getELPOIfromContentValues(ContentValues contentValues) {
        ELPOI poi = new ELPOI();
        AttributeEL attributeEL = new AttributeEL();
        FloorName floorName = new FloorName();
        Position pos = new Position();
        Name name = new Name();

        if (contentValues.containsKey(DatabaseContract.POIColumns.POI_ID)) {
            poi.setPoiId(contentValues.getAsString(DatabaseContract.POIColumns.POI_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_CODE)) {
            poi.setFloorCode(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_CODE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_EN)) {
            floorName.setEn(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_EN));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_KR)) {
            floorName.setKr(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_KR));
        }
        poi.setFloorName(floorName);

        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_INDEX)) {
            poi.setFloorIndex(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_INDEX));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_X)) {
            pos.setX(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_X));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_Y)) {
            pos.setY(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_Y));
        }
        poi.setPosition(pos);

        if (contentValues.containsKey(DatabaseContract.POIColumns.NAME_KR)) {
            name.setKr(contentValues.getAsString(DatabaseContract.POIColumns.NAME_KR));
        }
        poi.setName(name);

        if (contentValues.containsKey(DatabaseContract.POIColumns.RADIUS)) {
            poi.setRadius(contentValues.getAsInteger(DatabaseContract.POIColumns.RADIUS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.TYPE)) {
            poi.setType(contentValues.getAsInteger(DatabaseContract.POIColumns.TYPE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.IS_RESTRICTED)) {
            poi.setRestricted(contentValues.getAsInteger(DatabaseContract.POIColumns.IS_RESTRICTED));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.THETA)) {
            poi.setTheta(contentValues.getAsInteger(DatabaseContract.POIColumns.THETA));
        }

        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DESC)) {
            attributeEL.setDesc(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DESC));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_TEL)) {
            attributeEL.setTel(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_TEL));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_EL_ID)) {
            attributeEL.setElId(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_EL_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_EL_VENDOR)) {
            attributeEL.setVendor(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_EL_VENDOR));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_EL_FLOOR_LIST)) {
            attributeEL.setFloorList(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_EL_FLOOR_LIST));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DOOR)) {
            attributeEL.setDoor(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DOOR));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_IN_OUT)) {
            attributeEL.setInOut(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_IN_OUT));
        }
        poi.setAttribute(attributeEL);

        return poi;
    }

    public static DoorPOI getDoorPOIfromContentValues(ContentValues contentValues) {
        DoorPOI poi = new DoorPOI();
        AttributeDoor attributeDoor = new AttributeDoor();
        FloorName floorName = new FloorName();
        Position pos = new Position();
        Name name = new Name();

        if (contentValues.containsKey(DatabaseContract.POIColumns.POI_ID)) {
            poi.setPoiId(contentValues.getAsString(DatabaseContract.POIColumns.POI_ID));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_CODE)) {
            poi.setFloorCode(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_CODE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_EN)) {
            floorName.setEn(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_EN));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_NAME_KR)) {
            floorName.setKr(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_NAME_KR));
        }
        poi.setFloorName(floorName);

        if (contentValues.containsKey(DatabaseContract.POIColumns.FLOOR_INDEX)) {
            poi.setFloorIndex(contentValues.getAsString(DatabaseContract.POIColumns.FLOOR_INDEX));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_X)) {
            pos.setX(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_X));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.POSITION_Y)) {
            pos.setY(contentValues.getAsFloat(DatabaseContract.POIColumns.POSITION_Y));
        }
        poi.setPosition(pos);

        if (contentValues.containsKey(DatabaseContract.POIColumns.NAME_KR)) {
            name.setKr(contentValues.getAsString(DatabaseContract.POIColumns.NAME_KR));
        }
        poi.setName(name);

        if (contentValues.containsKey(DatabaseContract.POIColumns.RADIUS)) {
            poi.setRadius(contentValues.getAsInteger(DatabaseContract.POIColumns.RADIUS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.TYPE)) {
            poi.setType(contentValues.getAsInteger(DatabaseContract.POIColumns.TYPE));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.IS_RESTRICTED)) {
            poi.setRestricted(contentValues.getAsInteger(DatabaseContract.POIColumns.IS_RESTRICTED));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.THETA)) {
            poi.setTheta(contentValues.getAsInteger(DatabaseContract.POIColumns.THETA));
        }

        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_DESC)) {
            attributeDoor.setDesc(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_DESC));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_TEL)) {
            attributeDoor.setTel(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_TEL));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_MAC_ADDRESS)) {
            attributeDoor.setMacAddress(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_MAC_ADDRESS));
        }
        if (contentValues.containsKey(DatabaseContract.POIColumns.ATTRIBUTE_COMPANY_NAME)) {
            attributeDoor.setCompanyName(contentValues.getAsString(DatabaseContract.POIColumns.ATTRIBUTE_COMPANY_NAME));
        }
        poi.setAttribute(attributeDoor);

        return poi;
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
