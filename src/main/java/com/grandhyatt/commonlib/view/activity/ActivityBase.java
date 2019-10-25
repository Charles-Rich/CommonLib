package com.grandhyatt.commonlib.view.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.grandhyatt.commonlib.R;
import com.grandhyatt.commonlib.view.LoadingDialog;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Activity基类
 *
 * @author wulifu
 * @email 18602438878@qq.com
 * @mobile 18602438878
 * @create 2018/7/26 22:31
 */
public class ActivityBase extends SwipeBackActivity {

    private static final String TAG = ActivityBase.class.getSimpleName();

    private LoadingDialog mLoadingDialog;

    private boolean isDestroyed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isDestroyed = false;
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        isDestroyed = true;
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @SuppressLint("NewApi")
    @Override
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return isDestroyed;
        } else {
            return super.isDestroyed();
        }
    }

    /**
     * 是否可以对UI进行操作，比如更新UI控件，显示／消失对话框等
     * 由于Activity中存在大量异步网络操作，若异步回调时，Activity已被销毁，则不可以对UI进行更新操作
     *
     * @return true  - Activity未被销毁，可更新UI
     * false - Activity已被销毁，不可更新UI
     */
    @SuppressLint("NewApi")
    public boolean canUpdateUI() {
        return (!isFinishing()) && (!isDestroyed());
    }

    /**
     * 显示加载对话框
     */
    protected void showLogingDialog() {
        showLogingDialog("");
    }

    /**
     * 显示加载对话框
     */
    protected void showLogingDialog(String content) {
        mLoadingDialog = new LoadingDialog(ActivityBase.this, R.style.LoadingDialog, content);

        if(!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }

    }

    /**
     * 隐藏加载对话框
     */
    protected void dismissLoadingDialog() {
        if (mLoadingDialog == null) {
            return;
        }
        mLoadingDialog.dismiss();
    }
}
