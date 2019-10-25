package com.grandhyatt.commonlib.utils;

import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 *
 * @author
 * @email
 * @mobile
 * @create 2018-07-03 00:31
 */
public class ViewUtil {

	/**
	 * @Description: 设置光标位置
	 * @param editText
	 *            void 返回类型
	 * @throws
	 */
	public static void setCursorLocation(EditText editText) {
		CharSequence text = editText.getText();
		if (text instanceof Spannable) {
			Spannable spanText = (Spannable) text;
			Selection.setSelection(spanText, text.length());
		}
	}

	/**
	 * 改变文字颜色
	 * 
	 * @param view
	 *            textview
	 * @param starttext
	 *            开始的不需要改变颜色的文字
	 * @param middletext
	 *            中间需要改变颜色的文字
	 * @param lasttext
	 *            最后不需要改变颜色的文字
	 * @param middlecolor
	 *            要改变的颜色
	 */
	public static void setTestViewStyle(TextView view, String starttext, String middletext, String lasttext, int middlecolor) {
		Spannable WordtoSpan = new SpannableString(starttext + middletext + lasttext);
		WordtoSpan.setSpan(new ForegroundColorSpan(middlecolor), starttext.length(), (starttext + middletext).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		view.setText(WordtoSpan);
	}

}
