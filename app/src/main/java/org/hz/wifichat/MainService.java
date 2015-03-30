package org.hz.wifichat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Author huarizhong
 * Date 2015/3/30 10:40
 * PackageName org.hz.wifichat
 */
public class MainService extends Service {
    private static String TAG="MainService";
    private ServiceBinder mBinder = new ServiceBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    /**
     * 服务绑定
     */
    public class ServiceBinder extends Binder{
        public MainService getService(){
            return MainService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"MainService onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"MainService onDestroy()");
    }
}
