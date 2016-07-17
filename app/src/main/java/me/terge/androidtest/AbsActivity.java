package me.terge.androidtest;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by tangzg on 15-11-20.
 */
public class AbsActivity extends Activity {
    protected Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }
}
