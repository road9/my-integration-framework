package com.lugq.mydemo.hzcframe.taskbasic;
/**
 * Thread operation.
 * @ClassName: Task
 * @author lugq
 * @date 2014��9��9�� ����5:36:25
 *
 */
public abstract class Task implements Runnable {

	@Override
	public void run() {
		try {
			runTask();
		} catch (Exception e) {
			
		}
	}
	
	public abstract void runTask();
	
}
