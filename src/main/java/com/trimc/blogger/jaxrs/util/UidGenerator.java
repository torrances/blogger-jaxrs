package com.trimc.blogger.jaxrs.util;

import java.util.Random;

public final class UidGenerator {

	public static final long id() {
		long x = 0l;
		long y = Long.MAX_VALUE;
		Random r = new Random();
		long number = x + ((long) (r.nextDouble() * (y - x)));
		return number + System.currentTimeMillis();
	}
}
