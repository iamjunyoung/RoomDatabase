package app.example.roomdatabase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class Position implements Serializable {
    @SerializedName("el_id") public float x;
    @SerializedName("el_vendor") public float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
