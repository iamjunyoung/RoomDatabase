package app.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DoorPOIDao {
    @Insert
    long insert(DoorPOI doorPOI);

    @Insert
    long[] insertAll(DoorPOI[] doorPOIs);

    @Update
    int update(DoorPOI doorPOI);

    @Query("DELETE FROM door_table")
    int deleteAll();

    @Query("DELETE FROM door_table WHERE id = :id")
    int deletePOI(int id);

    @Query("SELECT * from door_table ORDER BY floorIndex ASC")
    LiveData<List<DoorPOI>> getAllPOIs();

}
