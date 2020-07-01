package app.example.roomdatabase;

import android.database.Cursor;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ReturnTrayPOIDao {
    @Insert
    long insert(ReturnTrayPOI returnTrayPOI);

    @Update
    int update(ReturnTrayPOI returnTrayPOI);

    @Query("DELETE FROM returnTray_table")
    int deleteAll();

    @Query("DELETE FROM returnTray_table WHERE id = :id")
    int deletePOI(int id);

    @Query("SELECT * from returnTray_table ORDER BY floorIndex ASC")
    LiveData<List<ReturnTrayPOI>> getAllPOIs();

    @Query("SELECT * from returnTray_table ORDER BY floorIndex ASC")
    Cursor getAllPOIsByCursor();

}
