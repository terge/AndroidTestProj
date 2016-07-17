package me.terge.androidtest.scene.customview;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/**
 * Created by tangzg on 15-12-30.
 */
public class ProgressAnimationDrawable  implements Runnable, Animatable {
    private Drawable mDrawable;
    ProgressAnimationDrawable(Drawable drawable){
        Context context = null;
        context.getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal);
    }
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void run() {

    }
}
