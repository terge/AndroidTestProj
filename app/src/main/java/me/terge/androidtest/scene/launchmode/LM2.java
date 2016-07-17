package me.terge.androidtest.scene.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import me.terge.androidtest.R;

/**
 * Created by tangzg on 15-12-4.
 */
public class LM2 extends LaunchmodeBase{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launche_mode);
        tv.setText("LM2");
        tv.append("要start到standard的LM3");
    }

    @Override
    protected Class<? extends Activity> getSelectClass() {
        return LM3.class;
    }
    @Override
    protected int getSelectMode() {
        return Intent.FLAG_ACTIVITY_NEW_TASK;
    }

}
