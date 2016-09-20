package com.supengchao.github.ipc_messenger_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Messenger messenger = null;
    private TextView tv;
    private static final int MSG_PRINT = 1;
    private Button start;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };
    private boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messenger == null)
                    return;
                try {
                    Message msg = Message.obtain();
                    msg.what = MSG_PRINT;
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }

    private void bindService() {
//        Intent intent = new Intent();
        Intent intent = new Intent("com.supengchao.github.ipc_messenger.MessengerService");
        intent.setPackage("com.supengchao.github.ipc_messenger");
        intent.setAction("com.supengchao.github.ipc_messenger.MessengerService");
//        ComponentName cn = new ComponentName("com.supengchao.github.ipc_messenger", "com.supengchao.github.ipc_messenger.MessengerService");
//        intent.setComponent(cn);
//        intent.setAction("com.supengchao.github.ipc_messenger.MessengerService");
//        intent.setClassName("com.supengchao.github.ipc_messenger","com.supengchao.github.ipc_messenger.MessengerService");
        isBind = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        if (isBind) {
            unbindService(serviceConnection);
            isBind = false;
        }
    }
}
