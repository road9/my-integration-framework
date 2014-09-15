package com.lugq.mydemo.hzcframe;

import android.app.Application;
/**
 * 
 * @ClassName: LotteryApp
 * @author lugq
 * @date 2014��9��10�� ����5:14:52
 *
 */
public class LotteryApp extends Application {
	
	// the sessionid from server.
	private String sessionid;
	
	// the server time the user first open the software.
	private String time;

	/**
	 * the sessionid from server.
	 * @return
	 */
	public String getSessionid() {
		return sessionid;
	}

	/**
	 * the sessionid from server.
	 * @param sessionid
	 */
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	/**
	 * the server time the user first open the software.
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * the server time the user first open the software.
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
}
