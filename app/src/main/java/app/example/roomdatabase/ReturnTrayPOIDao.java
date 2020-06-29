package app.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReturnTrayPOIDao {
    @Insert
    long insert(ReturnTrayPOI returnTrayPOI);

    @Update
    int update(ReturnTrayPOI returnTrayPOI);

    @Query("DELETE FROM returnTray_table")
    int deleteAll();

    @Query("DELETE FROM returnTray_table WHERE id = :id")
    int deleteUser(int id);

    @Query("SELECT * from returnTray_table ORDER BY floorIndex ASC")
    LiveData<List<ReturnTrayPOI>> getAllPOIs();

}
