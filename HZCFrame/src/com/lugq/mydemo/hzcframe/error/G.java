package com.lugq.mydemo.hzcframe.error;
/**
 * global variable.
 * @ClassName: G
 * @author lugq
 * @date 2014��9��5�� ����3:30:46
 *
 */
public class G {
	
    // This must be set by the application - it used to automatically
    // transmit exceptions to the trace server
	public static String FILES_PATH = null;
	public static String APP_VERSION = "unknown";
	public static String APP_PACKAGE = "unknown";
	public static String PHONE_MODEL = "unknown";
	public static String CLASS_NAME = "unknown";
	
    // Where are the stack traces posted?
    public static String URL = "http://trace.nullwire.com/collect/";
    public static String TraceVersion = "0.3.0";

    // ���󱨸����sdcard��Ŀ¼��
    public static String PATH_NAME = "lugq_bug_notes";
}
