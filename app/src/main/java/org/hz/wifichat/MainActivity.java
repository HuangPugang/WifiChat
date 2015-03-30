package org.hz.wifichat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private MainService mService = null;
    private Intent mMainServiceIntent=null;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((MainService.ServiceBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainServiceIntent = new Intent(this,MainService.class);
        bindService(mMainServiceIntent,mConnection,BIND_AUTO_CREATE);
        startService(mMainServiceIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mMainServiceIntent);
        unbindService(mConnection);
    }
}
