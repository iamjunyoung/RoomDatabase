package app.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DoorPOIRepository {
    private static final String TAG = DoorPOIRepository.class.getSimpleName();

    private final DoorPOIDao poiDao;
    private final LiveData<List<DoorPOI>> allPOIs;

    public DoorPOIRepository(Application application) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(application);
        poiDao = db.doorPOIDao();
        allPOIs = poiDao.getAllPOIs();
    }

    public void insert(DoorPOI DoorPOI) {
        new AsyncTask<DoorPOI, Void, Long>() {
            @Override
            protected Long doInBackground(DoorPOI... DoorPOIs) {
                if (poiDao == null)
                    return -1L;
                return poiDao.insert(DoorPOIs[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(DoorPOI);
    }

    public void update(DoorPOI DoorPOI) {
        new AsyncTask<DoorPOI, Void, Integer>() {
            @Override
            protected Integer doInBackground(DoorPOI... DoorPOIs) {
                if (poiDao == null)
                    return -1;
                return poiDao.update(DoorPOIs[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(DoorPOI);
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (poiDao == null)
                    return -1;
                return poiDao.deleteAll();
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
                if (poiDao == null)
                    return -1;
                return poiDao.deletePOI(integers[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "deleteDoorPOI : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<DoorPOI>> getAllPOIs() {
        return allPOIs;
    }
}
