package com.lugq.mydemo.hzcframe.netbasic;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * apn utils.
 * @ClassName: APNUtils
 * @author lugq
 * @date 2014��9��11�� ����2:47:41
 *
 */
public class APNUtils {

    private static final String TAG = APNUtils.class.getSimpleName();
    /**
     * cmwap
     */
    public static final int MPROXYTYPE_CMWAP = 1;
    /**
     * wifi
     */
    public static final int MPROXYTYPE_WIFI = 2;
    /**
     * cmnet
     */
    public static final int MPROXYTYPE_CMNET = 4;
    /**
     * uninet�������б�
     */
    public static final int MPROXYTYPE_UNINET = 8;
    /**
     * uniwap�������б�
     */
    public static final int MPROXYTYPE_UNIWAP = 16;
    /**
     * net��������б�
     */
    public static final int MPROXYTYPE_NET = 32;
    /**
     * wap��������б�
     */
    public static final int MPROXYTYPE_WAP = 64;
    /**
     * Ĭ�Ϸ������б�
     */
    public static final int MPROXYTYPE_DEFAULT = 128;
    /**
     * cmda net
     */
    public static final int MPROXYTYPE_CTNET = 256;
    /**
     * cmda wap
     */
    public static final int MPROXYTYPE_CTWAP = 512;

    public static final String ANP_NAME_WIFI = "wifi"; // �й��ƶ�wap APN����
    public static final String ANP_NAME_CMWAP = "cmwap"; // �й��ƶ�wap APN����
    public static final String ANP_NAME_CMNET = "cmnet"; // �й��ƶ�net APN����
    public static final String ANP_NAME_UNIWAP = "uniwap"; // �й���ͨwap APN����
    public static final String ANP_NAME_UNINET = "uninet"; // �й���ͨnet APN����
    public static final String ANP_NAME_WAP = "wap"; // �й�����wap APN����
    public static final String ANP_NAME_NET = "net"; // �й�����net APN����
    public static final String ANP_NAME_CTWAP = "�й�����ctwap�������б�"; // wap APN����
    public static final String ANP_NAME_CTNET = "�й�����ctnet�������б�"; // net APN����
    public static final String ANP_NAME_NONE = "none"; // net APN����

    // apn��ַ
    private static Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");

    // apn��������
    public static final String APN_PROP_APN = "apn";
    // apn���Դ���
    public static final String APN_PROP_PROXY = "proxy";
    // apn���Զ˿�
    public static final String APN_PROP_PORT = "port";

    public static final byte APNTYPE_NONE = 0;// δ֪����
    public static final byte APNTYPE_CMNET = 1;// cmnet
    public static final byte APNTYPE_CMWAP = 2;// cmwap
    public static final byte APNTYPE_WIFI = 3;// WiFi
    public static final byte APNTYPE_UNINET = 4;// uninet
    public static final byte APNTYPE_UNIWAP = 5;// uniwap
    public static final byte APNTYPE_NET = 6;// net������
    public static final byte APNTYPE_WAP = 7;// wap������
    public static final byte APNTYPE_CTNET = 8; // ctnet
    public static final byte APNTYPE_CTWAP = 9; // ctwap

    // jce���������
    public static final int JCE_APNTYPE_UNKNOWN = 0;
    public static final int JCE_APNTYPE_DEFAULT = 1;
    public static final int JCE_APNTYPE_CMNET = 2;
    public static final int JCE_APNTYPE_CMWAP = 4;
    public static final int JCE_APNTYPE_WIFI = 8;
    public static final int JCE_APNTYPE_UNINET = 16;
    public static final int JCE_APNTYPE_UNIWAP = 32;
    public static final int JCE_APNTYPE_NET = 64;
    public static final int JCE_APNTYPE_WAP = 128;
    public static final int JCE_APNTYPE_CTWAP = 512;
    public static final int JCE_APNTYPE_CTNET = 256;

    /**
     * ��ȡϵͳAPN����˿�
     * 
     * @param context
     * @return
     */
    public static int getApnPortInt(Context context) {
        Cursor c = context.getContentResolver().query(PREFERRED_APN_URI, null, null, null, null);
        c.moveToFirst();
        if (c.isAfterLast()) {
            return -1;
        }
        return c.getInt(c.getColumnIndex(APN_PROP_PORT));
    }

    /**
     * ��ȡϵͳAPN����IP
     * 
     * @param context
     * @return
     */
    public static String getApnProxy(Context context) {
        Cursor c = context.getContentResolver().query(PREFERRED_APN_URI, null, null, null, null);
        c.moveToFirst();
        if (c.isAfterLast()) {
            return null;
        }
        return c.getString(c.getColumnIndex(APN_PROP_PROXY));
    }

    /**
     * �Ƿ������ش���
     * 
     * @param context
     * @return
     */
    public static boolean hasProxy(Context context) {
        int netType = getMProxyType(context);
        if (netType == MPROXYTYPE_CMWAP || netType == MPROXYTYPE_UNIWAP || netType == MPROXYTYPE_WAP ||
            netType == MPROXYTYPE_CTWAP) {
            return true;
        }
        return false;
    }

    /**
     * ��ȡ�Զ��嵱ǰ��������
     * 
     * @param act ��ǰ�Activity
     * @return �������� -1��ʾδ֪����������, ��ȷ���ͣ� MPROXYTYPE_WIFI | MPROXYTYPE_CMWAP | MPROXYTYPE_CMNET
     */
    public static int getMProxyType(Context act) {
        try {
            ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null)
                return MPROXYTYPE_DEFAULT;
            String typeName = info.getTypeName();
            if (typeName.toUpperCase().equals("WIFI")) { // wifi����
                return MPROXYTYPE_WIFI;
            }
            else {
                String extraInfo = info.getExtraInfo().toLowerCase();
                if (extraInfo.startsWith("cmwap")) { // cmwap
                    return MPROXYTYPE_CMWAP;
                }
                else if (extraInfo.startsWith("cmnet") || extraInfo.startsWith("epc.tmobile.com")) { // cmnet
                    return MPROXYTYPE_CMNET;
                }
                else if (extraInfo.startsWith("uniwap")) {
                    return MPROXYTYPE_UNIWAP;
                }
                else if (extraInfo.startsWith("uninet")) {
                    return MPROXYTYPE_UNINET;
                }
                else if (extraInfo.startsWith("wap")) {
                    return MPROXYTYPE_WAP;
                }
                else if (extraInfo.startsWith("net")) {
                    return MPROXYTYPE_NET;
                }
                else if (extraInfo.startsWith("#777")) { // cdma
                    String proxy = getApnProxy(act);
                    if (proxy != null && proxy.length() > 0) {
                        return MPROXYTYPE_CTWAP;
                    }
                    else {
                        return MPROXYTYPE_CTNET;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return MPROXYTYPE_DEFAULT;
    }

    /**
     * ��ȡ��������
     * 
     * @param act
     * @return
     */
    public static String getNetType(Context act) {
        try {
            ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null)
                return "unknown";
            String typeName = info.getTypeName();
            if (typeName.toUpperCase().equals("WIFI")) { // wifi����
                return ANP_NAME_WIFI;
            }
            else {
                String extraInfo = info.getExtraInfo().toLowerCase();
                if (extraInfo.startsWith(ANP_NAME_CMWAP)) { // cmwap
                    return ANP_NAME_CMWAP;
                }
                else if (extraInfo.startsWith(ANP_NAME_CMNET) || extraInfo.startsWith("epc.tmobile.com")) { // cmnet
                    return ANP_NAME_CMNET;
                }
                else if (extraInfo.startsWith(ANP_NAME_UNIWAP)) {
                    return ANP_NAME_UNIWAP;
                }
                else if (extraInfo.startsWith(ANP_NAME_UNINET)) {
                    return ANP_NAME_UNINET;
                }
                else if (extraInfo.startsWith(ANP_NAME_WAP)) {
                    return ANP_NAME_WAP;
                }
                else if (extraInfo.startsWith(ANP_NAME_NET)) {
                    return ANP_NAME_NET;
                }
                else if (extraInfo.startsWith("#777")) { // cdma
                    String proxy = getApnProxy(act);
                    if (proxy != null && proxy.length() > 0) {
                        return "cmda_net";
                    }
                    else {
                        return "cmda_wap";
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown";
    }

}	
