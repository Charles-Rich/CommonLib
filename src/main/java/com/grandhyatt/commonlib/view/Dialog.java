package com.grandhyatt.commonlib.view;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grandhyatt.commonlib.R;


/**
 * 自定义Dialog 包含标题,内容,确定按钮和取消按钮
 *
 * @author
 * @email
 * @mobile
 * @create 2018/7/2 13:14
 */
public class Dialog extends android.app.Dialog {

	public Dialog(Context context) {
		super(context);
	}

	public Dialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context mContext;
		private String mStr_Title;
		private String mStr_Message;
		private boolean mBol_Cancelable;
		private String mStr_PositiveButtonText;
		private String mStr_NegativeButtonText;
		private int mInt_PositiveButtonTextColor = -1;
		private int mInt_NegativeButtonTextColor = -1;
		private View mContentView;
		private TextView mTv_Title;
		private TextView mTv_Message;
		private Button mBtn_PositiveButton;
		private Button mBtn_NegativeButton;
		private OnClickListener mPositiveButtonClickListener;
		private OnClickListener mNegativeButtonClickListener;

		public Builder(Context context) {
			this.mContext = context;
		}

		/**
		 * 设置Dialog的message
		 * 
		 * @param message
		 * @return
		 */
		public Builder setMessage(String message) {
			this.mStr_Message = message;
			return this;
		}

		/**
		 * 设置Dialog的message
		 * 
		 * @param message
		 * @return
		 */
		public Builder setMessage(int message) {
			this.mStr_Message = (String) mContext.getText(message);
			return this;
		}

		/**
		 * 设置Dialog的标题
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.mStr_Title = (String) mContext.getText(title);
			return this;
		}

		/**
		 * 设置Dialog的标题
		 * 
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			this.mStr_Title = title;
			return this;
		}

		public Builder setContentView(View view) {
			this.mContentView = view;
			return this;
		}
		
		public Builder setCancelable(boolean value){
			this.mBol_Cancelable = value;
			return this;
		}

		/**
		 * 设置确认按钮的文本和单击监听事件
		 * 
		 * @param buttonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(int buttonText, OnClickListener listener) {
			this.mStr_PositiveButtonText = (String) mContext.getText(buttonText);
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		/**
		 * 设置确认按钮的文本和单击监听事件
		 *
		 * @param buttonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String buttonText, OnClickListener listener) {
			this.mStr_PositiveButtonText = buttonText;
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		/**
		 * 设置取消按钮的文本和单击监听事件
		 *
		 * @param buttonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int buttonText, OnClickListener listener) {
			this.mStr_NegativeButtonText = (String) mContext.getText(buttonText);
			this.mNegativeButtonClickListener = listener;
			return this;
		}

		/**
		 * 设置取消按钮的文本和单击监听事件
		 *
		 * @param buttonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String buttonText, OnClickListener listener) {
			this.mStr_NegativeButtonText = buttonText;
			this.mNegativeButtonClickListener = listener;
			return this;
		}
		
		/**
		 * 设置PositiveButton的文本颜色
		 * 
		 * @param color
		 * @return
		 */
		public Builder setPositiveButtonTextColor(int color) {
			this.mInt_PositiveButtonTextColor = color;
			return this;
		}
		
		/**
		 * 设置NegativeButton的文本颜色
		 * 
		 * @param color
		 * @return
		 */
		public Builder setNegativeButtonTextColor(int color) {
			this.mInt_NegativeButtonTextColor = color;
			return this;
		}

		public Dialog create() {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final Dialog dialog = new Dialog(mContext, R.style.Dialog);
			View layout = inflater.inflate(R.layout.view_dialog, null);
			dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			mTv_Title = (TextView)layout.findViewById(R.id.mTv_Title);
			mTv_Message = (TextView)layout.findViewById(R.id.mTv_Message);
			
			mBtn_PositiveButton = (Button)layout.findViewById(R.id.mBtn_PositiveButton);
			mBtn_NegativeButton = (Button)layout.findViewById(R.id.mBtn_NegativeButton);
			
			if(mInt_PositiveButtonTextColor != -1){
				mBtn_PositiveButton.setTextColor(mInt_PositiveButtonTextColor);
			}
			
			if(mInt_NegativeButtonTextColor != -1){
				mBtn_NegativeButton.setTextColor(mInt_NegativeButtonTextColor);
			}
			
			if(!TextUtils.isEmpty(mStr_Title)){
				mTv_Title.setText(mStr_Title);
				mTv_Title.setVisibility(View.VISIBLE);
			}else{
				mTv_Title.setVisibility(View.GONE);
			}
			
			if (!TextUtils.isEmpty(mStr_PositiveButtonText)) {
				mBtn_PositiveButton.setText(mStr_PositiveButtonText);
				if (mPositiveButtonClickListener != null) {
					mBtn_PositiveButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							mPositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
						}
					});
				}
			} else {
				mBtn_PositiveButton.setVisibility(View.GONE);
			}
			if (!TextUtils.isEmpty(mStr_NegativeButtonText)) {
				mBtn_NegativeButton.setText(mStr_NegativeButtonText);
				if (mNegativeButtonClickListener != null) {
					mBtn_NegativeButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							mNegativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
						}
					});
				}
			} else {
				mBtn_NegativeButton.setVisibility(View.GONE);
			}
			if (!TextUtils.isEmpty(mStr_Message)) {
				mTv_Message.setText(mStr_Message);
			} else if (mContentView != null) {
				((LinearLayout) layout.findViewById(R.id.mLL_Content)).removeAllViews();
				((LinearLayout) layout.findViewById(R.id.mLL_Content)).addView(mContentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}
			
			dialog.setCancelable(mBol_Cancelable);
			
			dialog.setContentView(layout);
			return dialog;
		}
	}
	

	/** 调用方法

		Dialog.Builder builder = new Dialog.Builder(this);
		builder.setMessage("你确定真的要删除吗?");
		builder.setTitle("删除");
		builder.setPositiveButtonTextColor(getResources().getColor(R.color.blue2));
		builder.setNegativeButtonTextColor(getResources().getColor(R.color.blue2));
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//设置你的操作事项
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();

	 */

}
