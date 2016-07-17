package me.terge.androidtest;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import me.terge.androidtest.AVObject.Stat;

/**
 * Created by terge on 16-7-7.
 */
public class TestApplication extends Application{
    private final String LEAN_APP_ID = "OWsIXz4Ysdz5FXA7ICWHAp3J-gzGzoHsz";
    private final String LEAN_APP_KEY = "GkTKw18rpNCTf0KFxQK7r1BN";
    @Override
    public void onCreate() {
        super.onCreate();

        AVObject.registerSubclass(Stat.class);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,LEAN_APP_ID,LEAN_APP_KEY);
    }
}
