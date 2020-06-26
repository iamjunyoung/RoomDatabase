package app.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();

    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;

    UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user) {
        new AsyncTask<User, Void, Long>() {
            @Override
            protected Long doInBackground(User... users) {
                if (userDao == null)
                    return -1L;
                return userDao.insert(users[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(user);
    }

    public void update(User user) {
        new AsyncTask<User, Void, Integer>() {
            @Override
            protected Integer doInBackground(User... users) {
                if (userDao == null)
                    return -1;
                return userDao.update(users[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(user);
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (userDao == null)
                    return -1;
                return userDao.deleteAll();
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "deleteAll : " + integer);
            }
        }.execute();
    }

    public void deleteUser(int id) {
        new AsyncTask<Integer, Void, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                if (userDao == null)
                    return -1;
                return userDao.deleteUser(integers[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "deleteUser : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
