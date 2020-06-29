package app.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface POIListPOIDao {
    @Insert
    long insert(POIListPOI poiListPOI);

    @Update
    int update(POIListPOI poiListPOI);

    @Query("DELETE FROM poiList_table")
    int deleteAll();

    @Query("DELETE FROM poiList_table WHERE id = :id")
    int deleteUser(int id);

    @Query("SELECT * from poiList_table ORDER BY floorIndex ASC")
    LiveData<List<POIListPOI>> getAllPOIs();

}
