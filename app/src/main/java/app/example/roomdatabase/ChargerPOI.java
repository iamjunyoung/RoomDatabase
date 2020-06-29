package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "charger_table")
class ChargerPOI extends POI {
    @SerializedName("current_charger") private Boolean currentCharger;

    @Embedded
    @SerializedName("attribute") private AttributeNormal attribute;

    public Boolean getCurrentCharger() {
        return currentCharger;
    }

    public void setCurrentCharger(Boolean currentCharger) {
        this.currentCharger = currentCharger;
    }

    public AttributeNormal getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeNormal attribute) {
        this.attribute = attribute;
    }
}
