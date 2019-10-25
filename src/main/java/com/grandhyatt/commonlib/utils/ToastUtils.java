package com.grandhyatt.commonlib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtils {

    /** 之前显示的内容 */
    private static String mStr_Old_Message ;
    /** Toast对象 */
    private static Toast mToast = null ;
    /** 第一次时间 */
    private static long mLng_One_Time = 0 ;
    /** 第二次时间 */
    private static long mLng_Two_Time = 0 ;

    /**
     * 显示短Toast
     * */
    public static void showShortToast(Context context,String text){
        if (TextUtils.isEmpty(text)){
            return;
        }
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长Toast
     *
     * */
    public static void showLongToast(Context context,String text){
        if (TextUtils.isEmpty(text)){
            return;
        }
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    /**
     * 显示Toast
     * @param context
     * @param text
     */
    public static void showToast(Context context,String text){
        if (TextUtils.isEmpty(text)){
            return;
        }

        if(mToast == null){
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            mToast.show() ;
            mLng_One_Time = System.currentTimeMillis() ;
        }else{
            mLng_Two_Time = System.currentTimeMillis() ;
            if(text.equals(mStr_Old_Message)){
                if(mLng_Two_Time - mLng_One_Time > Toast.LENGTH_SHORT){
                    mToast.show() ;
                }
            }else{
                mStr_Old_Message = text ;
                mToast.setText(text) ;
                mToast.show() ;
            }
        }
        mLng_One_Time = mLng_Two_Time ;
    }

}
