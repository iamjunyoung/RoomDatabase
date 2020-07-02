package app.example.roomdatabase.provider;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class InsertAllToDBService extends IntentService {
    static final String ACTION_GENERATE_DB = "ACTION_GENERATE_DB";
    private static final String TAG = "[RP][POI][DB]";
    private String dec_poiSourceFolderPath =  "/sdcard/porterbot/MAP_LGIDM/";
    private boolean byMapDone = false;

    public InsertAllToDBService() {
        super("InsertAllToDBService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            generatePoi();
            if ("AGENT_EVENT_RETRIEVE_RESOURCE_MAP_DONE".equals(intent.getStringExtra("event"))) {
                byMapDone = true;
                Log.d(TAG, "[InsertAllToDBService] generatePoi. POI generating by AGENT_EVENT_RETRIEVE_RESOURCE_MAP_DONE async done.");
            } else {
                Log.d(TAG, "[InsertAllToDBService] generatePoi. POI generating async done.");
            }
        } else {
            Log.d(TAG, "[InsertAllToDBService] skip generatePoi. intent null");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (byMapDone) {
            //sendEvent(MessageType.POI_DB_GENERATED_AFTER_MAP_DONE);
        } else {
            //sendEvent(MessageType.POI_DB_GENERATED);
        }
        Log.d(TAG, "[InsertAllToDBService] Done. onDestroy this Service.");
    }

    private void sendEvent(int messageType) {
        Intent intent = new Intent();
        //intent.setAction(RobotPlatformService.BR_ACTION);
        //intent.putExtra(RobotPlatformService.TYPE, messageType);
        Log.d(TAG, "[sendEvent] messageType : " + messageType);
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public List<String> getPOIFileNameFromDir() {
        File directory = new File(dec_poiSourceFolderPath);
        File[] files = directory.listFiles();
        List<String> filesNameList = null;

        if (files != null) {
            filesNameList = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                Log.d(TAG, "get file " + i + " : " + files[i].getName());
                int pos = files[i].getName().lastIndexOf(".");
                String ext = files[i].getName().substring(pos + 1);
                if ("poi".equals(ext)) {
                    Log.d(TAG, "only get poi file " + i + " : " + files[i].getName());
                    filesNameList.add(files[i].getName());
                }
            }
        } else {
            Log.d(TAG, "getPOIFileNameFromDir() file not exist");
        }
        return filesNameList;
    }


    public String getFloorIndexFromFloor(String fileNameComp) {
        // ex) B2 -> -2
        String result;
        if ("B3".equals(fileNameComp)) {
            result = "-3.0";
        } else if ("B2".equals(fileNameComp)) {
            result = "-2.0";
        } else if ("B1".equals(fileNameComp)) {
            result = "-1.0";
        } else if ("1F".equals(fileNameComp) || "F1".equals(fileNameComp)) {
            result = "1.0";
        } else if ("2F".equals(fileNameComp) || "F2".equals(fileNameComp)) {
            result = "2.0";
        } else if ("3F".equals(fileNameComp) || "F3".equals(fileNameComp)) {
            result = "3.0";
        } else if ("4F".equals(fileNameComp) || "F4".equals(fileNameComp)) {
            result = "4.0";
        } else if ("5F".equals(fileNameComp) || "F5".equals(fileNameComp)) {
            result = "5.0";
        } else if ("6F".equals(fileNameComp) || "F6".equals(fileNameComp)) {
            result = "6.0";
        } else if ("7F".equals(fileNameComp) || "F7".equals(fileNameComp)) {
            result = "7.0";
        } else if ("8F".equals(fileNameComp) || "F8".equals(fileNameComp)) {
            result = "8.0";
        } else if ("9F".equals(fileNameComp) || "F9".equals(fileNameComp)) {
            result = "9.0";
        } else if ("10F".equals(fileNameComp) || "F10".equals(fileNameComp)) {
            result = "10.0";
        } else {
            result = "";
        }
        return  result;
    }

    public String getFloorFromFileName(String fileName) {
        // input : SNUH_DH_B2
        String[] fileNameComp = fileName.split("_");
        String onlyFloor = null;
        if (fileNameComp[2] != null) {
            onlyFloor = fileNameComp[2].replace(".poi", "");
        }
        // output : B2
        return onlyFloor;
    }

    public void generatePoi() {
        List<String> filesNameList = getPOIFileNameFromDir();
        JSONUtil generatePoi = new JSONUtil(this);
        if (filesNameList != null) {
            for (int i = 0 ; i < filesNameList.size() ; i++) {
                JSONArray array = generatePoi.jsonParsing(generatePoi.getJsonFromStorage(filesNameList.get(i)));

                if (array != null) {
                    String onlyFloor = getFloorFromFileName(filesNameList.get(i));
                    Log.d(TAG, "Input floor for update this floor's POI DB : " + onlyFloor);
                    updatePOIDatabase(getFloorIndexFromFloor(onlyFloor), array);
                } else {
                    Log.d(TAG, "POI Json array is null, check poi json file again");
                }
            }
        }
    }

    public void updatePOIDatabase(String floor, JSONArray array) {
        // db에서 해당 floor 전부 delete
        // 이후 파싱한 jsonarray로 해당 층에 대해서 전부 insert
        //SQLiteDatabase db = POIListDBHelper.getInstance(this).getWritableDatabase();
        Log.d(TAG, "updatePOIDatabase floor : " + floor);
        //int count = db.delete(DatabaseContract.TABLE_POI_LIST,  DatabaseContract.POIColumns.FLOOR_INDEX + "= '" + floor + "';", null);
        //Log.d(TAG, "drop POIList Table() row count (POI list table : " + count + ") at floor " + floor);

        generatePOIDatabase(array);
    }
    public void generatePOIDatabase(JSONArray array) {
        //SQLiteDatabase db = POIListDBHelper.getInstance(this).getWritableDatabase();
        ContentValues cv = null;
        for (int i = 0 ; i < array.length() ; i++) {
            Log.d(TAG, "generatePOIDatabaseBulk object to db " + i + " " + array.optJSONObject(i));
            cv = getContentValuesForJson(array.optJSONObject(i));
            //db.insert(DatabaseContract.TABLE_POI_LIST, null, cv);
        }
        Log.d(TAG, "generatePOIDatabaseBulk object to db Done. count " + array.length());

        //this.getContentResolver().notifyChange(POI_LIST_CONTENT_URI, null);
    }

    public ContentValues getContentValuesForJson(JSONObject object) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.POIColumns.POI_ID, object.optString("poiId", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_CODE, object.optString("floorCode", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_NAME_EN, object.optJSONObject("floorName").optString("en", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_NAME_KR, object.optJSONObject("floorName").optString("kr", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_INDEX, object.optString("floorIndex", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_EL_ID, object.optJSONObject("attributes").optString("elevatorID", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_EL_VENDOR, object.optJSONObject("attributes").optString("elevatorVendor", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_EL_FLOOR_LIST, object.optJSONObject("attributes").optString("elevatorFloorList", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_DESC, object.optJSONObject("attributes").optString("desc", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_TEL, object.optJSONObject("attributes").optString("tel", "N/A"));
        cv.put(DatabaseContract.POIColumns.RADIUS, object.optDouble("radius", 0.0f));
        cv.put(DatabaseContract.POIColumns.IS_RESTRICTED, object.optInt("isRestricted", 0));
        cv.put(DatabaseContract.POIColumns.NAME_EN, object.optJSONObject("name").optString("en", "N/A"));
        cv.put(DatabaseContract.POIColumns.NAME_KR, object.optJSONObject("name").optString("kr", "N/A"));
        cv.put(DatabaseContract.POIColumns.POSITION_X, object.optJSONObject("pos").optDouble("x", 0.0f));
        cv.put(DatabaseContract.POIColumns.POSITION_Y, object.optJSONObject("pos").optDouble("y", 0.0f));
        cv.put(DatabaseContract.POIColumns.THETA, object.optInt("theta", 0));
        cv.put(DatabaseContract.POIColumns.TYPE, object.optInt("type", 0));
        cv.put(DatabaseContract.POIColumns.CANDIDATE_POI_ID, object.optString("candidatePoiId", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_DOOR, object.optJSONObject("attributes").optString("door", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_IN_OUT, object.optJSONObject("attributes").optString("in/out", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_MAC_ADDRESS, object.optJSONObject("attributes").optString("macAddress", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_COMPANY_NAME, object.optJSONObject("attributes").optString("companyName", "N/A"));
        return cv;
    }
}
