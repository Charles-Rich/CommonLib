package com.grandhyatt.commonlib.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 包信息工具类
 *
 * @author
 * @email
 * @mobile
 * @create 2018/6/1 10:50
 */
public class PackageUtils {

    public static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String getPackageVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

}
