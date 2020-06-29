package app.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ELPOIDao {
    @Insert
    long insert(ELPOI homePOI);

    @Update
    int update(ELPOI homePOI);

    @Query("DELETE FROM el_table")
    int deleteAll();

    @Query("DELETE FROM el_table WHERE id = :id")
    int deleteUser(int id);

    @Query("SELECT * from el_table ORDER BY floorIndex ASC")
    LiveData<List<ELPOI>> getAllPOIs();

}
