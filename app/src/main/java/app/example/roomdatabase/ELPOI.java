package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "el_table")
class ELPOI extends POI {
    @Embedded
    @SerializedName("attribute") private AttributeEL attribute;

    public AttributeEL getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeEL attribute) {
        this.attribute = attribute;
    }


    @Override
    public String toString() {
        return "ELPOI{" +
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
