package com.lugq.mydemo.hzcframe.netbasic;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;

import com.lugq.mydemo.hzcframe.Domain;
import com.lugq.mydemo.hzcframe.LotteryApp;
import com.lugq.mydemo.hzcframe.security.EncryptUtil;
import com.lugq.mydemo.hzcframe.util.HttpConnectionUtil;
import com.lugq.mydemo.hzcframe.util.Logger;
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
	
	//interface prefix.
	private static final String URL_PREFIX = "";
	
	// get and post.
	private static final int GET_METHOD_INDEX = 0;
	private static final int POST_METHOD_INDEX = 1;
	
	private int mRequestMethod;	
	// server interface name. such as : services/gateway.
	private String mUrlMethod;
	// holl server interface prefix, maybe add sessionid.
	private String mUrlPrefix;
	private String mUrl;
	private HashMap<String, String> mRequestParams;
	private String mFinalParams;
	
	public HttpConnection(Context context) {
		this.mContext = context;
		appState = (LotteryApp) ((Activity) mContext).getApplicationContext();
	}
	
	public void getServiceGateway(Boolean isWithSession, HashMap<String, String> params) {
		create(isWithSession, GET_METHOD_INDEX, URL_PREFIX, params);
	}
	
	public String getmUrl() {
		if (mUrl == null) {
			Logger.inf("请先生成url");
		}
		return mUrl;
	}
	
	private void create(boolean isWithSession, int method, String urlMethod, HashMap<String, String> requestParams) {
		this.mRequestMethod = method;
		this.mUrlMethod = urlMethod;
		this.mRequestParams = requestParams;
		
		appendTimeSession(requestParams);
		
		if (isWithSession) {
			String sessionId = appState.getSessionid();
			mUrlPrefix = mUrlMethod + ";jsessionid=" + sessionId;
		} else {
			mUrlPrefix = mUrlMethod;
		}
		
		httpAsynSend();
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
	
	private boolean httpAsynSend() {
		mFinalParams = HttpConnectionUtil.generateQueryString(mRequestParams);
		if (mRequestMethod == GET_METHOD_INDEX) {
			mUrl = Domain.getHTTPURL(mContext) + mUrlPrefix + "?" + mFinalParams;
		} else if (mRequestMethod == POST_METHOD_INDEX) {
			mUrl = Domain.getHTTPURL(mContext) + mUrlPrefix;
		}
		return true;
	}
	
}





















