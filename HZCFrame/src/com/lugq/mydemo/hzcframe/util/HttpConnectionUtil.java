package com.lugq.mydemo.hzcframe.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Network connection utility class.
 * 
 * @ClassName: HttpConnectionUtil
 * @author lugq
 * @date 2014��9��5�� ����2:44:37
 * 
 */
public class HttpConnectionUtil {

	/**
	 * generate query string.
	 * @param params
	 * @return
	 */
	public static String generateQueryString(Map params) {
        if (params == null)
            return "";
        String aQueryParam = "";
        if (params.size() > 0) {
            Set aKeySet = params.keySet();
            Iterator aKeyIterator = aKeySet.iterator();
            while (aKeyIterator.hasNext()) {
                String aParamName = (String) aKeyIterator.next();
                String aParamValue = (String) params.get(aParamName);
                aQueryParam += aParamName + "=" + aParamValue + "&";
            }
            aQueryParam = aQueryParam.substring(0, aQueryParam.length() - 1);
        }
        return aQueryParam;
    }
	
	/**
	 * whether netword is valid.
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
    public static String encodeParameter(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (Exception ex) {
            return "";
        }
    }

    public static String decodeParameter(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex) {
        }
        return null;
    }
	
}
