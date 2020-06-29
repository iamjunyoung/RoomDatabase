package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "home_table")
class HomePOI extends POI {
    @SerializedName("current_home") private Boolean currentHome;

    @Embedded
    @SerializedName("attribute") private AttributeNormal attribute;

    public Boolean getCurrentHome() {
        return currentHome;
    }

    public void setCurrentHome(Boolean currentHome) {
        this.currentHome = currentHome;
    }

    public AttributeNormal getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeNormal attribute) {
        this.attribute = attribute;
    }
}
