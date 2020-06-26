package app.example.roomdatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                ListView listView = findViewById(R.id.listView);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<User> arrayAdapter = (ArrayAdapter<User>) adapter;
                    arrayAdapter.clear();
                    if (users != null)
                        arrayAdapter.addAll(users);

                    arrayAdapter.notifyDataSetChanged();
                } else {
                    adapter = new ArrayAdapter<>(listView.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, users);
                    listView.setAdapter(adapter);
                }
            }
        });


        // Event
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    userViewModel.deleteUser(((User) item).getId());
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.actionAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputName = findViewById(R.id.inputName);
                if (inputName.getText().toString().trim().length() == 0)
                    return;

                EditText inputAge = findViewById(R.id.inputAge);
                if (inputAge.getText().toString().trim().length() == 0)
                    return;

                try {
                    int age = Integer.valueOf(inputAge.getText().toString().trim());
                    String name = inputName.getText().toString().trim();

                    User user = new User();
                    user.setUserName(name);
                    user.setAge(age);

                    userViewModel.insert(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
