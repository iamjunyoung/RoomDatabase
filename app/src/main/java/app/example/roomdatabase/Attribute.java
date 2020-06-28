package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attribute implements Serializable {
    @SerializedName("desc") public String desc;
    @SerializedName("tel") public String tel;
}
