package com.lugq.mydemo.hzcframe.util;

import android.util.Log;

import com.lugq.mydemo.hzcframe.LotteryConfig;

/**
 * ���ڴ�ͳһ��־������ƽ̨Android SDK������־�� TAG = "OpenSdkLog" ����ʽ���
 * ��LotteryConfig��ͨ������B_LOG_OPEN������־�͹ر���־��Ӧ�÷���ǰһ��Ҫ�ر���־
 * �ṩӦ�ó�������ܡ�
 * @ClassName: Logger
 * @author lugq
 * @date 2014��9��10�� ����3:27:35
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
