package com.lugq.mydemo.hzcframe.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Network connection utility class.
 * 
 * @ClassName: HttpConnectionUtil
 * @author lugq
 * @date 2014年9月5日 下午2:44:37
 * 
 */
public class HttpConnectionUtil {

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
