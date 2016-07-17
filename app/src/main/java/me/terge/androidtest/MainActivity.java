package me.terge.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import me.terge.androidtest.AVObject.Stat;
import me.terge.androidtest.scene.PlayVideoWithWebview;
import me.terge.androidtest.scene.ScreenShot;
import me.terge.androidtest.scene.SendSms;
import me.terge.androidtest.scene.animation.ZoomUpAnimation;
import me.terge.androidtest.scene.launchmode.LM1;
import me.terge.androidtest.utils.DeviceUtils;

public class MainActivity extends Activity {
    SparseArray<Class> classMap = new SparseArray<Class>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initClassMap();
        stat();
    }

    public void initClassMap(){
        classMap.put(R.id.btn_play_video,PlayVideoWithWebview.class);
        classMap.put(R.id.btn_sceen_shot,ScreenShot.class);
        classMap.put(R.id.btn_launch_mode,LM1.class);
        classMap.put(R.id.btn_send_sms, SendSms.class);
        classMap.put(R.id.btn_zoom_up, ZoomUpAnimation.class);
    }



    public void onClick(View view){
        int id = view.getId();
        Class cls = classMap.get(id);
        if(cls == null)return;
        startActivity(new Intent(this,cls));
    }

    private void stat(){
        new Stat()//
            .setCpuCountRuntime(Runtime.getRuntime().availableProcessors())//
            .setCpuCountSysFile(DeviceUtils.getCpuNum())//
            .setModel(android.os.Build.MODEL)//
            .saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    Log.d("LeanCloud","stat "+(e == null?"success":"fail"));
                }
            });
    }
}
