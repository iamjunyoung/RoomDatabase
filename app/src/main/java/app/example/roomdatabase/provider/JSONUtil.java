package app.example.roomdatabase.provider;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class JSONUtil {
    public Context context = null;
    public String TAG = "JSONUTIL";
    private String dec_poiSourceFolderPath =  "/sdcard/porterbot/MAP_LGIDM/";

    public JSONUtil(Context context) {
        this.context = context;
    }

    public JSONArray jsonParsing(String json) {
        JSONArray resultArray = null;

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONObject newObject = null;
            resultArray = new JSONArray();

            String buildingIndex = jsonObject.getString("buildingIndex");
            String floorIndex = jsonObject.getString("floorIndex");
            JSONObject customPointData = jsonObject.getJSONObject("customPointData");

            Iterator i = customPointData.keys();
            while (i.hasNext()) {
                String cpId = i.next().toString(); //"01cd7491-68f6-11ea-82bf-057c1b8569a3"

                JSONObject customPointDataValue = customPointData.getJSONObject(cpId);

                newObject = new JSONObject();
                // TODO. buildingCode 추가
                newObject.put("poiId", customPointDataValue.optString("cpId", "N/A"));
                newObject.put("candidatePoiId", "N/A");
                newObject.put("floorCode", customPointDataValue.optString("floorCode", "N/A")); //defence
                newObject.put("floorIndex", customPointDataValue.optString("floorIndex", "N/A"));

                JSONObject floorName = customPointDataValue.optJSONObject("floorName");
                if (floorName == null) {
                    floorName = new JSONObject();
                }
                newObject.put("floorName", floorName);

                JSONObject attributes = customPointDataValue.optJSONObject("attributes");
                if (attributes == null) {
                    attributes = new JSONObject();
                }
                newObject.put("attributes", attributes);

                newObject.put("radius", customPointDataValue.optDouble("radius", 0.0f));
                newObject.put("isRestricted", customPointDataValue.optInt("isRestricted", 0));

                JSONObject name = customPointDataValue.optJSONObject("name");
                if  (name == null) {
                    name = new JSONObject();
                }
                newObject.put("name", name);

                JSONObject pos = customPointDataValue.optJSONObject("pos");
                if (pos == null) {
                    pos = new JSONObject();
                }
                newObject.put("pos", pos);

                newObject.put("theta", customPointDataValue.optInt("theta", 0));
                newObject.put("type", customPointDataValue.optInt("type", 0));

                Log.d(TAG, "transfer " + newObject);
                resultArray.put(newObject);
            }
            Log.d(TAG, "To JSONArray " + resultArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultArray;
    }

    // Parsing all file to json.
    public String getJsonFromStorage() {
        String json = "";
        try {
            //File poiFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "poi_object_sample_data.json");
            File poiFile = new File(dec_poiSourceFolderPath, "LGSP_W2_1F.poi");
            if (poiFile.exists()) {
                InputStream is = null;
                try {
                    is = new FileInputStream(poiFile);
                    int fileSize = is.available();

                    byte[] buffer = new byte[fileSize];
                    if (is.read(buffer) > 0) {
                        json = new String(buffer, "UTF-8");
                        Log.d(TAG, "getJsonFromStorage Success from poi_object_sample_data.json in DIRECTORY_DOWNLOADS");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            } else {
                Log.d(TAG, "getJsonFromStorage Fail from poi_object_sample_data.json in DIRECTORY_DOWNLOADS. There is no such file");
                return getJsonString();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Log.d(TAG, "getJsonFromStorage : " + json);
        return json;
    }

    public String getJsonFromStorage(String fileName){
        String json = "";
        try {
            File poiFile = new File(dec_poiSourceFolderPath, fileName);
            if (poiFile.exists()) {
                InputStream is = null;
                try {
                    is = new FileInputStream(poiFile);
                    int fileSize = is.available();

                    byte[] buffer = new byte[fileSize];
                    if (is.read(buffer) > 0) {
                        json = new String(buffer, "UTF-8");
                        Log.d(TAG, "getJsonFromStorage from " + fileName + " Success");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            } else {
                Log.d(TAG, "getJsonFromStorage from " + fileName + " Fail. There is some problem");
                return getJsonString();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Log.d(TAG, "getJsonFromStorage from " + fileName + " : " + json);
        return json;
    }

    //아예 삭제?
    public String getJsonString() {
        String json = "";
        InputStream is = null;
        try {
            is = context.getAssets().open("poi_object_sample_data.json");
            int fileSize = is.available();
            byte[] buffer = new byte[fileSize];
            if (is.read(buffer) > 0) {
                json = new String(buffer, "UTF-8");
            }
            Log.d(TAG, "getJsonString Success from poi_object_sample_data.json in assets (RobotPlatformLibrary)");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "getJsonString : " + json);
        return json;
    }
}
