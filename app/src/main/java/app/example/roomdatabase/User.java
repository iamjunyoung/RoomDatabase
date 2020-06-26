package app.example.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "userName")
    private String userName;

    @NonNull
    @ColumnInfo(name = "age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public int getAge() {
        return age;
    }

    public void setAge(@NonNull int age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%d. 이름 : %s, 나이 : %d", id, userName, age);
    }
}
