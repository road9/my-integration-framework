package com.lugq.mydemo.hzcframe.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import com.lugq.mydemo.hzcframe.LotteryConfig;

/**
 * 
 * @ClassName: LotteryUtils
 * @author lugq
 * @date 2014年9月10日 下午2:56:11
 *
 */
public class LotteryUtils {
	
	/**
	 * get pid.
	 * @param context
	 */
	public static String getPid(Context context) {
		if (LotteryConfig.B_TEST_ENVIRONMENT) {
			return "101001";//TODO modify.
		} else {
			return getMainfestMetaDataInt(context, "PID").toString();
		}
	}
	
	/**
	 * get key.
	 * @param context
	 * @return
	 */
	public static String getKey(Context context) {
		if (LotteryConfig.B_TEST_ENVIRONMENT) {
			return "479gx6B7Yhw18j9f";//TODO
		} else {
			return getMainfestMetaDataString(context, "KEY");
		}
	}
	
	/**
	 * get String value from mainfest meta According to keyName.
	 * @param context
	 * @param keyName
	 * @return
	 */
	private static String getMainfestMetaDataString(Context context, String keyName) {
		return (String) readKey(context, keyName);
	}
	
	/**
	 * get Integer value from mainfest meta according to keyName.
	 * @param context
	 * @param keyName
	 * @return
	 */
	private static Integer getMainfestMetaDataInt(Context context, String keyName) {
		return (Integer) readKey(context, keyName);
	}
	
	/**
	 * 
	 * @param context
	 * @param keyName
	 * @return
	 */
	private static Object readKey(Context context, String keyName) {
		try {
			ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			Bundle bundle = applicationInfo.metaData;
			Object value = bundle.get(keyName);
			return value;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}














