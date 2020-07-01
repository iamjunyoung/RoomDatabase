package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "door_table")
class DoorPOI extends POI {
    @Embedded
    @SerializedName("attribute") private AttributeDoor attribute;

    public AttributeDoor getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeDoor attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "DoorPOI{" +
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
}
