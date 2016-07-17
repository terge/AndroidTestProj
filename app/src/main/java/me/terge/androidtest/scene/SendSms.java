package me.terge.androidtest.scene;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;

import me.terge.androidtest.AbsActivity;
import me.terge.androidtest.R;
import me.terge.androidtest.utils.AppOpsChecker;

public class SendSms extends AbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

    }

    public void sendSms(View view){
        boolean result = AppOpsChecker.checkOps(context,AppOpsChecker.OP_SEND_SMS);
        Log.e("terge","sendsms op:"+result);

        Intent sentIntent = new Intent("SENT_SMS_ACTION");
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("18610273153", null, "lalal", sentPI, null);
    }

}
