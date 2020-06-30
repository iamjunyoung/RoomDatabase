package app.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChargerPOIDao {
    @Insert
    long insert(ChargerPOI chargerPOI);

    @Update
    int update(ChargerPOI chargerPOI);

    @Query("DELETE FROM charger_table")
    int deleteAll();

    @Query("DELETE FROM charger_table WHERE id = :id")
    int deletePOI(int id);

    @Query("SELECT * from charger_table ORDER BY floorIndex ASC")
    LiveData<List<ChargerPOI>> getAllPOIs();

}
