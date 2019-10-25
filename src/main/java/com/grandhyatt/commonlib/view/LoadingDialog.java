package com.grandhyatt.commonlib.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.grandhyatt.commonlib.R;

/**
 * 加载提醒对话框
 */
public class LoadingDialog extends ProgressDialog {

    private String mContent;

    private TextView mTv_Content;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public LoadingDialog(Context context, int theme, String content) {
        super(context,theme);
        mContent = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(true);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.dialog_loading);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        mTv_Content = findViewById(R.id.mTv_Content);
        if(!TextUtils.isEmpty(mContent)){
            mTv_Content.setText(mContent);
        }
    }

    @Override
    public void show() {
        super.show();
    }
}