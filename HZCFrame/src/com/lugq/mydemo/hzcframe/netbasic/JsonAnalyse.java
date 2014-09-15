package com.lugq.mydemo.hzcframe.netbasic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Analyse the json data coming from the server.
 * @ClassName: JsonAnalyse
 * @author lugq
 * @date 2014年9月11日 下午4:04:17
 *
 */
public class JsonAnalyse {
	
	/**
	 * get the status of the json data.
	 * @param json
	 * @return
	 */
	public String getStatus(String json) {
		if (json == null) 
			return null;
		String status = "";
		try {
			JSONObject o = new JSONObject(json);
			status = o.getString("status");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * get the main part of the json, name means the name of the main part.
	 * @param json
	 * @param name
	 * @return
	 */
	public String getData(String json, String name) {
		if (json == null) 
			return null;
		String data = "";
		try {
			JSONObject o = new JSONObject(json);
			data = o.getString(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}
	
	/**
	 * get the pointed data of the array.
	 * @param json
	 * @param order
	 * @param name
	 * @return
	 */
    public String getFromArray(String json, int order, String name) {
        JSONArray hallArray;
        try {
            hallArray = new JSONArray(json);
            JSONObject jo = hallArray.getJSONObject(order);
            return jo.getString(name);
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
















