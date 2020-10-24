package com.jetx;

import javax.swing.SwingUtilities;

import com.jetx.display.Display;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new Display("A Concurrency Test Frame", 600, 400);
		});

		Thread[] runningThreads = new Thread[5];

		int result = Thread.enumerate(runningThreads);

		System.out.println("Result: " + result);

		for (Thread th : runningThreads) {

			if (th != null) {
				System.out.println("Thread Id: " + th.getId());
				System.out.println("Thread Name: " + th.getName());
				System.out.println("Thread Priority: " + th.getPriority());
				System.out.println("Thread State: " + th.getState());
				System.out.println();
			}

		}
	}

}
