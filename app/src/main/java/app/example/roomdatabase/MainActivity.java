package app.example.roomdatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
                ListView listView = findViewById(R.id.listViewPOIList);
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
        ListView listView = findViewById(R.id.listViewPOIList);
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

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionAddHome:

                break;
            case R.id.actionAddCharger:
                break;
            case R.id.actionAddReturnTray:
                break;
            case R.id.actionAddPoiListPoi:
                break;
            case R.id.actionAddELPoi:
                Log.d("JYN", "getRandomString " + makeRandomString());
                showLoginDialog();
                break;
            case R.id.actionAddDoorPoi:
                break;
        }
    }

    private void showLoginDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.dialog_el, null);
        final EditText id = (EditText)loginLayout.findViewById(R.id.id);
        final EditText vendor = (EditText)loginLayout.findViewById(R.id.vendor);
        final EditText floorList = (EditText)loginLayout.findViewById(R.id.floorList);
        final EditText desc = (EditText)loginLayout.findViewById(R.id.desc);
        final EditText tel = (EditText)loginLayout.findViewById(R.id.tel);
        final EditText door = (EditText)loginLayout.findViewById(R.id.door);
        final EditText inout = (EditText)loginLayout.findViewById(R.id.inout);

        new AlertDialog.Builder(this).setTitle("Login").setView(loginLayout).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "ID : " + id.getText().toString() + "@nPW : " + vendor.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    public String makeRandomString() {
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        temp.insert(5, "-");
        temp.insert(11, "-");
        temp.insert(17, "-");

        return temp.toString();
    }

    public void inputEditText() {
        EditText inputName = findViewById(R.id.id);
        if (inputName.getText().toString().trim().length() == 0)
            return;
        EditText inputAge = findViewById(R.id.floorIndex);
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
}
