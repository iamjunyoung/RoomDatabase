package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttributeEL extends Attribute implements Serializable {
    @SerializedName("el_id") public String elId;
    @SerializedName("el_vendor") public String vendor;
    @SerializedName("el_floor_list") public String floorList;
    @SerializedName("el_door") public String door;
    @SerializedName("el_in_out") public String inOut;

    public String getElId() {
        return elId;
    }

    public void setElId(String elId) {
        this.elId = elId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFloorList() {
        return floorList;
    }

    public void setFloorList(String floorList) {
        this.floorList = floorList;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    @Override
    public String toString() {
        return "AttributeEL{" +
                "elId='" + elId + '\'' +
                ", vendor='" + vendor + '\'' +
                ", floorList='" + floorList + '\'' +
                ", door='" + door + '\'' +
                ", inOut='" + inOut + '\'' +
                ", desc='" + desc + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
