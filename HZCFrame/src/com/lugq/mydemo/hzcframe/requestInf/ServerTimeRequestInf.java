package com.lugq.mydemo.hzcframe.requestInf;

import java.util.HashMap;

import com.lugq.mydemo.hzcframe.netbasic.HttpConnection;
import com.lugq.mydemo.hzcframe.util.LotteryUtils;

import android.content.Context;

/**
 * 
 * @ClassName: ServerTimeRequestInf
 * @author lugq
 * @date 2014年9月10日 下午2:54:15
 *
 */
public class ServerTimeRequestInf {
	
	public HashMap<String, String> initHashMap(Context context) {
		HashMap<String, String> parameter = new HashMap<>();
		parameter.put("service", "get_time");
		parameter.put("pid", LotteryUtils.getPid(context));
		return parameter;
	}
	
	public String getUrl(Context context) {
		ServerTimeRequestInf requestInf = new ServerTimeRequestInf();
		HttpConnection connection = new HttpConnection(context);
		connection.
	}
}
