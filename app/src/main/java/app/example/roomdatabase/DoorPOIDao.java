package app.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoorPOIDao {
    @Insert
    long insert(DoorPOI doorPOI);

    @Update
    int update(DoorPOI doorPOI);

    @Query("DELETE FROM door_table")
    int deleteAll();

    @Query("DELETE FROM door_table WHERE id = :id")
    int deleteUser(int id);

    @Query("SELECT * from door_table ORDER BY floorIndex ASC")
    LiveData<List<DoorPOI>> getAllPOIs();

}
