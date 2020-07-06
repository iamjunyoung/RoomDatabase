package app.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class POIListPOIRepository {
    private static final String TAG = POIListPOIRepository.class.getSimpleName();

    private final POIListPOIDao poiDao;
    private final LiveData<List<POIListPOI>> allPOIs;

    public POIListPOIRepository(Application application) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(application);
        poiDao = db.poiListPOIDao();
        allPOIs = poiDao.getAllPOIs();
    }

    public void insert(POIListPOI POIListPOI) {
        new AsyncTask<POIListPOI, Void, Long>() {
            @Override
            protected Long doInBackground(POIListPOI... POIListPOIs) {
                if (poiDao == null)
                    return -1L;
                return poiDao.insert(POIListPOIs[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(POIListPOI);
    }

    public void update(POIListPOI POIListPOI) {
        new AsyncTask<POIListPOI, Void, Integer>() {
            @Override
            protected Integer doInBackground(POIListPOI... POIListPOIs) {
                if (poiDao == null)
                    return -1;
                return poiDao.update(POIListPOIs[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(POIListPOI);
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
                Log.d(TAG, "deletePOIListPOI : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<POIListPOI>> getAllPOIs() {
        return allPOIs;
    }
}
