package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttributeDoor extends Attribute implements Serializable {
    @SerializedName("mac_address") public String macAddress;
    @SerializedName("company_name") public String companyName;

}