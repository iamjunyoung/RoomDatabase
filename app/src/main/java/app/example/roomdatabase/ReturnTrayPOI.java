package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "returnTray_table")
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
}
