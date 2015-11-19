package badge.system.pds.crmbadge;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Muthu on 10/11/2015.
 */
public class ThreadConnected implements Runnable {

//    private final BluetoothSocket connectedBluetoothSocket;
    private final InputStream connectedInputStream;
    private final OutputStream connectedOutputStream;

    public ThreadConnected(BluetoothSocket socket) {
        CallActivity.connectedBluetoothSocket = socket;
        InputStream in = null;
        OutputStream out = null;

        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        connectedInputStream = in;
        connectedOutputStream = out;

       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();*/
    }








    public void write(byte[] buffer) {
        try {
            connectedOutputStream.write(buffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while (true) {
            try {
                bytes = connectedInputStream.read(buffer);
                String strReceived = new String(buffer, 0, bytes);

                //faire l'appel

                CallActivity.makeCall(strReceived);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();


            }
        }
    }

}