package com.grandhyatt.commonlib.view.activity;

/**
 * Activity基类接口
 *
 * @author wulifu
 * @email 18602438878@qq.com
 * @mobile 18602438878
 * @create 2018/7/26 22:28
 */
public interface IActivityBase {

    /** 绑定View */
    void initView();

    /** 绑定事件 */
    void bindEvent();

    /** 刷新UI */
    void refreshUI();

    /** 请求本地数据 */
    void requestDBData();

    /** 请求网络数据 */
    void requestNetworkData();

}
