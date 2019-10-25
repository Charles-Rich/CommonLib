package com.grandhyatt.commonlib.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.grandhyatt.commonlib.R;

import java.util.List;

/**
 *
 *
 * @author
 * @email
 * @mobile
 * @create 2018/7/2 11:30
 */
public class SelectDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener {

    private SelectDialogListener mListener;
    private Activity mActivity;
    private Button mMBtn_Cancel;
    private TextView mTv_Title;
    private List<String> mDataList;
    private String mTitle;

    /**
     *
     *
     * * @param null
     * @return
     */
    public interface SelectDialogListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

   /**
    * 取消事件监听接口
    *
    * * @param null
    * @return
    */
    private SelectDialogCancelListener mCancelListener;

    /**
     *
     *
     * * @param null
     * @return
     */
    public interface SelectDialogCancelListener {
        void onCancelClick(View v);
    }

    /**
     *
     *
     * * @param null
     * @return
     */
    public SelectDialog(Activity activity, int theme, SelectDialogListener listener, List<String> lists) {
        super(activity, theme);
        mActivity = activity;
        mListener = listener;
        this.mDataList = lists;

        // 设置是否点击外围解散
        setCanceledOnTouchOutside(true);
    }

    /**
     * @param activity 调用弹出菜单的activity
     * @param theme 主题
     * @param listener 菜单项单击事件
     * @param cancelListener 取消事件
     * @param lists 菜单项名称
     */
    public SelectDialog(Activity activity, int theme, SelectDialogListener listener, SelectDialogCancelListener cancelListener, List<String> lists) {
        super(activity, theme);
        mActivity = activity;
        mListener = listener;
        mCancelListener = cancelListener;
        this.mDataList = lists;

        // 设置是否点击外围不解散
        setCanceledOnTouchOutside(false);
    }

    /**
     * @param activity 调用弹出菜单的activity
     * @param theme 主题
     * @param listener 菜单项单击事件
     * @param lists 菜单项名称
     * @param title 菜单标题文字
     */
    public SelectDialog(Activity activity, int theme, SelectDialogListener listener, List<String> lists, String title) {
        super(activity, theme);
        mActivity = activity;
        mListener = listener;
        this.mDataList = lists;
        mTitle = title;

        // 设置是否点击外围可解散
        setCanceledOnTouchOutside(true);
    }

    /**
     * @param activity 调用弹出菜单的activity
     * @param theme 主题
     * @param listener 菜单项单击事件
     * @param cancelListener 菜单项单机取消事件
     * @param lists 菜单项名称
     * @param title 菜单标题文字
     */
    public SelectDialog(Activity activity, int theme, SelectDialogListener listener, SelectDialogCancelListener cancelListener, List<String> lists, String title) {
        super(activity, theme);
        mActivity = activity;
        mListener = listener;
        mCancelListener = cancelListener;
        this.mDataList = lists;
        mTitle = title;

        // 设置是否点击外围可解散
        setCanceledOnTouchOutside(true);
    }

    /**
     *
     *
     * * @param null
     * @return
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.common_view_dialog, null);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        onWindowAttributesChanged(wl);

        /**
         *  2015-03-17 11:08 修改:为解决只能通过取消按钮隐藏菜单项 // 设置点击外围解散
         * //setCanceledOnTouchOutside(false);
         */
        initViews();
    }

    /**
     *
     *
     * * @param null
     * @return
     */
    private void initViews() {
        DialogAdapter dialogAdapter = new DialogAdapter(mDataList);
        ListView dialogList = (ListView) findViewById(R.id.mLv_DialogList);
        dialogList.setOnItemClickListener(this);
        dialogList.setAdapter(dialogAdapter);
        mMBtn_Cancel = (Button) findViewById(R.id.mBtn_Cancel);
        mTv_Title = (TextView) findViewById(R.id.mTv_Title);

        /**
         * 2015-03-17 11:08 修改:为解决只能通过取消按钮隐藏菜单项
         * //mMBtn_Cancel.setOnClickListener(this);
         */

        /**  2015-03-17 11:10 调用取消事件 */
        mMBtn_Cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mCancelListener != null) {
                    mCancelListener.onCancelClick(v);
                }
                dismiss();
            }
        });

        /** 2015-03-20 16:33 用于显示菜单的标题 */
        if (!TextUtils.isEmpty(mTitle) && mTv_Title != null) {
            mTv_Title.setVisibility(View.VISIBLE);
            mTv_Title.setText(mTitle);
        } else {
            mTv_Title.setVisibility(View.GONE);
        }
    }

    /**
     *
     *
     * * @param null
     * @return
     */
    @Override
    public void onClick(View v) {
        dismiss();

    }

    /**
     *
     *
     * * @param null
     * @return
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mListener.onItemClick(parent, view, position, id);
        dismiss();
    }

    /**
     *
     *
     * * @param null
     * @return
     */
    private class DialogAdapter extends BaseAdapter {
        private List<String> mDataList;
        private Viewholder viewholder;
        private LayoutInflater layoutInflater;


        /**
         *
         *
         * * @param null
         * @return
         */
        public DialogAdapter(List<String> dataList) {
            this.mDataList = dataList;
            this.layoutInflater = mActivity.getLayoutInflater();
        }

        /**
         *
         *
         * * @param null
         * @return
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mDataList.size();
        }

        /**
         *
         *
         * * @param null
         * @return
         */
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mDataList.get(position);
        }

        /**
         *
         *
         * * @param null
         * @return
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        /**
         *
         *
         * * @param null
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == convertView) {
                viewholder = new Viewholder();
                convertView = layoutInflater.inflate(R.layout.common_view_dialog_item, null);
                viewholder.mTv_Dialog_Item_Title = (TextView) convertView.findViewById(R.id.mTv_Dialog_Item_Title);
                convertView.setTag(viewholder);
            } else {
                viewholder = (Viewholder) convertView.getTag();
            }
            viewholder.mTv_Dialog_Item_Title.setText(mDataList.get(position));
            if (1 == mDataList.size()) {
                viewholder.mTv_Dialog_Item_Title.setTextColor(mActivity.getResources().getColor(R.color.dialog_blue));
                viewholder.mTv_Dialog_Item_Title.setBackgroundResource(R.drawable.common_dialog_item_bg_only);
            } else if (position == 0) {
                viewholder.mTv_Dialog_Item_Title.setTextColor(mActivity.getResources().getColor(R.color.dialog_blue));
                viewholder.mTv_Dialog_Item_Title.setBackgroundResource(R.drawable.common_dialog_item_bg_top);
            } else if (position == mDataList.size() - 1) {
                viewholder.mTv_Dialog_Item_Title.setTextColor(mActivity.getResources().getColor(R.color.dialog_blue));
                viewholder.mTv_Dialog_Item_Title.setBackgroundResource(R.drawable.common_dialog_item_bg_buttom);
            } else {
                viewholder.mTv_Dialog_Item_Title.setTextColor(mActivity.getResources().getColor(R.color.dialog_blue));
                viewholder.mTv_Dialog_Item_Title.setBackgroundResource(R.drawable.common_dialog_item_bg_center);
            }
            return convertView;
        }

    }

    /**
     *
     *
     * * @param null
     * @return
     */
    public static class Viewholder {
        public TextView mTv_Dialog_Item_Title;
    }
}
