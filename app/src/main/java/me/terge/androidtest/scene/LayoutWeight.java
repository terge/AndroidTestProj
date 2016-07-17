package me.terge.androidtest.scene;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.terge.androidtest.AbsActivity;
import me.terge.androidtest.R;

/**
 * Created by tangzg on 16-2-19.
 */
public class LayoutWeight extends AbsActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout_weight);
        btn2 = (Button) findViewById(R.id.btn2);
    }
    private Button btn2;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_weight_gone:
                break;
            case R.id.btn_weight_visiable:
                break;

        }
    }
}
