package me.terge.androidtest.utils;

import android.util.Log;

/**
 * Created by tangzg on 15-12-1.
 */
public class ColorByteUtils {

    public static int[] convertToColor(byte[] piex,int width,int height,int deepth)   {
        switch (deepth) {
            case 2:
                return convertToColor_2byte(piex,width,height);
            case 3:
                return convertToColor_3byte(piex,width,height);
            case 4:
                return convertToColor_4byte(piex,width,height);
            default:
                Log.e("terge","Deepth Error!");
                return null;
        }
    }

    private static int[] convertToColor_2byte(byte[] piex,int width,int height) {
        int[] colors = new int[width * height];
        int len = piex.length;
        for (int i = 0; i < len; i += 2) {
            int colour = (piex[i+1] & 0xFF) << 8 | (piex[i] & 0xFF);
            int r = ((colour & 0xF800) >> 11)*8;
            int g = ((colour & 0x07E0) >> 5)*4;
            int b = (colour & 0x001F)*8;
            int a = 0xFF;
            colors[i / 2] = (a << 24) + (r << 16) + (g << 8) + b;
        }
        return colors;
    }

    private static int[] convertToColor_3byte(byte[] piex,int width,int height) {
        int[] colors = new int[width * height];
        int len = piex.length;
        for (int i = 0; i < len; i += 3) {
            int r = (piex[i] & 0xFF);
            int g = (piex[i + 1] & 0xFF);
            int b = (piex[i + 2] & 0xFF);
            int a = 0xFF;
            colors[i / 3] = (a << 24) + (r << 16) + (g << 8) + b;
        }
        return colors;
    }

    private static int[] convertToColor_4byte(byte[] piex,int width,int height) {
        int[] colors = new int[width * height];
        int len = piex.length;
        for (int i = 0; i < len; i += 4) {
            int r = (piex[i] & 0xFF);
            int g = (piex[i + 1] & 0xFF);
            int b = (piex[i + 2] & 0xFF);
            int a = (piex[i + 3] & 0xFF);
            colors[i / 4] = (a << 24) + (r << 16) + (g << 8) + b;
        }
        return colors;
    }
}
