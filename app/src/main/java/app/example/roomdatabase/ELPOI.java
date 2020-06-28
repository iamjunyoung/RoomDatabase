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
}
