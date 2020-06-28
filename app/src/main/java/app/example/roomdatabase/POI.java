package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;

public class POI {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id") private int id;

    @ColumnInfo(name = "poiId")
    @SerializedName("poiId") private String poiId;

    @ColumnInfo(name = "floorCode")
    @SerializedName("floorCode") private String floorCode;

    @Embedded
    @SerializedName("floorName") private FloorName floorName;

    @ColumnInfo(name = "floorIndex")
    @SerializedName("floorIndex") private float floorIndex;

    @Embedded
    @SerializedName("position") private Position position;

    @Embedded
    @SerializedName("name") private Name name;

    /*
    @Embedded
    @SerializedName("attribute") public Attribute attribute;
    */

    @ColumnInfo(name = "radius")
    @SerializedName("radius") private int radius;

    @ColumnInfo(name = "type")
    @SerializedName("type") private int type;

    @ColumnInfo(name = "restricted")
    @SerializedName("restricted") private int restricted;

    @ColumnInfo(name = "theta")
    @SerializedName("theta") private int theta;

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

    public float getFloorIndex() {
        return floorIndex;
    }

    public void setFloorIndex(float floorIndex) {
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

    /*
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }*/

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
}
