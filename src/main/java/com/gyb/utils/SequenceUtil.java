package com.gyb.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列生成工具类
 * @author Shunzhong.Huang
 *
 */
public class SequenceUtil {
	private static AtomicInteger counter = new AtomicInteger(0);

	/**
	 * 产生序列ID
	 */
	public static long getAtomicCounter() {
		if (counter.get() > 999999) {
			counter.set(1);
		}
		long time = System.currentTimeMillis();
		long returnValue = time * 100 + counter.incrementAndGet();
		return returnValue;
	}

}
