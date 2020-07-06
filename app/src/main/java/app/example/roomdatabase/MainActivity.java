package app.example.roomdatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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

    private static final String TAG = "[POIRoomDataBase][MainActivity]";
    private POIViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(POIViewModel.class);
        viewModel.getAllHomePOIs().observe(this, new Observer<List<HomePOI>>() {
            @Override
            public void onChanged(List<HomePOI> users) {
                ListView listView = findViewById(R.id.listViewHome);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<HomePOI> arrayAdapter = (ArrayAdapter<HomePOI>) adapter;
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

        viewModel.getAllChargerPOIs().observe(this, new Observer<List<ChargerPOI>>() {
            @Override
            public void onChanged(List<ChargerPOI> users) {
                ListView listView = findViewById(R.id.listViewCharger);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<ChargerPOI> arrayAdapter = (ArrayAdapter<ChargerPOI>) adapter;
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

        viewModel.getAllReturnTrayPOIs().observe(this, new Observer<List<ReturnTrayPOI>>() {
            @Override
            public void onChanged(List<ReturnTrayPOI> users) {
                ListView listView = findViewById(R.id.listViewReturnTray);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<ReturnTrayPOI> arrayAdapter = (ArrayAdapter<ReturnTrayPOI>) adapter;
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

        //viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
        viewModel.getAllPOIListPOIs().observe(this, new Observer<List<POIListPOI>>() {
            @Override
            public void onChanged(List<POIListPOI> users) {
                ListView listView = findViewById(R.id.listViewPOIList);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<POIListPOI> arrayAdapter = (ArrayAdapter<POIListPOI>) adapter;
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

        viewModel.getAllELPOIs().observe(this, new Observer<List<ELPOI>>() {
            @Override
            public void onChanged(List<ELPOI> users) {
                ListView listView = findViewById(R.id.listViewEl);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<ELPOI> arrayAdapter = (ArrayAdapter<ELPOI>) adapter;
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

        viewModel.getAllDoorPOIs().observe(this, new Observer<List<DoorPOI>>() {
            @Override
            public void onChanged(List<DoorPOI> users) {
                ListView listView = findViewById(R.id.listViewDoor);
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof ArrayAdapter) {
                    ArrayAdapter<DoorPOI> arrayAdapter = (ArrayAdapter<DoorPOI>) adapter;
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
        ListView listViewHome = findViewById(R.id.listViewHome);
        listViewHome.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    viewModel.deleteHomePOI(((HomePOI) item).getId());
                    return true;
                }
                return false;
            }
        });

        ListView listViewCharger = findViewById(R.id.listViewCharger);
        listViewCharger.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    viewModel.deleteChargerPOI(((ChargerPOI) item).getId());
                    return true;
                }
                return false;
            }
        });

        ListView listViewReturnTray = findViewById(R.id.listViewReturnTray);
        listViewReturnTray.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    viewModel.deleteReturnTrayPOI(((ReturnTrayPOI) item).getId());
                    return true;
                }
                return false;
            }
        });

        ListView listViewPOIList = findViewById(R.id.listViewPOIList);
        listViewPOIList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    viewModel.deletePOIListPOI(((POIListPOI) item).getId());
                    return true;
                }
                return false;
            }
        });

        ListView listViewEL = findViewById(R.id.listViewEl);
        listViewEL.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    viewModel.deleteELPOI(((ELPOI) item).getId());
                    return true;
                }
                return false;
            }
        });

        ListView listViewDoor = findViewById(R.id.listViewDoor);
        listViewDoor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof User) {
                    viewModel.deleteDoorPOI(((HomePOI) item).getId());
                    return true;
                }
                return false;
            }
        });


    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionAddHome:
                showHomeChargerReturnTrayDialog();
                break;
            case R.id.actionAddCharger:
                showHomeChargerReturnTrayDialog();
                break;
            case R.id.actionAddReturnTray:
                showHomeChargerReturnTrayDialog();
                break;
            case R.id.actionAddPoiListPoi:
                break;
            case R.id.actionAddELPoi:
                Log.d("JYN", "getRandomString " + makeRandomString());
                showELDialog();
                break;
            case R.id.actionAddDoorPoi:
                showDoorDialog();
                break;
        }
    }

    private Attribute showHomeChargerReturnTrayDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.dialog_home_charger_return, null);
        final CheckBox currentSet = (CheckBox)loginLayout.findViewById(R.id.check1);

        new AlertDialog.Builder(this).setTitle("Login").setView(loginLayout).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "currentSet : " + currentSet.isChecked(), Toast.LENGTH_LONG).show();
            }
        }).show();

        //TODO.
        return null;
    }

    private Attribute showELDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.dialog_el, null);
        final EditText id = (EditText)loginLayout.findViewById(R.id.id);
        final EditText vendor = (EditText)loginLayout.findViewById(R.id.vendor);
        final EditText floorList = (EditText)loginLayout.findViewById(R.id.floorList);
        final EditText door = (EditText)loginLayout.findViewById(R.id.door);
        final EditText inout = (EditText)loginLayout.findViewById(R.id.inout);

        new AlertDialog.Builder(this).setTitle("Login").setView(loginLayout).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                //MainActivity에 있는 editText의 값들을 전부 읽어감
                //Dialog에 있는 editText의 값들을 전부 읽어감.
                ELPOI poi = (ELPOI) getInputEditTexts("el");

                String desc, tel;
                EditText inputDesc = findViewById(R.id.desc);
                if (inputDesc.getText().toString().trim().length() == 0) {
                    desc = "N/A";
                } else {
                    desc = inputDesc.getText().toString();
                }

                EditText inputTel = findViewById(R.id.tel);
                if (inputTel.getText().toString().trim().length() == 0) {
                    tel = "010-0000-0000";
                } else {
                    tel = inputTel.getText().toString();
                }

                AttributeEL elAttribute = new AttributeEL();
                elAttribute.setDesc(desc);
                elAttribute.setTel(tel);
                elAttribute.setElId(id.getText().toString());
                elAttribute.setVendor(vendor.getText().toString());
                elAttribute.setFloorList(floorList.getText().toString());
                elAttribute.setDoor(door.getText().toString());
                elAttribute.setInOut(inout.getText().toString());
                poi.setAttribute(elAttribute);
                viewModel.insert(poi);
                Toast.makeText(MainActivity.this,
                        "EL ID : " + id.getText().toString()
                        + "\nEL Vendor : " + vendor.getText().toString()
                        + "\nEL floorList : " + floorList.getText().toString()
                        + "\ndoor : " + door.getText().toString()
                        + "\ninout : " + inout.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }).show();

        AttributeEL attributeEL = new AttributeEL();
        attributeEL.setElId(id.getText().toString());
        attributeEL.setVendor(vendor.getText().toString());
        attributeEL.setFloorList(floorList.getText().toString());
        attributeEL.setDoor(door.getText().toString());
        attributeEL.setInOut(inout.getText().toString());

        return attributeEL;
    }

    private void showDoorDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.dialog_door, null);
        final EditText macAddress = (EditText)loginLayout.findViewById(R.id.macAddress);
        final EditText companyName = (EditText)loginLayout.findViewById(R.id.companyName);
        DoorPOI poi = (DoorPOI) getInputEditTexts("door");

        String desc, tel;
        EditText inputDesc = findViewById(R.id.desc);
        if (inputDesc.getText().toString().trim().length() == 0) {
            desc = "N/A";
        } else {
            desc = inputDesc.getText().toString();
        }

        EditText inputTel = findViewById(R.id.tel);
        if (inputTel.getText().toString().trim().length() == 0) {
            tel = "010-0000-0000";
        } else {
            tel = inputTel.getText().toString();
        }

        AttributeDoor doorAttribute = new AttributeDoor();
        doorAttribute.setDesc(desc);
        doorAttribute.setTel(tel);
        doorAttribute.setMacAddress(macAddress.getText().toString());
        doorAttribute.setCompanyName(companyName.getText().toString());
        poi.setAttribute(doorAttribute);
        viewModel.insert(poi);
        new AlertDialog.Builder(this).setTitle("Login").setView(loginLayout).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,
                        "macAddress : " + macAddress.getText().toString()
                                + "companyName : " + companyName.getText().toString(), Toast.LENGTH_LONG).show();
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

    public POI getInputEditTexts(String tableType) {
        String poiId = "";
        String floorIndex = ""; // floorCode, floorName(en), floorName(kr)
        float positionX;
        float positionY;
        String nameKr;
        String nameEn;
        String desc;
        String tel;
        int radius;
        int type;
        int restricted;
        int theta;

        EditText inputId = findViewById(R.id.id);
        if (inputId.getText().toString().trim().length() == 0) {
            poiId = makeRandomString();
        } else {
            poiId = inputId.getText().toString();
        }

        EditText inputFloorIndex = findViewById(R.id.floorIndex);
        if (inputFloorIndex.getText().toString().trim().length() == 0) {
            floorIndex = "1.0";
        } else {
            floorIndex = inputFloorIndex.getText().toString();
        }

        EditText inputX = findViewById(R.id.positionX);
        if (inputX.getText().toString().trim().length() == 0) {
            positionX = 0.0f;
        } else {
            positionX = Float.parseFloat(inputX.getText().toString());
        }

        EditText inputY = findViewById(R.id.positionY);
        if (inputY.getText().toString().trim().length() == 0) {
            positionY = 0.0f;
        } else {
            positionY = Float.parseFloat(inputY.getText().toString());
        }


        EditText inputNameKr = findViewById(R.id.nameKr);
        if (inputNameKr.getText().toString().trim().length() == 0) {
            nameKr = "N/A";
        } else {
            nameKr = inputNameKr.getText().toString();
        }

        EditText inputNameEn = findViewById(R.id.nameEn);
        if (inputNameEn.getText().toString().trim().length() == 0) {
            nameEn = "N/A";
        } else {
            nameEn = inputNameEn.getText().toString();
        }


        EditText inputDesc = findViewById(R.id.desc);
        if (inputDesc.getText().toString().trim().length() == 0) {
            desc = "N/A";
        } else {
            desc = inputDesc.getText().toString();
        }

        EditText inputTel = findViewById(R.id.tel);
        if (inputTel.getText().toString().trim().length() == 0) {
            tel = "010-0000-0000";
        } else {
            tel = inputTel.getText().toString();
        }

        EditText inputRadius = findViewById(R.id.radius);
        if (inputRadius.getText().toString().trim().length() == 0) {
            radius = 0;
        } else {
            radius = Integer.parseInt(inputRadius.getText().toString());
        }

        EditText inputType = findViewById(R.id.type);
        if (inputType.getText().toString().trim().length() == 0) {
            type = 0;
        } else {
            type = Integer.parseInt(inputType.getText().toString());
        }

        EditText inputRestricted = findViewById(R.id.restricted);
        if (inputRestricted.getText().toString().trim().length() == 0) {
            restricted = 0;
        } else {
            restricted = Integer.parseInt(inputRestricted.getText().toString());
        }

        EditText inputTheta = findViewById(R.id.theta);
        if (inputTheta.getText().toString().trim().length() == 0) {
            theta = 0;
        } else {
            theta = Integer.parseInt(inputTheta.getText().toString());
        }

        POI poi = null;
        if ("home".equals(tableType)) {
            poi = new HomePOI();
        } else if ("charger".equals(tableType)) {
            poi = new ChargerPOI();
        } else if ("returnTray".equals(tableType)) {
            poi = new ReturnTrayPOI();
        } else if ("poiListPOI".equals(tableType)) {
            poi = new POIListPOI();
        } else if ("el".equals(tableType)) {
            poi = new ELPOI();
        } else if ("door".equals(tableType)) {
            poi = new DoorPOI();
        } else {
            poi = new POI();
        }

        poi.setPoiId(poiId);
        poi.setFloorCode(getFloorNameEnFromFloorIndex(floorIndex));

        FloorName floorName = new FloorName();
        floorName.setEn(getFloorNameEnFromFloorIndex(floorIndex));
        floorName.setKr(getFloorNameKrFromFloorIndex(floorIndex));
        poi.setFloorName(floorName);

        poi.setFloorIndex(floorIndex);

        Position pos = new Position();
        pos.setX(positionX);
        pos.setY(positionY);
        poi.setPosition(pos);

        Name name = new Name();
        name.setKr(nameKr);
        poi.setName(name);

        /*
        Attribute attribute = new Attribute();
        attribute.setDesc(desc);
        attribute.setTel(tel);
        */

        poi.setRadius(radius);
        poi.setType(type);
        poi.setRestricted(restricted);
        poi.setTheta(theta);

        return poi;
        /*
        try {

            User user = new User();
            user.setUserName(name);
            user.setAge(age);
            viewModel.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }

    public String getFloorNameEnFromFloorIndex(String floorIndex) { // FloorName = FloorCode
        String result;
        if ("-3.0".equals(floorIndex)) {
            result = "B3";
        } else if ("-2.0".equals(floorIndex)) {
            result = "B2";
        } else if ("-1.0".equals(floorIndex)) {
            result = "B1";
        } else if ("1.0".equals(floorIndex)) {
            result = "F1";
        } else if ("2.0".equals(floorIndex)) {
            result = "F2";
        } else if ("3.0".equals(floorIndex)) {
            result = "F3";
        } else if ("4.0".equals(floorIndex)) {
            result = "F4";
        } else if ("5.0".equals(floorIndex)) {
            result = "F5";
        } else if ("6.0".equals(floorIndex)) {
            result = "F6";
        } else if ("7.0".equals(floorIndex)) {
            result = "F7";
        } else if ("8.0".equals(floorIndex)) {
            result = "F8";
        } else {
            result = "";
        }
        return  result;
    }

    public String getFloorNameKrFromFloorIndex(String floorIndex) {
        String result;
        if ("-3.0".equals(floorIndex)) {
            result = "지하3층";
        } else if ("-2.0".equals(floorIndex)) {
            result = "지하2층";
        } else if ("-1.0".equals(floorIndex)) {
            result = "지하1층";
        } else if ("1.0".equals(floorIndex)) {
            result = "1층";
        } else if ("2.0".equals(floorIndex)) {
            result = "2층";
        } else if ("3.0".equals(floorIndex)) {
            result = "3층";
        } else if ("4.0".equals(floorIndex)) {
            result = "4층";
        } else if ("5.0".equals(floorIndex)) {
            result = "5층";
        } else if ("6.0".equals(floorIndex)) {
            result = "6층";
        } else if ("7.0".equals(floorIndex)) {
            result = "7층";
        } else if ("8.0".equals(floorIndex)) {
            result = "8층";
        } else {
            result = "";
        }
        return  result;
    }


    public String getFloorIndexFromFloor(String fileNameComp) {
        // ex) B2 -> -2
        String result;
        if ("B3".equals(fileNameComp)) {
            result = "-3.0";
        } else if ("B2".equals(fileNameComp)) {
            result = "-2.0";
        } else if ("B1".equals(fileNameComp)) {
            result = "-1.0";
        } else if ("1F".equals(fileNameComp) || "F1".equals(fileNameComp)) {
            result = "1.0";
        } else if ("2F".equals(fileNameComp) || "F2".equals(fileNameComp)) {
            result = "2.0";
        } else {
            result = "";
        }
        return  result;
    }
}
