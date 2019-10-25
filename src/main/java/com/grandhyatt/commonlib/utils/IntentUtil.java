package com.grandhyatt.commonlib.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;

import com.grandhyatt.commonlib.R;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 页面跳转控制类
 * 
 * @author wu
 * @date 2018-06-23 18:32:36
 */

public class IntentUtil {

	public static void newIntent(Context context, Class<?> toActivity) {
		newIntent(context, toActivity, null, 0, false);
	}

	public static void newIntent(Context context, Class<?> toActivity, HashMap<String, Object> hashMaps) {
		newIntent(context, toActivity, hashMaps, 0, false);
	}

	public static void newIntentForResult(Activity fromActivity, Class<?> toActivity, int code) {
		newIntent(fromActivity, toActivity, null, code, true);
	}
	
	public static void newIntentForResult(Activity fromActivity, Class<?> toActivity, HashMap<String, Object> hashMaps,int code) {
		newIntent(fromActivity, toActivity, hashMaps,code, true);
	}
	
	@SuppressLint("NewApi")
	public static void newIntentForResult(Fragment fragment, Class<?> toActivity, int code) {
		newIntent(fragment, toActivity, null, code, true);
	}
	
	@SuppressLint("NewApi")
	public static void newIntentForResult(Fragment fragment, Class<?> toActivity, HashMap<String, Object> hashMaps,int code) {
		newIntent(fragment, toActivity, hashMaps,code, true);
	}

	/**
	 * @param from_act Context对象
	 * @param toActivity 目标页
	 * @param hashMaps 携带参数
	 */
	public static void newIntent(Context from_act, Class<?> toActivity, HashMap<String, Object> hashMaps, int code, boolean isResult) {
		Intent intent = new Intent(from_act, toActivity);
		if (null != hashMaps) {
			if (hashMaps.size() > 0) {
				for (Map.Entry<String, Object> entry : hashMaps.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						intent.putExtra(entry.getKey(), (Integer) entry.getValue());
					} else if (entry.getValue() instanceof Long) {
						intent.putExtra(entry.getKey(), (Long) entry.getValue());
					} else if (entry.getValue() instanceof Double) {
						intent.putExtra(entry.getKey(), (Double) entry.getValue());
					} else if (entry.getValue() instanceof Boolean) {
						intent.putExtra(entry.getKey(), (Boolean) entry.getValue());
					} else if (entry.getValue() instanceof Serializable) {
						intent.putExtra(entry.getKey(), (Serializable) entry.getValue());
					} else {
						intent.putExtra(entry.getKey(), entry.getValue() + "");
					}
				}
			}
		}
		if (isResult) {
			Activity activity = (Activity) from_act;
			activity.startActivityForResult(intent, code);
		} else {
			from_act.startActivity(intent);
		}
		((Activity)from_act).overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
	}
	
	/**
	 * @param fragment Context对象
	 * @param toActivity 目标页
	 * @param hashMaps 携带参数
	 */
	public static void newIntent(Fragment fragment, Class<?> toActivity, HashMap<String, Object> hashMaps, int code, boolean isResult) {
		Intent intent = new Intent(fragment.getActivity(), toActivity);
		if (null != hashMaps) {
			if (hashMaps.size() > 0) {
				for (Map.Entry<String, Object> entry : hashMaps.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						intent.putExtra(entry.getKey(), (Integer) entry.getValue());
					} else if (entry.getValue() instanceof Long) {
						intent.putExtra(entry.getKey(), (Long) entry.getValue());
					} else if (entry.getValue() instanceof Double) {
						intent.putExtra(entry.getKey(), (Double) entry.getValue());
					} else if (entry.getValue() instanceof Boolean) {
						intent.putExtra(entry.getKey(), (Boolean) entry.getValue());
					} else if (entry.getValue() instanceof Serializable) {
						intent.putExtra(entry.getKey(), (Serializable) entry.getValue());
					} else {
						intent.putExtra(entry.getKey(), entry.getValue() + "");
					}
				}
			}
		}
		if (isResult) {
			fragment.startActivityForResult(intent, code);
		} else {
			fragment.startActivity(intent);
		}
		fragment.getActivity().overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
	}

	/**
	 * 打电话
	 *
	 * @param context
	 */
	public static void call(Context context, String phone) {
		try {
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发短信
	 * 
	 * @param activity
	 * @param number 电话
	 * @param message 内容
	 */
	public static void sendMessage(Activity activity, String number, String message) {
		Uri uri = Uri.parse("smsto:" + number);
		Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
		sendIntent.putExtra("sms_body", message);
		activity.startActivity(sendIntent);
	}

	/**
	 * 安装apk
	 */
	public static void installApk(Context c, String apkPath) {
		File file = new File(apkPath);
		if (!file.exists()) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		c.startActivity(intent);
	}
	
}
