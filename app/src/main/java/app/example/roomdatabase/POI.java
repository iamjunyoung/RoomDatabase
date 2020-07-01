package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;

public class POI {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id") protected int id;

    @ColumnInfo(name = "poiId")
    @SerializedName("poiId") protected String poiId;

    @ColumnInfo(name = "floorCode")
    @SerializedName("floorCode") protected String floorCode;

    @Embedded
    @SerializedName("floorName") protected FloorName floorName;

    @ColumnInfo(name = "floorIndex")
    @SerializedName("floorIndex") protected String floorIndex;

    @Embedded
    @SerializedName("position") protected Position position;

    @Embedded
    @SerializedName("name") protected Name name;

    @ColumnInfo(name = "radius")
    @SerializedName("radius") protected int radius;

    @ColumnInfo(name = "type")
    @SerializedName("type") protected int type;

    @ColumnInfo(name = "restricted")
    @SerializedName("restricted") protected int restricted;

    @ColumnInfo(name = "theta")
    @SerializedName("theta") protected int theta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public FloorName getFloorName() {
        return floorName;
    }

    public void setFloorName(FloorName floorName) {
        this.floorName = floorName;
    }

    public String getFloorIndex() {
        return floorIndex;
    }

    public void setFloorIndex(String floorIndex) {
        this.floorIndex = floorIndex;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRestricted() {
        return restricted;
    }

    public void setRestricted(int restricted) {
        this.restricted = restricted;
    }

    public int getTheta() {
        return theta;
    }

    public void setTheta(int theta) {
        this.theta = theta;
    }

    /*
    @Override
    public String toString() {
        return "POI{" +
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
                '}';
    }
     */
}
