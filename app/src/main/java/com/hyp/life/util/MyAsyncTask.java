package com.hyp.life.util;

import android.os.Handler;

public abstract class MyAsyncTask {
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			postTask();
		};
	};
	/**
	 * 在子线程之前执行的方法
	 */
	public abstract void preTask();
	/**
	 * 在子线程之中执行的方法
	 */
	public abstract void doInBack();
	/**
	 * 在子线程之后执行的方法
	 */
	public abstract void postTask();
	/**
	 * 执行的方法
	 */
	public void execute(){
		preTask();
		new Thread(){
			public void run() {
				doInBack();
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
}
