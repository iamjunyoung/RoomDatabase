package app.example.roomdatabase;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

import app.example.roomdatabase.provider.DatabaseContract;

@Entity(tableName = "poiList_table")
public
class POIListPOI extends POI {
    @Embedded
    @SerializedName("attribute") private AttributeNormal attribute;

    public AttributeNormal getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeNormal attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "POIListPOI{" +
                "id=" + id +
                ", poiId='" + poiId + '\'' +
                ", floorCode='" + floorCode + '\'' +
                ", floorName=" + floorName +
                ", floorIndex='" + floorIndex + '\'' +
                ", position=" + position +
                ", name=" + name +
                ", radius=" + radius +
                ", type=" + type +
                ", restricted=" + restricted +
                ", theta=" + theta +
                ", attribute=" + attribute +
                '}';
    }

    /*
        public static final String POI_ID = "poi_id";
        public static final String FLOOR_CODE = "floor_code";
        public static final String FLOOR_NAME_EN = "floor_name_en";
        public static final String FLOOR_NAME_KR = "floor_name_kr";
        public static final String FLOOR_INDEX = "floor_index";
        public static final String POSITION_X = "position_x";
        public static final String POSITION_Y = "position_y";
        public static final String NAME_EN = "name_en";
        public static final String NAME_KR = "name_kr";
        public static final String RADIUS = "radius";
        public static final String TYPE = "type";
        public static final String IS_RESTRICTED = "is_restricted";
        public static final String THETA = "theta";

        public static final String ATTRIBUTE_DESC = "attribute_desc";
        public static final String ATTRIBUTE_TEL = "attribute_tel";
     */

    /*
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.POIColumns.POI_ID, object.optString("poiId", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_CODE, object.optString("floorCode", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_NAME_EN, object.optJSONObject("floorName").optString("en", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_NAME_KR, object.optJSONObject("floorName").optString("kr", "N/A"));
        cv.put(DatabaseContract.POIColumns.FLOOR_INDEX, object.optString("floorIndex", "N/A"));
        cv.put(DatabaseContract.POIColumns.POSITION_X, object.optJSONObject("pos").optDouble("x", 0.0f));
        cv.put(DatabaseContract.POIColumns.POSITION_Y, object.optJSONObject("pos").optDouble("y", 0.0f));
        cv.put(DatabaseContract.POIColumns.NAME_EN, object.optJSONObject("name").optString("en", "N/A"));
        cv.put(DatabaseContract.POIColumns.NAME_KR, object.optJSONObject("name").optString("kr", "N/A"));
        cv.put(DatabaseContract.POIColumns.RADIUS, object.optDouble("radius", 0.0f));
        cv.put(DatabaseContract.POIColumns.TYPE, object.optInt("type", 0));
        cv.put(DatabaseContract.POIColumns.IS_RESTRICTED, object.optInt("isRestricted", 0));
        cv.put(DatabaseContract.POIColumns.THETA, object.optInt("theta", 0));

        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_DESC, object.optJSONObject("attributes").optString("desc", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_TEL, object.optJSONObject("attributes").optString("tel", "N/A"));

        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_EL_ID, object.optJSONObject("attributes").optString("elevatorID", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_EL_VENDOR, object.optJSONObject("attributes").optString("elevatorVendor", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_EL_FLOOR_LIST, object.optJSONObject("attributes").optString("elevatorFloorList", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_DOOR, object.optJSONObject("attributes").optString("door", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_IN_OUT, object.optJSONObject("attributes").optString("in/out", "N/A"));

        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_MAC_ADDRESS, object.optJSONObject("attributes").optString("macAddress", "N/A"));
        cv.put(DatabaseContract.POIColumns.ATTRIBUTE_COMPANY_NAME, object.optJSONObject("attributes").optString("companyName", "N/A"));

        cv.put(DatabaseContract.POIColumns.CANDIDATE_POI_ID, object.optString("candidatePoiId", "N/A"));
     */
}
