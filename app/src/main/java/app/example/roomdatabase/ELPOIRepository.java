package app.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

class ELPOIRepository {
    private static final String TAG = ELPOIRepository.class.getSimpleName();

    private final ELPOIDao poiDao;
    private final LiveData<List<ELPOI>> allPOIs;

    ELPOIRepository(Application application) {
        POIRoomDatabase db = POIRoomDatabase.getDatabase(application);
        poiDao = db.elPOIDao();
        allPOIs = poiDao.getAllPOIs();
    }

    public void insert(ELPOI ELPOI) {
        new AsyncTask<ELPOI, Void, Long>() {
            @Override
            protected Long doInBackground(ELPOI... ELPOIs) {
                if (poiDao == null)
                    return -1L;
                return poiDao.insert(ELPOIs[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d(TAG, "insert : " + aLong);
            }
        }.execute(ELPOI);
    }

    public void update(ELPOI ELPOI) {
        new AsyncTask<ELPOI, Void, Integer>() {
            @Override
            protected Integer doInBackground(ELPOI... ELPOIs) {
                if (poiDao == null)
                    return -1;
                return poiDao.update(ELPOIs[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Log.d(TAG, "update : " + integer);
            }
        }.execute(ELPOI);
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
                Log.d(TAG, "deleteELPOI : " + integer);
            }
        }.execute(id);
    }

    public LiveData<List<ELPOI>> getAllPOIs() {
        return allPOIs;
    }
}
