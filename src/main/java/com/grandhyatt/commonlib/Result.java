package com.grandhyatt.commonlib;

/**
 * API接口调用返回结果
 *
 * @author
 * @email
 * @mobile
 * @create 2018/6/1 10:50
 */
public class Result {
    public static final int RESULT_CODE_SUCCSED = 0;

    public int status;

    public String message;

    public String response;

    public Result(){
        status = 1;
        message = "unkonw";
    }

}
