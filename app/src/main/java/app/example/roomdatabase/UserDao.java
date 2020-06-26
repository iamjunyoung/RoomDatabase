package app.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Update
    int update(User user);

    @Query("DELETE FROM user_table")
    int deleteAll();

    @Query("DELETE FROM user_table WHERE id = :id")
    int deleteUser(int id);

    @Query("SELECT * from user_table ORDER BY userName ASC")
    LiveData<List<User>> getAllUsers();

}
