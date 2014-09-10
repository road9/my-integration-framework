package com.lugq.mydemo.hzcframe.task;

import android.content.Context;
import android.os.Handler;

import com.lugq.mydemo.hzcframe.taskbasic.Task;

/**
 * 
 * @ClassName: CheckConnectionTask
 * @author lugq
 * @date 2014年9月10日 上午10:33:37
 *
 */
public class CheckConnectionTask extends Task {
	
	private Context mContext;
	private Handler mHandler;
	
	public CheckConnectionTask(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}

	@Override
	public void runTask() {
		
	}
	
	
}
