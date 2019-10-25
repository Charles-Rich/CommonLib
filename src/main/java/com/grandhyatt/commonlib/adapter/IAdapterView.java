package com.grandhyatt.commonlib.adapter;

/**
 * 绑定控件接口
 *
 * @author wulifu
 * @email 18602438878@qq.com
 * @mobile 18602438878
 * @create 2018/7/26 22:27
 */
public interface IAdapterView<T> {
	public void bind(int position, T... t);
}
