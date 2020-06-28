package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttributeEL extends Attribute implements Serializable {
    @SerializedName("el_id") public String elId;
    @SerializedName("el_vendor") public String elVendor;
    @SerializedName("el_floor_list") public String elFloorList;
}
