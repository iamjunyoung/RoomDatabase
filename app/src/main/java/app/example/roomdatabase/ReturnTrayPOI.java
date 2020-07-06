package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "returnTray_table")
public
class ReturnTrayPOI extends POI {
    @SerializedName("current_return_tray") private Boolean currentReturnTray;

    @Embedded
    @SerializedName("attribute") private AttributeNormal attribute;

    public Boolean getCurrentReturnTray() {
        return currentReturnTray;
    }

    public void setCurrentReturnTray(Boolean currentReturnTray) {
        this.currentReturnTray = currentReturnTray;
    }

    public AttributeNormal getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeNormal attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "ReturnTrayPOI{" +
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
                ", currentReturnTray=" + currentReturnTray +
                ", attribute=" + attribute +
                '}';
    }
}
