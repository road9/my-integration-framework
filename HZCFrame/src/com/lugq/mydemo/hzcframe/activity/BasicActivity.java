package com.lugq.mydemo.hzcframe.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
/**
 * BasicActivity.
 * @ClassName: BasicActivity
 * @author lugq
 * @date 2014年9月5日 下午4:21:06
 *
 */
public class BasicActivity extends Activity {
	
	protected Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this.getApplicationContext();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
}
