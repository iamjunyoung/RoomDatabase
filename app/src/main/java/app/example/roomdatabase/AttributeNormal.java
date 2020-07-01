package app.example.roomdatabase;

import java.io.Serializable;

public class AttributeNormal extends Attribute implements Serializable {

    @Override
    public String toString() {
        return "AttributeNormal{" +
                "desc='" + desc + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
