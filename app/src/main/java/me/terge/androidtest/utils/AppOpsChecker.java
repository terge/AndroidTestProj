package me.terge.androidtest.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 权限检查工具
 * 4.3以上适用
 * 
 * @author:ligs@ucweb.com
 * @createTime:Jun 29, 20153:25:47 PM
 */
public class AppOpsChecker {

    private static final String CLASS_NAME = "AppOpsChecker";

    private static final String APP_OPS_SERVICE = "appops";

    public static final int MODE_ALLOWED = 0;


    public static final int MODE_IGNORED = 1;


    public static final int MODE_ERRORED = 2;

    /** No operation specified. */
    public static final int OP_NONE = -1;
    /** Access to coarse location information. */
    public static final int OP_COARSE_LOCATION = 0;
    /** Access to fine location information. */
    public static final int OP_FINE_LOCATION = 1;
    /** Causing GPS to run. */
    public static final int OP_GPS = 2;
    public static final int OP_VIBRATE = 3;
    public static final int OP_READ_CONTACTS = 4;
    public static final int OP_WRITE_CONTACTS = 5;
    public static final int OP_READ_CALL_LOG = 6;
    public static final int OP_WRITE_CALL_LOG = 7;
    public static final int OP_READ_CALENDAR = 8;
    public static final int OP_WRITE_CALENDAR = 9;
    public static final int OP_WIFI_SCAN = 10;
    public static final int OP_POST_NOTIFICATION = 11;
    public static final int OP_NEIGHBORING_CELLS = 12;
    public static final int OP_CALL_PHONE = 13;
    public static final int OP_READ_SMS = 14;
    public static final int OP_WRITE_SMS = 15;
    public static final int OP_RECEIVE_SMS = 16;
    public static final int OP_RECEIVE_EMERGECY_SMS = 17;
    public static final int OP_RECEIVE_MMS = 18;
    public static final int OP_RECEIVE_WAP_PUSH = 19;
    public static final int OP_SEND_SMS = 20;
    public static final int OP_READ_ICC_SMS = 21;
    public static final int OP_WRITE_ICC_SMS = 22;
    public static final int OP_WRITE_SETTINGS = 23;
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    public static final int OP_ACCESS_NOTIFICATIONS = 25;
    public static final int OP_CAMERA = 26;
    public static final int OP_RECORD_AUDIO = 27;
    public static final int OP_PLAY_AUDIO = 28;
    public static final int OP_READ_CLIPBOARD = 29;
    public static final int OP_WRITE_CLIPBOARD = 30;
    public static final int OP_TAKE_MEDIA_BUTTONS = 31;
    public static final int OP_TAKE_AUDIO_FOCUS = 32;
    public static final int OP_AUDIO_MASTER_VOLUME = 33;
    public static final int OP_AUDIO_VOICE_VOLUME = 34;
    public static final int OP_AUDIO_RING_VOLUME = 35;
    public static final int OP_AUDIO_MEDIA_VOLUME = 36;
    public static final int OP_AUDIO_ALARM_VOLUME = 37;
    public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    public static final int OP_WAKE_LOCK = 40;
    /** Continually monitoring location data. */
    public static final int OP_MONITOR_LOCATION = 41;
    /** Continually monitoring location data with a relatively high power request. */
    public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
    public static final int _NUM_OP = 43;


    public static final boolean isFloatAllow(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            return checkOps(context, OP_SYSTEM_ALERT_WINDOW);
        } else {
            return true;
        }
    }

    /**
     * 检查对应权限开启情况
     * 
     * @param context
     * @param ops
     *            权限对应的int值
     * @return
     */
    @TargetApi(19)
    public static final boolean checkOps(Context context, int ops) {
        boolean disabled = false;

        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            if (context != null) {
                Object opsManager = context.getSystemService(APP_OPS_SERVICE);
                Class clazz = opsManager.getClass();
                try {
                    Method dispatchMethod = clazz.getMethod("checkOp", new Class[] { int.class, int.class, String.class });
                    int mode = (Integer) dispatchMethod.invoke(opsManager, new Object[] { ops, Binder.getCallingUid(), context.getApplicationContext().getPackageName() });
                    if (mode != MODE_ALLOWED) {
                        //disabled
                        disabled = false;
                    } else {
                        //enabled
                        disabled = true;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } else {
            disabled = false;
        }

        return disabled;
    }

    /**
     * 判断当前的程序是否存在输入的权限
     * 
     * @param ctx
     * @param permission
     *            待检测的权限，大小写敏感
     * @return true， 存在输入的权限；false，不存在输入的权限
     */
    public static boolean hasPermission(Context ctx, String permission) {
        boolean hasPermisson = false;

        if (permission == null ) {
            Log.d(CLASS_NAME, "hasPermission -- 输入的permission为空");
            return hasPermisson;
        }

        if (ctx != null) {
            int reslut = ctx.getPackageManager().checkPermission(permission, ctx.getPackageName());
            if (reslut == PackageManager.PERMISSION_GRANTED) {
                hasPermisson = true;
            }
        }

        return hasPermisson;
    }
}
