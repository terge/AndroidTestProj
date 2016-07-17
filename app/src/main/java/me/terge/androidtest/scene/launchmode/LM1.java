package me.terge.androidtest.scene.launchmode;

import android.app.Activity;
import android.os.Bundle;

import me.terge.androidtest.R;

/**
 * Created by tangzg on 15-12-4.
 */
public class LM1 extends LaunchmodeBase{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launche_mode);
        tv.setText("当前是LM1");
        tv.append("跳转到standard的LM2");
    }

    @Override
    protected Class<? extends Activity> getSelectClass() {
        return LM2.class;
    }

//    @Override
//    protected int getSelectMode() {
//        return Intent.FLAG_ACTIVITY_NEW_TASK;
//    }
}
