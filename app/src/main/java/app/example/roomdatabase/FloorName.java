package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class FloorName implements Serializable {
    @SerializedName("floorName_en") public String floorEn;
    @SerializedName("floorName_kr") public String floorKr;

    public String getEn() {
        return floorEn;
    }

    public void setEn(String floorEn) {
        this.floorEn = floorEn;
    }

    public String getKr() {
        return floorKr;
    }

    public void setKr(String floorKr) {
        this.floorKr = floorKr;
    }
}
