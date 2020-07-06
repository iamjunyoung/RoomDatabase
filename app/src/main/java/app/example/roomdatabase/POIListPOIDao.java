package app.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface POIListPOIDao {
    @Insert
    long insert(POIListPOI poiListPOI);

    @Insert
    long[] insertAll(POIListPOI[] poiListPOIs);

    @Update
    int update(POIListPOI poiListPOI);

    @Query("DELETE FROM poiList_table")
    int deleteAll();

    @Query("DELETE FROM poiList_table WHERE id = :id")
    int deletePOI(int id);

    @Query("SELECT * from poiList_table ORDER BY floorIndex ASC")
    LiveData<List<POIListPOI>> getAllPOIs();

}
