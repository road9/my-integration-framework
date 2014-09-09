package com.lugq.mydemo.hzcframe.activity;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;

/**
 * to prevent memory leak.
 * @ClassName: WeakReferenceHandler
 * @author lugq
 * @date 2014年9月5日 下午5:02:32
 *
 */
public abstract class WeakReferenceHandler<T> extends Handler {
	
	private WeakReference<T> mReference;
	
	public WeakReferenceHandler (T reference) {
		mReference = new WeakReference<T>(reference);
	}
	
	@Override
	public void handleMessage(Message msg) {
		if(mReference.get() == null)
			return;
		handleMessage(mReference.get(), msg);
	}
	
	public abstract void handleMessage(T reference, Message msg);
}