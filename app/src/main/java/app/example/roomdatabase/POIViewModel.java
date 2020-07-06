package app.example.roomdatabase;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class POIViewModel extends AndroidViewModel {
    private static final String TAG = POIViewModel.class.getSimpleName();

    private final UserRepository repository;
    private final HomePOIRepository homePOIRepository;
    private final ChargerPOIRepository chargerPOIRepository;
    private final ReturnTrayPOIRepository returnTrayPOIRepository;
    private final POIListPOIRepository poiListPOIRepository;
    private final ELPOIRepository elPOIRepository;
    private final DoorPOIRepository doorPOIRepository;

    private final LiveData<List<User>> allUsers;
    private final LiveData<List<HomePOI>> allHomePOIs;
    private final LiveData<List<ChargerPOI>> allChargerPOIs;
    private final LiveData<List<ReturnTrayPOI>> allReturnTrayPOIs;
    private final LiveData<List<POIListPOI>> allPOIListPOIs;
    private final LiveData<List<ELPOI>> allELPOIs;
    private final LiveData<List<DoorPOI>> allDoorPOIs;

    public POIViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
        homePOIRepository = new HomePOIRepository(application);
        chargerPOIRepository = new ChargerPOIRepository(application);
        returnTrayPOIRepository = new ReturnTrayPOIRepository(application);
        poiListPOIRepository = new POIListPOIRepository(application);
        elPOIRepository = new ELPOIRepository(application);
        doorPOIRepository = new DoorPOIRepository(application);

        allUsers = repository.getAllUsers();
        allHomePOIs = homePOIRepository.getAllPOIs();
        allChargerPOIs = chargerPOIRepository.getAllPOIs();
        allReturnTrayPOIs = returnTrayPOIRepository.getAllPOIs();
        allPOIListPOIs = poiListPOIRepository.getAllPOIs();
        allELPOIs = elPOIRepository.getAllPOIs();
        allDoorPOIs = doorPOIRepository.getAllPOIs();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void insert(POI poi) {
        if (poi instanceof HomePOI) {
            homePOIRepository.insert((HomePOI) poi);
        } else if (poi instanceof ChargerPOI) {
            chargerPOIRepository.insert((ChargerPOI) poi);
        } else if (poi instanceof ReturnTrayPOI) {
            returnTrayPOIRepository.insert((ReturnTrayPOI) poi);
        } else if (poi instanceof POIListPOI) {
            poiListPOIRepository.insert((POIListPOI) poi);
        } else if (poi instanceof ELPOI) {
            Log.d("UserViewModel", "insert to EL Table " + poi);
            elPOIRepository.insert((ELPOI) poi);
        } else if (poi instanceof DoorPOI) {
            doorPOIRepository.insert((DoorPOI) poi);
        } else {

        }
    }

    public void update(User user) {
        repository.update(user);
    }

    public void update(POI poi) {
        if (poi instanceof HomePOI) {
            homePOIRepository.update((HomePOI) poi);
        } else if (poi instanceof ChargerPOI) {
            chargerPOIRepository.update((ChargerPOI) poi);
        } else if (poi instanceof ReturnTrayPOI) {
            returnTrayPOIRepository.update((ReturnTrayPOI) poi);
        } else if (poi instanceof POIListPOI) {
            poiListPOIRepository.update((POIListPOI) poi);
        } else if (poi instanceof ELPOI) {
            elPOIRepository.update((ELPOI) poi);
        } else if (poi instanceof DoorPOI) {
            doorPOIRepository.update((DoorPOI) poi);
        } else {

        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAllHomePOIs() {
        homePOIRepository.deleteAll();
    }
    public void deleteAllChargerPOIs() {
        chargerPOIRepository.deleteAll();
    }
    public void deleteAllReturnTrayPOIs() {
        returnTrayPOIRepository.deleteAll();
    }
    public void deleteAllPOIListPOIs() {
        poiListPOIRepository.deleteAll();
    }
    public void deleteAllELPOIs() {
        elPOIRepository.deleteAll();
    }
    public void deleteAllDoorPOIs() {
        doorPOIRepository.deleteAll();
    }

    public void deleteUser(int id) {
        repository.deleteUser(id);
    }

    public void deleteHomePOI(int id) {
        homePOIRepository.deletePOI(id);
    }

    public void deleteChargerPOI(int id) {
        chargerPOIRepository.deletePOI(id);
    }

    public void deleteReturnTrayPOI(int id) {
        returnTrayPOIRepository.deletePOI(id);
    }

    public void deletePOIListPOI(int id) {
        homePOIRepository.deletePOI(id);
    }

    public void deleteELPOI(int id) {
        homePOIRepository.deletePOI(id);
    }

    public void deleteDoorPOI(int id) {
        homePOIRepository.deletePOI(id);
    }


    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<HomePOI>> getAllHomePOIs() {
        return allHomePOIs;
    }

    public LiveData<List<ChargerPOI>> getAllChargerPOIs() {
        return allChargerPOIs;
    }

    public LiveData<List<ReturnTrayPOI>> getAllReturnTrayPOIs() {
        return allReturnTrayPOIs;
    }

    public LiveData<List<POIListPOI>> getAllPOIListPOIs() {
        return allPOIListPOIs;
    }

    public LiveData<List<ELPOI>> getAllELPOIs() {
        return allELPOIs;
    }

    public LiveData<List<DoorPOI>> getAllDoorPOIs() {
        return allDoorPOIs;
    }

}
