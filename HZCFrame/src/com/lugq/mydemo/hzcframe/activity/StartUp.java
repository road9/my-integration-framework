package com.lugq.mydemo.hzcframe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.lugq.mydemo.hzcframe.MainActivity;
import com.lugq.mydemo.hzcframe.R;
import com.lugq.mydemo.hzcframe.control.ControlMessage;
import com.lugq.mydemo.hzcframe.control.InitControl;
import com.lugq.mydemo.hzcframe.util.HttpConnectionUtil;

/**
 * lunch application.
 * @ClassName: StartUp
 * @author lugq
 * @date 2014年9月5日 下午3:00:32
 *
 */
public class StartUp extends BasicActivity {
	
	private static final int TO_MAIN = 0;
	private  static final int HALL_INIT = 1;
	
	private MyHandler mHandler;
	private InitControl mInitControl;
	
	private static class MyHandler extends WeakReferenceHandler<StartUp> {

		public MyHandler(StartUp reference) {
			super(reference);
		}

		@Override
		public void handleMessage(StartUp reference, Message msg) {
			switch (msg.what) {
			case ControlMessage.FINISH_CHECK_CONNECTION:
				
				break;
			case TO_MAIN:
				reference.gotoMain();
				break;
			case HALL_INIT:
				reference.mHandler.sendEmptyMessageDelayed(TO_MAIN, 1500);
				
				break;
			default:
				break;
			}
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_up);
        initData();
        setupViews();
        init();
	}
	
	private void initData() {
		mHandler = new MyHandler(this);
		mInitControl = new InitControl(this, mHandler);
	}

	private void setupViews() {
		
	}

	
	private void init() {
		if (HttpConnectionUtil.isNetworkAvailable(mContext)) {
			mInitControl.checkConnection();
		}
		mInitControl.createShortCut();
		
		mHandler.sendEmptyMessage(HALL_INIT);
	}
	
	private void gotoMain() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
}


/*
 *         if (HttpConnectUtil.isNetworkAvailable(mContext)) {
            mInitControl.checkConnection();
        }

        mInitControl.createShortCut();

        // 推送设置、读取闪屏等耗时操作延迟处理
        mHandler.sendEmptyMessage(HALL_INIT);
 */
















