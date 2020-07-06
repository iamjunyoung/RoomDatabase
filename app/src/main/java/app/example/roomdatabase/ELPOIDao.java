package app.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ELPOIDao {
    @Insert
    long insert(ELPOI elPOI);

    @Insert
    long[] insertAll(ELPOI[] elpois);

    @Update
    int update(ELPOI elPOI);

    @Query("DELETE FROM el_table")
    int deleteAll();

    @Query("DELETE FROM el_table WHERE id = :id")
    int deletePOI(int id);

    @Query("SELECT * from el_table ORDER BY floorIndex ASC")
    LiveData<List<ELPOI>> getAllPOIs();

}
