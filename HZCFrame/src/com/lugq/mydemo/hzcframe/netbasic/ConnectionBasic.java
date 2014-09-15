package com.lugq.mydemo.hzcframe.netbasic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BufferedHeader;
import org.apache.http.params.HttpConnectionParams;

import android.content.Context;

/**
 * access network util.提供直接访问服务器接口功能.
 * @ClassName: ConnectionBasic
 * @author lugq
 * @date 2014年9月11日 下午1:48:20
 *
 */
public class ConnectionBasic {
	
	public int timeout = 8000;
	
	private int mRequestMethod;
	private String mUrl;
	private byte[] mPostData;
	
	private Context mContext;
	
	public ConnectionBasic(Context context) {
		this.mContext = context;
	}
	
	/**
	 * get.
	 * @param url
	 * @return
	 */
	public String[] requestGet(String url) {
		create(AsyncConnectionBasic.GET_METHOD_INDEX, url, null);
		return request();
	}
	
	/**
	 * post.
	 * @param url
	 * @param postData
	 * @return
	 */
	public String[] requestPost(String url, byte[] postData) {
		create(AsyncConnectionBasic.POST_METHOD_INDEX, url, postData);
		return request();
	}
	
	private String[] request() {
		try {
			HttpEntity httpEntity = getHttpEntity();
			if (httpEntity != null) 
				return processEntity(httpEntity);
		} catch (Exception e) {
		}
		
		String json[] = {"405", "数据获取失败"};
		return json;
	}
	
	private HttpEntity getHttpEntity() {
		HttpClient mHttpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(mHttpClient.getParams(), timeout);
		HttpConnectionParams.setSoTimeout(mHttpClient.getParams(), timeout);
		
		checkProxySetting(mHttpClient);
		try {
			HttpResponse response = null;
			switch (mRequestMethod) {
				case AsyncConnectionBasic.GET_METHOD_INDEX: {
					HttpGet httpGet = new HttpGet(mUrl);
					response = mHttpClient.execute(httpGet);
					break;					
				}
				
				case AsyncConnectionBasic.POST_METHOD_INDEX: {					
					HttpPost httpPost = new HttpPost(mUrl);
					InputStream instream = new ByteArrayInputStream(mPostData);
					InputStreamEntity inputStreamEntity = new InputStreamEntity(instream, mPostData.length);
					httpPost.setEntity(inputStreamEntity);
					
					response = mHttpClient.execute(httpPost);
					break;
				}
			}
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return response.getEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void create(int method, String url, byte[] postData) {
		this.mRequestMethod = method;
		this.mUrl = url;
		this.mPostData = postData;
	}
	
	private void checkProxySetting(HttpClient httpClient) {
		boolean useProxy = APNUtils.hasProxy(mContext);
		if (useProxy) {
			HttpHost proxy = new HttpHost(APNUtils.getApnProxy(mContext), APNUtils.getApnPortInt(mContext));
			httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		}
	}
	
	/**
	 * get string json.
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private String[] processEntity(HttpEntity entity) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String line, result = "";
		while ((line = br.readLine()) != null)
			result += line;
		
		JsonAnalyse analyse = new JsonAnalyse();
		String json[] = {analyse.getStatus(result), result};
		return json;
	}
	
}
















