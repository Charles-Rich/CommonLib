package com.grandhyatt.commonlib;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Application基类
 *
 * @author
 * @email
 * @mobile
 * @create 2018/6/1 10:50
 */
public class ApplicationBase extends Application {

    private static final String TAG = ApplicationBase.class.getSimpleName();

    private static ApplicationBase mInstance;

    private static Handler mHandler = new Handler();

    @Override
    public void onCreate(){
        super.onCreate();

        mInstance = this;
    }

    /**
     * 获取Instance
     *
     */
    public static ApplicationBase getInstance(){
        return mInstance;
    }

    /**
     * 获取Application Context
     *
     */
    public static Context getAppContext(){
        return mInstance != null ? mInstance.getApplicationContext():null;
    }
    
    /**
     * 在主线程上执行Runnable
     *
     */
    public static void runOnMainThread(Runnable runnable){
        mHandler.post(runnable);
    }
}
