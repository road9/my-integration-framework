package com.lugq.mydemo.hzcframe.activity;

import android.os.Bundle;
import android.os.Message;

import com.lugq.mydemo.hzcframe.R;
import com.lugq.mydemo.hzcframe.util.HttpConnectionUtil;

/**
 * lunch application.
 * @ClassName: StartUp
 * @author lugq
 * @date 2014��9��5�� ����3:00:32
 *
 */
public class StartUp extends BasicActivity {
	
	private MyHandler myHandler;
	
	private static class MyHandler extends WeakReferenceHandler<StartUp> {

		public MyHandler(StartUp reference) {
			super(reference);
		}

		@Override
		public void handleMessage(StartUp reference, Message msg) {
			
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
		
	}

	private void setupViews() {
		
	}

	
	private void init() {
		if (HttpConnectionUtil.isNetworkAvailable(mContext)) {
			
		}
	}
	
}


/*
 *         if (HttpConnectUtil.isNetworkAvailable(mContext)) {
            mInitControl.checkConnection();
        }

        mInitControl.createShortCut();

        // �������á���ȡ�����Ⱥ�ʱ�����ӳٴ���
        mHandler.sendEmptyMessage(HALL_INIT);
 */
















