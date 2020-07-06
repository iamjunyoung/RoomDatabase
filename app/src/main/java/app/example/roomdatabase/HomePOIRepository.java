package app.example.roomdatabase;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class HomePOIRepository {
    private static final String TAG = HomePOIRepository.class.getSimpleName();

    private final HomePOIDao homePOIDao;
    private final LiveData<List<HomePOI>> allPOIs;

    public HomePOIRepository(Application application) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(application);
        homePOIDao = db.homePOIDao();
        allPOIs = homePOIDao.getAllPOIs();
    }

    public HomePOIRepository(Context context) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(context);
        homePOIDao = db.homePOIDao();
        allPOIs = homePOIDao.getAllPOIs();
    }

    public void insert(HomePOI HomePOI) {
        new AsyncTask<HomePOI, Void, Long>() {
            @Override
            protected Long doInBackground(HomePOI... HomePOIs) {
                if (homePOIDao == null)
                    return -1L;
                return homePOIDao.insert(HomePOIs[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(HomePOI);
    }

    public void update(HomePOI HomePOI) {
        new AsyncTask<HomePOI, Void, Integer>() {
            @Override
            protected Integer doInBackground(HomePOI... homePOIs) {
                if (homePOIDao == null)
                    return -1;
                return homePOIDao.update(homePOIs[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(HomePOI);
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (homePOIDao == null)
                    return -1;
                return homePOIDao.deleteAll();
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "deleteAll : " + integer);
            }
        }.execute();
    }

    public void deletePOI(int id) {
        new AsyncTask<Integer, Void, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                if (homePOIDao == null)
                    return -1;
                return homePOIDao.deletePOI(integers[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "deleteHomePOI : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<HomePOI>> getAllPOIs() {
        return allPOIs;
    }
}
