package com.grandhyatt.commonlib;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 当前应用及系统的环境信息
 *
 * @author
 * @email
 * @mobile
 * @create 2018/6/1 10:50
 */
public class Environment {
	/**
	 * 获取Application Context
	 * */
	public static Context getAppContext() {
		return ApplicationBase.getAppContext();
	}

	public static int getPackageVersionCode() {
		try {
			Context context = ApplicationBase.getAppContext();
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException ex) {
			ex.printStackTrace();
			return 1;
		}
	}

	public static String getPackageVersionName() {
		try {
			Context context = ApplicationBase.getAppContext();
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException ex) {
			ex.printStackTrace();
			return "1.0";
		}
	}
}
