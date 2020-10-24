package com.jetx.display;

import javax.swing.JFrame;

public class Display extends JFrame {

	/**
	 * To track any changes of the class
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private int width, height;

	public Display(String title, int width, int height) {
		this.title = title;

		this.width = width;
		this.height = height;

		initFrame();
	}

	private void initFrame() {

		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
