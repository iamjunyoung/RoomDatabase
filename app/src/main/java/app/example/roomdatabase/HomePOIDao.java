package app.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface HomePOIDao {
    @Insert
    long insert(HomePOI homePOI);

    @Update
    int update(HomePOI homePOI);

    @Query("DELETE FROM home_table")
    int deleteAll();

    @Query("DELETE FROM home_table WHERE id = :id")
    int deletePOI(int id);

    @Query("SELECT * from home_table ORDER BY floorIndex ASC")
    LiveData<List<HomePOI>> getAllPOIs();

}
