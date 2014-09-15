package com.lugq.mydemo.hzcframe;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.lugq.mydemo.hzcframe.netbasic.AsyncConnectionBasic;
import com.lugq.mydemo.hzcframe.netbasic.ConnectionBasic;
import com.lugq.mydemo.hzcframe.netbasic.JsonAnalyse;
import com.lugq.mydemo.hzcframe.requestInf.ServerTimeRequestInf;
import com.lugq.mydemo.hzcframe.util.ActionUtil;
import com.lugq.mydemo.hzcframe.util.TimeUtils;

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
			isReachable = isHostReachable(context);
			
			if (isReachable) {
				break;
			} else {
				presentServer = getNext(context, presentServer);
			}
		}
		
		if (isReachable == false) {
			presentServer = 0;
			API_BASE_ENDPOINT = getHost(context, presentServer);
		}
		
		if (lastServer != presentServer) {
			Editor databaseData = ActionUtil.getEditor(context);
			databaseData.putInt(SERVERDATABASE, presentServer);
			databaseData.commit();
		}
	}
	
	/**
	 * get http url.
	 * @param context
	 * @return
	 */
	public static final String getHTTPURL(Context context) {
		if (API_BASE_ENDPOINT == null) {
			new Domain().checkHostReachable(context);
		}
		return API_BASE_ENDPOINT;
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
		ServerTimeRequestInf requestInf = new ServerTimeRequestInf();
		ConnectionBasic connect = new ConnectionBasic(context);
		connect.timeout = 3000;
		String[] json = connect.requestGet(requestInf.getUrl(context));
		if (json != null && json.length == 2) {
			if (json[0].equals(String.valueOf(AsyncConnectionBasic.GET_SUCCESS_STATUS))) {
				JsonAnalyse ja = new JsonAnalyse();
				String time = ja.getData(json[1], "datetime");
				if (time != null) {
					LotteryApp appState = (LotteryApp)((Activity) context).getApplication();
					appState.setTime(TimeUtils.convertDate(time, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmm"));
					return true;
				}
			}
		}
		return false;
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



















