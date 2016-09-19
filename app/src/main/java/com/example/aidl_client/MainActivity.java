package com.example.aidl_client;

import com.example.aidl.IService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    IService RemoteService; //�������
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.i("mConnection", service + "");
            RemoteService = IService.Stub.asInterface(service);

            try {
                String s = RemoteService.hello("finch");
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

    };
    private TextView tv_result;
    private TextView disbundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        disbundle = (TextView) findViewById(R.id.disbundle);
        tv_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initService();
            }
        });
        disbundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseService();
            }
        });
    }

    //���ӷ���
    private void initService() {
        Intent i = new Intent();
        i.setPackage("com.example.aidl_server");
        i.setAction("android.intent.action.AIDLService");
        boolean ret = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
    }

    //�Ͽ�����
    private void releaseService() {
        unbindService(mConnection);
//        mConnection = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
}
