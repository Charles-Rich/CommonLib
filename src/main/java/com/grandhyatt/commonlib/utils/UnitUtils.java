package com.grandhyatt.commonlib.utils;

import android.content.Context;
import android.util.Log;

/**
 * DP与像素转换工具类
 *
 * @author
 * @email
 * @mobile
 * @create 2017/6/29 17:49
 */
public class UnitUtils {

	private UnitUtils(){

	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		Log.e("*******dp2px*********","dpValue=" + dpValue + ", scale=" + String.valueOf(scale));
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		Log.e("*******px2dp*********","dpValue=" + pxValue + ", scale=" + String.valueOf(scale));
		return (int) (pxValue / scale + 0.5f);
	}


}
