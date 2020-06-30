package app.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

class ReturnTrayPOIRepository {
    private static final String TAG = ReturnTrayPOIRepository.class.getSimpleName();

    private final ReturnTrayPOIDao poiDao;
    private final LiveData<List<ReturnTrayPOI>> allPOIs;

    ReturnTrayPOIRepository(Application application) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(application);
        poiDao = db.returnTrayPOIDao();
        allPOIs = poiDao.getAllPOIs();
    }

    public void insert(ReturnTrayPOI ReturnTrayPOI) {
        new AsyncTask<ReturnTrayPOI, Void, Long>() {
            @Override
            protected Long doInBackground(ReturnTrayPOI... ReturnTrayPOIs) {
                if (poiDao == null)
                    return -1L;
                return poiDao.insert(ReturnTrayPOIs[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(ReturnTrayPOI);
    }

    public void update(ReturnTrayPOI ReturnTrayPOI) {
        new AsyncTask<ReturnTrayPOI, Void, Integer>() {
            @Override
            protected Integer doInBackground(ReturnTrayPOI... ReturnTrayPOIs) {
                if (poiDao == null)
                    return -1;
                return poiDao.update(ReturnTrayPOIs[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(ReturnTrayPOI);
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
                Log.d(TAG, "deleteReturnTrayPOI : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<ReturnTrayPOI>> getAllPOIs() {
        return allPOIs;
    }
}
