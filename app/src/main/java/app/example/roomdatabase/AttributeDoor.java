package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttributeDoor extends Attribute implements Serializable {
    @SerializedName("mac_address") public String macAddress;
    @SerializedName("company_name") public String companyName;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "AttributeDoor{" +
                "macAddress='" + macAddress + '\'' +
                ", companyName='" + companyName + '\'' +
                ", desc='" + desc + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
