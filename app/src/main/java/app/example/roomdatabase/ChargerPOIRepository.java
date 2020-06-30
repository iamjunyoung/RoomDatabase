package app.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

class ChargerPOIRepository {
    private static final String TAG = ChargerPOIRepository.class.getSimpleName();

    private final ChargerPOIDao poiDao;
    private final LiveData<List<ChargerPOI>> allPOIs;

    ChargerPOIRepository(Application application) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(application);
        poiDao = db.chargerPOIDao();
        allPOIs = poiDao.getAllPOIs();
    }

    public void insert(ChargerPOI ChargerPOI) {
        new AsyncTask<ChargerPOI, Void, Long>() {
            @Override
            protected Long doInBackground(ChargerPOI... ChargerPOIs) {
                if (poiDao == null)
                    return -1L;
                return poiDao.insert(ChargerPOIs[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(ChargerPOI);
    }

    public void update(ChargerPOI ChargerPOI) {
        new AsyncTask<ChargerPOI, Void, Integer>() {
            @Override
            protected Integer doInBackground(ChargerPOI... ChargerPOIs) {
                if (poiDao == null)
                    return -1;
                return poiDao.update(ChargerPOIs[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(ChargerPOI);
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
                Log.d(TAG, "deleteChargerPOI : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<ChargerPOI>> getAllPOIs() {
        return allPOIs;
    }
}
