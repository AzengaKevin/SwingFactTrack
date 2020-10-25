package com.jetx.model;

public class DoMath {

	public static long fib(long number) {

		if (number < 2)
			return number;
		return fib(number - 1) + fib(number - 2);

	}

}