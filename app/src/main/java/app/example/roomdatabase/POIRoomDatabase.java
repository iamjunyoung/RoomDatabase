package app.example.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, HomePOI.class, ChargerPOI.class, ReturnTrayPOI.class, ELPOI.class, POIListPOI.class, DoorPOI.class}, version = 4)
//@Database(entities = {HomePOI.class, ChargerPOI.class, ReturnTrayPOI.class, ELPOI.class, POIListPOI.class, DoorPOI.class}, version = 4)
public abstract class POIRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract HomePOIDao homePOIDao();
    public abstract ChargerPOIDao chargerPOIDao();
    public abstract ReturnTrayPOIDao returnTrayPOIDao();
    public abstract POIListPOIDao poiListPOIDao();
    public abstract ELPOIDao elPOIDao();
    public abstract DoorPOIDao doorPOIDao();

    private static volatile POIRoomDatabase INSTANCE;

    static POIRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (POIRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), POIRoomDatabase.class, "user_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
