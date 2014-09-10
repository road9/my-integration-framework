package com.lugq.mydemo.hzcframe;

import com.lugq.mydemo.hzcframe.util.ActionUtil;

import android.content.Context;

/**
 * about domain.
 * @ClassName: Domain
 * @author lugq
 * @date 2014年9月10日 上午10:36:00
 *
 */
public class Domain {
	
	private static final String SERVERDATABASE = "serverNumber";

	/**
	 * official server domain name.
	 */
	private static final String[] RELEASE_ROOT_DOMAIN = {"lottery.haozan88.com", "lottery.jackypeng.com", "lottery.jackypeng.com", "61.129.47.83"};//TODO
	
	/**
	 * test server domain name.
	 */
	private static final String[] TEST_ROOT_DOMAIN = {"192.168.1.113", "192.168.1.113", "192.168.1.113"};//TODO
	
	private static final String SCHEME_HTTP = "http://";//TODO
	private static final String SCHEME_SUFFIX = ":8080/BukeServ/";//TODO
	private static String API_BASE_ENDPOINT = null;

	/**
	 * 
	 */
	public void checkHostReachable(Context context) {
		int lastServer = ActionUtil.getSharedPreferences(context).getInt(SERVERDATABASE, 0);
		boolean isReachable = false;
		int length = getDomainLength(context);
		if (lastServer >= length) {
			lastServer = 0;
		}
		int presentServer = lastServer;
		for (int i = 0; i < length; i++) {
			API_BASE_ENDPOINT = getHost(context, presentServer);
			isReachable = is
		}
	}
	
	/**
	 * get the domain name length.
	 * @param context
	 * @return
	 */
	private int getDomainLength(Context context) {
		int length;
		if(LotteryConfig.B_TEST_ENVIRONMENT) {
			length = TEST_ROOT_DOMAIN.length;
		} else {
			length = RELEASE_ROOT_DOMAIN.length;
		}
		return length;
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	private boolean isHostReachable(Context context) {
		
	}

	/**
	 * get reachable endpoint.such as : http://lottery.haozan88.com:8080/BukeServ/
	 * @param context
	 * @param index
	 * @return
	 */
	private String getHost(Context context, int index) {
		String reachableEndpoint;
		if (LotteryConfig.B_TEST_ENVIRONMENT) {
			reachableEndpoint = SCHEME_HTTP + TEST_ROOT_DOMAIN[index] + SCHEME_SUFFIX;
		} else {
			reachableEndpoint = SCHEME_HTTP + RELEASE_ROOT_DOMAIN[index] + SCHEME_SUFFIX;
		}
		return reachableEndpoint;
	}
	
	/**
	 * 
	 * @param context
	 * @param indexOrg
	 * @return
	 */
	private int getNext(Context context, int indexOrg) {
		int index;
		if (LotteryConfig.B_TEST_ENVIRONMENT) {
			index = getTestEndpoint(indexOrg);
		} else {
			index = getOfficialEndpoint(indexOrg);
		}
		return index;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	private int getTestEndpoint(int index) {
		if (index < TEST_ROOT_DOMAIN.length -1) {
			index++;
		} else {
			index = 0;
		}
		return index;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	private int getOfficialEndpoint(int index) {
		if (index < RELEASE_ROOT_DOMAIN.length - 1) {
			index ++;
		} else {
			index = 0;
		}
		return index;
	}
	
}



















