package me.terge.androidtest.scene.launchmode;

import android.app.Activity;
import android.os.Bundle;

import me.terge.androidtest.R;

/**
 * Created by tangzg on 15-12-4.
 */
public class LM3 extends LaunchmodeBase{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launche_mode);
        tv.setText("当前是Lm3");
        tv.append("\n");
        tv.append("现在是要start到singleTask的LaunchmodeBase");
    }

    @Override
    protected Class<? extends Activity> getSelectClass() {
        return LM4.class;
    }
}
