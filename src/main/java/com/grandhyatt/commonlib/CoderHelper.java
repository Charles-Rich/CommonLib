package com.grandhyatt.commonlib;

import java.io.Closeable;

/**
 * 语法简化器
 * 
 * @author
 * @email
 * @create_date 2017-07-04
 * */
public class CoderHelper {
	
	public static void close(Closeable closable) {
		if (closable != null) {
			try {
				closable.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
}
