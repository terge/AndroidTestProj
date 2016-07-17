package me.terge.androidtest.scene.launchmode;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.terge.androidtest.AbsActivity;
import me.terge.androidtest.R;

/**
 * Created by tangzg on 15-12-4.
 */
public class LaunchmodeBase  extends AbsActivity{
    private Spinner spMode,spActivity;
    protected TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("launchmode", getClass().getSimpleName() + " -onCreate");
        printAppTask();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        tv = (TextView)findViewById(R.id.tv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("launchmode", getClass().getSimpleName() + " -onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("launchmode", getClass().getSimpleName() + " -onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("launchmode", getClass().getSimpleName() + " -onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("launchmode", getClass().getSimpleName() + " -onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("launchmode", getClass().getSimpleName() + " -onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("launchmode", getClass().getSimpleName() + " -onNewIntent");
    }

    public void startActivity(View view){
        Intent intent = new Intent();
        Class<? extends Activity> cls = getSelectClass();
        if(cls == null){
            Toast.makeText(context,"未配置要跳转的activity",Toast.LENGTH_SHORT).show();
            return;
        }
        intent.setClass(context,cls);
        int launchMode = getSelectMode();
        if(launchMode !=-1)intent.setFlags(getSelectMode());
        startActivity(intent);
    }

    protected int getSelectMode() {
        return -1;
    }

    protected Class<? extends Activity> getSelectClass() {
        return null;
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void printAppTask(){
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> taskList = am.getAppTasks();
        for(ActivityManager.AppTask task:taskList){
            ActivityManager.RecentTaskInfo taskInfo = task.getTaskInfo();
            Log.d("launchmode",getClass().getSimpleName() + " taskId:"+taskInfo.id+" baseActivity:"+taskInfo.baseActivity.getShortClassName()+" topActivity:"+taskInfo.topActivity.getShortClassName());
        }
    }

    class SpModeAdatper implements SpinnerAdapter{
        List<Info> data = new ArrayList<Info>();
        SpModeAdatper(){
//            data.add(new Info("",Intent.FLAG_ACTIVITY_SINGLE_TOP));
//            data.add(new Info("",Intent.FLAG_ACTIVITY_NEW_TASK));
//            data.add(new Info("",Intent.));
        }

        @Override
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }


        @Override
        public boolean hasStableIds() {
            return false;
        }



        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        private class Info{
            Info(String desc,int mode){
                this.desc = desc;
                this.mode = mode;
            }
            String desc;
            int mode;
        }
    }

    class SpActivityAdatper implements SpinnerAdapter {


        @Override
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }



        @Override
        public boolean hasStableIds() {
            return false;
        }



        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Info getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(new ViewGroup.LayoutParams(-2,-2));
            tv.setText(getItem(i).desc);
            return tv;
        }

        private class Info{
            Info(String desc,Class<Activity> clz){
                this.desc = desc;
                this.clz = clz;
            }
            String desc;
            Class<Activity> clz;
        }
    }


}
