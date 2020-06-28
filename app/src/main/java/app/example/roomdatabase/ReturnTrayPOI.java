package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "returnTray_table")
class ReturnTrayPOI extends POI {
    @Embedded
    @SerializedName("attribute") private AttributeNormal attribute;

    public AttributeNormal getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeNormal attribute) {
        this.attribute = attribute;
    }
}
