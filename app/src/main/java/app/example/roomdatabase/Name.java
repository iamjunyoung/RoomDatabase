package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Name implements Serializable {
    @SerializedName("kr") public String kr;

    public String getKr() {
        return kr;
    }

    public void setKr(String kr) {
        this.kr = kr;
    }
}
