package com.lugq.mydemo.hzcframe.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * jumps tools and get object of Shareperference.
 * @ClassName: ActionUtil
 * @author lugq
 * @date 2014年9月9日 下午3:49:29
 *
 */
public class ActionUtil {
	
	public static SharedPreferences preferences;
	public static Editor databaseData;
	
	/**
	 * get object of SharedPreferences.
	 * @param context
	 * @return
	 */
	public static SharedPreferences getSharedPreferences(Context context) {
		if(preferences == null) {
			Context appContext = context.getApplicationContext();
			preferences = appContext.getSharedPreferences("user", 0);
		}
		return preferences;
	}
	
	/**
	 * get object of Editor.
	 * @param context
	 * @return
	 */
	public static Editor getEditor(Context context) {
		if (databaseData == null) {
			Context appContext = context.getApplicationContext();
			databaseData = getSharedPreferences(appContext).edit();
		}
		return databaseData;
	}
}
