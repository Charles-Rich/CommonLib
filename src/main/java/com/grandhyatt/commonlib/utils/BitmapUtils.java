package com.grandhyatt.commonlib.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 *
 *
 * @author
 * @email
 * @mobile
 * @create 2018/7/25 23:37
 */
public class BitmapUtils {

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
