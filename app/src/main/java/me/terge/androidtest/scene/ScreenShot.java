package me.terge.androidtest.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import me.terge.androidtest.AbsActivity;
import me.terge.androidtest.R;
import me.terge.androidtest.utils.ColorByteUtils;
import me.terge.androidtest.utils.ReflectionUtils;

public class ScreenShot extends AbsActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
        iv = (ImageView) findViewById(R.id.iv_screen);
    }

   public void onScreenShot(View view){
//       byReflection();
       byAdbShell();
//       byFrameBuffer();
   }

    private void byReflection(){
        Class<?> classType = Surface.class;
        String methodName = "screenshot";
        Class<?>[] argTypes = {Integer.class,Integer.class};

        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        float[] dimsFloat = {Math.abs(mDisplayMetrics.widthPixels), Math.abs(mDisplayMetrics.heightPixels)};

        Object obj = ReflectionUtils.invokeStatic(classType,methodName,argTypes,(int)dimsFloat[0],(int)dimsFloat[1]);
        Log.e("teg", "obj:" + obj);
        if(obj !=null){
            Bitmap bitmap = (Bitmap) obj;
            iv.setImageBitmap(bitmap);
        }
    }

    private void byAdbShell(){
        String imgPath = android.os.Environment.getExternalStorageDirectory()+File.separator+"teger.png";
        Log.d("teg", "imgPath:" + imgPath);

        try {

//            Process sh = Runtime.getRuntime().exec("su", null,null);
//            OutputStream os = sh.getOutputStream();
//            os.write(("/system/bin/screencap -p " + "screencap -p " + imgPath).getBytes("ASCII"));
//            os.flush();
//            os.close();
//            sh.waitFor();

            Runtime.getRuntime().exec("screencap -p "+imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        boolean isImgFileExisit = new File(imgPath).exists();
        Log.d("teg", "isImgFileExisit:" + isImgFileExisit);
        if(isImgFileExisit){
            Bitmap bm = BitmapFactory.decodeFile(imgPath);
            iv.setImageBitmap(bm);
        }


    }

    private void byFrameBuffer(){
        //1. read frame buffer file

        FileInputStream buf = null;
        try {
            Runtime.getRuntime().exec("chmod 777 /dev/graphics/fb0");
             buf = new  FileInputStream(new File("/dev/graphics/fb0"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2. get screen info
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = WM.getDefaultDisplay();
        display.getMetrics(metrics);
        int height = metrics.heightPixels; //屏幕高
        int with = metrics.widthPixels;     //屏幕的宽

        //获取显示方式
        int pixelformat = display.getPixelFormat();
        PixelFormat localPixelFormat1 = new PixelFormat();
        PixelFormat.getPixelFormatInfo(pixelformat, localPixelFormat1);
        int deepth = localPixelFormat1.bytesPerPixel;//位深


        byte[] piex = new byte[height * with * deepth];
        DataInputStream dStream = new DataInputStream(buf);
        try {
            dStream.readFully(piex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3. generate image file
        int data[] = ColorByteUtils.convertToColor(piex,with,height,deepth);
        Bitmap bitmap = Bitmap.createBitmap(data, with, height, Bitmap.Config.RGB_565);
        iv.setImageBitmap(bitmap);
//        FileOutputStream out = new FileOutputStream(file_name);
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
    }


}
