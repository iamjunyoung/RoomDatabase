package app.example.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, HomePOI.class, ChargerPOI.class, ReturnTrayPOI.class, ELPOI.class, POIListPOI.class, DoorPOI.class}, version = 3)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile UserRoomDatabase INSTANCE;

    static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserRoomDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
