package com.lugq.mydemo.hzcframe;

import android.app.Application;
/**
 * 
 * @ClassName: LotteryApp
 * @author lugq
 * @date 2014年9月10日 下午5:14:52
 *
 */
public class LotteryApp extends Application {
	
	/**
	 * the server time the user first open the software.
	 */
	private String time;

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
