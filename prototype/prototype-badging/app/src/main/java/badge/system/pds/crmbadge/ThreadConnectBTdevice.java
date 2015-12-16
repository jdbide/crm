package badge.system.pds.crmbadge;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Muthu on 10/11/2015.
 */
public class ThreadConnectBTdevice extends Thread {
    private BluetoothSocket bluetoothSocket = null;
    private final BluetoothDevice bluetoothDevice;
    ThreadConnected myThreadConnected;


    public ThreadConnectBTdevice(BluetoothDevice device, UUID myUUID) {
        bluetoothDevice = device;

        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean success = false;
        try {
            bluetoothSocket.connect();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                bluetoothSocket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if(success) {
            //connect OK
            startThreadConnected(bluetoothSocket);
        }

               /*
                final String msgconnected = "connect OK:\n"
                        + "BluetoothSocket: " + bluetoothSocket + "\n"
                        + "BluetoothDevice: " + bluetoothDevice;

               runOnUiThread(new Runnable() {

                   @Override
                   public void run() {
                       textStatus.setText(msgconnected);

                       listViewPairedDevice.setVisibility(View.GONE);
                       inputPane.setVisibility(View.VISIBLE);
                    }});


            }else{
                //fail
            }*/
    }

    public void cancel() {

        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void startThreadConnected(BluetoothSocket socket){

        myThreadConnected = new ThreadConnected(socket);
        myThreadConnected.run();

    }
}
