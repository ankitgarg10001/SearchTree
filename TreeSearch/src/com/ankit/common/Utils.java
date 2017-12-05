package com.ankit.common;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	// private static final Logger log =
	// LogManager.getLogger(Utils.class);

	public static String toString(Object object) {
		if (object != null) {
			return object.toString();
		}
		return "";
	}

	public static final Date toTimestamp(String str) {
		if (StringUtils.isNotBlank(str)) {
			try {
				return Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		if (Class.class.equals(method.getReturnType()))
			return false;
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set"))
			return false;
		if (method.getParameterTypes().length != 1)
			return false;
		return true;
	}

}