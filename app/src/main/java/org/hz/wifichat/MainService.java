package org.hz.wifichat;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Author huarizhong
 * Date 2015/3/30 10:40
 * PackageName org.hz.wifichat
 */
public class MainService extends Service {
    private static String TAG="MainService";
    private ServiceBinder mBinder = new ServiceBinder();

    private WifiManager mWifiManager = null;//wifi管理器
    public InetAddress mLocalInetAddress=null;//本地地址
    private String mLocalIp=null;//本地ip
    private static Person mSelfInfo =null; //自己的个人信息


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
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        new CheckNetConnectivity().start();
        getPersonalInfo();
    }
    /**
     * 检测网络连接状态,获得本机IP地址
     * @author huarizhong
     *
     */
    private class CheckNetConnectivity extends Thread {
        public void run() {
            try {
                if (!mWifiManager.isWifiEnabled()) {
                    mWifiManager.setWifiEnabled(true);
                }

                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            if(inetAddress.isReachable(1000)){
                                mLocalInetAddress = inetAddress;
                                mLocalIp = inetAddress.getHostAddress().toString();
                                Log.e(TAG, mLocalIp);
                            }
                        }
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    };

    /**
     * 获得个人信息
     */
    private void getPersonalInfo(){
        if (mSelfInfo==null) {
            mSelfInfo = new Person();
            mSelfInfo.setId((int)(Math.random()*1000000));
            mSelfInfo.setIp(mLocalIp);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"MainService onDestroy()");
    }
}
