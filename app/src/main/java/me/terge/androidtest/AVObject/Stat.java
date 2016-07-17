package me.terge.androidtest.AVObject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by terge on 16-7-7.
 */
@AVClassName("Stat")
public class Stat extends AVObject{
    public static final String KEY_CPU_COUNT_RUNTIME = "cpu_count_runtime";
    public static final String KEY_CPU_COUNT_SYS_FILE = "cpu_count_sysfile";
    public static final String KEY_MODEL = "model";
    public Stat(){}
    public Stat setCpuCountRuntime(int count){
        put(KEY_CPU_COUNT_RUNTIME,count);
        return this;
    }

    public Stat setCpuCountSysFile(int count){
        put(KEY_CPU_COUNT_SYS_FILE,count);
        return this;
    }

    public Stat setModel(String model){
        put(KEY_MODEL,model);
        return this;
    }
}
