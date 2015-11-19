package badge.system.pds.crmbadge;


import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
public class CallActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;

    BluetoothAdapter bluetoothAdapter;

    ArrayList<BluetoothDevice> pairedDeviceArrayList;

    TextView textInfo, textStatus;
    ListView listViewPairedDevice;
    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    private UUID myUUID;
    static BluetoothSocket connectedBluetoothSocket;

    LinearLayout inputPane;
    EditText inputField;
    Button btnSend;
    private static Context mContext;

    ThreadConnectBTdevice myThreadConnectBTdevice;
   // ThreadConnected myThreadConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext();

        setContentView(R.layout.activity_call);
        textInfo = (TextView) findViewById(R.id.info);
        textStatus = (TextView) findViewById(R.id.status);
        listViewPairedDevice = (ListView) findViewById(R.id.pairedlist);


        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
            Toast.makeText(this,
                    "Cet appareil n'a pas de BLUETOOTH",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        //generer UUID : http://www.famkruithof.net/uuid/uuidgen
        //doit etre identique dans les 2 app
        myUUID = UUID.fromString("ec79da00-853f-11e4-b4a9-0800200c9a66");

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this,
                    "Cet appareil n'a pas de BLUETOOTH",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String stInfo = bluetoothAdapter.getName() + "\n" +
                bluetoothAdapter.getAddress();
        textInfo.setText(stInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //activer Bluetoth si c'est pas fait
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        setup();
    }

    private void setup() {
        // recupere la liste de devices appairés
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
            }

            // creation de list view avec les devices trouvés
            pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this,
                    android.R.layout.simple_list_item_1, pairedDeviceArrayList);
            listViewPairedDevice.setAdapter(pairedDeviceAdapter);

            // On click listener pour la list
            listViewPairedDevice.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    BluetoothDevice device =
                            (BluetoothDevice) parent.getItemAtPosition(position);
                    Toast.makeText(CallActivity.this,
                            "Name: " + device.getName() + "\n"
                                    + "Address: " + device.getAddress() + "\n"
                                    + "BondState: " + device.getBondState() + "\n"
                                    + "BluetoothClass: " + device.getBluetoothClass() + "\n"
                                    + "Class: " + device.getClass(),
                            Toast.LENGTH_LONG).show();

                    textStatus.setText("ThreadConnectBTdevice est démarré");


                    //démarer le thread conexion bluetooth
                    myThreadConnectBTdevice = new ThreadConnectBTdevice(device, myUUID);
                    myThreadConnectBTdevice.start();


                }
            });
        }
    }

    //démarer call activity
    static void makeCall(String numTel) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + numTel));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(callIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myThreadConnectBTdevice != null) {
            myThreadConnectBTdevice.cancel();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                setup();
            } else {
                Toast.makeText(this,
                        "BLUETOOTH n'est pas activé",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quitter")
                .setMessage("Êtes-vous sûr de quitter?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }).setNegativeButton("Non", null).show();
    }







}