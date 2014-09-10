package com.lugq.mydemo.hzcframe.netbasic;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;

import com.lugq.mydemo.hzcframe.LotteryApp;
import com.lugq.mydemo.hzcframe.security.EncryptUtil;
import com.lugq.mydemo.hzcframe.util.LotteryUtils;
import com.lugq.mydemo.hzcframe.util.TimeUtils;

/**
 * connect network.
 * @ClassName: HttpConnection
 * @author lugq
 * @date 2014年9月10日 下午3:42:27
 *
 */
public class HttpConnection {
	
	private Context mContext;
	
	private LotteryApp appState;
	
	private static final String URL_PREFIX = "";
	
	private int mRequestMethod;
	
	private String mUrlMethod;
	
	private HashMap<String, String> mRequestParams;
	
	private static final int GET_METHOD_INDEX = 0;
	
	private static final int POST_METHOD_INDEX = 1;
	
	public HttpConnection(Context context) {
		this.mContext = context;
		appState = (LotteryApp) ((Activity) mContext).getApplicationContext();
	}
	
	public void getServiceGateway(Boolean isWithSession, HashMap<String, String> params) {
		
	}
	
	private void create(boolean isWithSession, int method, String urlMethod, HashMap<String, String> requestParams) {
		this.mRequestMethod = method;
		this.mUrlMethod = urlMethod;
		this.mRequestParams = requestParams;
		
		
	}
	
	/**
	 * 
	 * @param requestParams
	 */
	private void appendTimeSession(HashMap<String, String> requestParams) {
		String time = appState.getTime();
		if (time == null) {
			String t = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
			time = TimeUtils.convertDate(t, "yyyy-MM-dd hh:mm:ss", "yyyyMMddHHmm");
		}
		mRequestParams.put("timestamp", time);
		mRequestParams.put("sign", getMD5Sign(time));
	}
	
	/**
	 * 
	 * @param parameter
	 * @return
	 */
	private String getMD5Sign(String parameter) {
		return EncryptUtil.MD5Encrypt(parameter + LotteryUtils.getKey(mContext));
	}
}





















