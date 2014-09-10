package com.lugq.mydemo.hzcframe.util;

import android.util.Log;

import com.lugq.mydemo.hzcframe.LotteryConfig;

/**
 * 用于打统一日志，开放平台Android SDK调试日志以 TAG = "OpenSdkLog" 的形式打出
 * 在LotteryConfig里通过开关B_LOG_OPEN开启日志和关闭日志，应用发布前一定要关闭日志
 * 提供应用程序的性能。
 * @ClassName: Logger
 * @author lugq
 * @date 2014年9月10日 下午3:27:35
 *
 */
public class Logger {
	public static final String TAG = "OpenSdkLog";
	
	public static void inf(String msg) {
		if (!LotteryConfig.B_LOG_OPEN)
			return;
		Log.i(TAG, msg);
	}
	
	public static void inf(String tag, String msg) {
		if (!LotteryConfig.B_LOG_OPEN)
			return;
		Log.i(tag, msg);
	}
}
