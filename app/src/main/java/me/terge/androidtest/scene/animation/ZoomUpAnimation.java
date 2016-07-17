package me.terge.androidtest.scene.animation;

import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import me.terge.androidtest.AbsActivity;
import me.terge.androidtest.R;

/**
 * Created by terge on 16-7-11.
 */
public class ZoomUpAnimation  extends AbsActivity{
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_up);
        setupWindowAnimations();
    }



    private void setupWindowAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.layout_anim_item);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.1f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        LinearLayout content = (LinearLayout) findViewById(R.id.layout_anim_content);
        content.setLayoutAnimation(controller);
    }





}
