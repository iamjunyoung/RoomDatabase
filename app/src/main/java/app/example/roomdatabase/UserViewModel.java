package app.example.roomdatabase;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {
    private static final String TAG = UserViewModel.class.getSimpleName();

    private final UserRepository repository;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteUser(int id) {
        repository.deleteUser(id);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
