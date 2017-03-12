package com.sdudoc.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DocUtil {

	/** 格式化时间 */
	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String formateDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 检查输入的对象是否为空
	 * @param objects
	 * @return 如果对象全不为空返回false，否则返回true
	 */
	public static boolean checkNull(Object... objects) {
		for (Object object : objects) {
			if (object == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查输入的对象是否相等，检查方式为：第一个和第二个，第三个和第四个...
	 * @param objects
	 * @return 如果都相等返回true，否则返回false
	 */
	public static boolean checkEquals(Object... objects) {
		if (objects == null || objects.length == 0 || objects.length % 2 == 1)
			return false;
		for (int i = 0; i < objects.length; i += 2) {
			if (!objects[i].equals(objects[i + 1]))
				return false;
		}
		return true;
	}

	/**
	 * 检查输入的对象是否不相等，检查方式为：第一个和第二个，第三个和第四个...
	 * @param objects
	 * @return 如果全不相等返回true，否则返回false
	 */
	public static boolean checkNotEquals(Object... objects) {
		if (objects == null || objects.length == 0 || objects.length % 2 == 1)
			return false;
		for (int i = 0; i < objects.length; i += 2) {
			if (objects[i].equals(objects[i + 1]))
				return false;
		}
		return true;
	}

}
