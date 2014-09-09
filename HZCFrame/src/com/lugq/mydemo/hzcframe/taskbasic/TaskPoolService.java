package com.lugq.mydemo.hzcframe.taskbasic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * create thread.
 * @ClassName: TaskPoolService
 * @author lugq
 * @date 2014年9月9日 下午4:47:56
 *
 */
public class TaskPoolService {
	public static final int MAX_CONNECTIONS = 5;

	private static TaskPoolService service;
	private ExecutorService mThreadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);

	public static TaskPoolService getInstance() {
		if (service == null) {
		    service = new TaskPoolService();
		}
		return service;
	}

	public void requestService(Runnable runnable) {
		mThreadPool.submit(runnable);
	}
}
