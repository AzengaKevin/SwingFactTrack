package com.jetx.display;

import java.util.concurrent.ExecutionException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.jetx.model.DoMath;

public class Display extends JFrame {

	/**
	 * To track any changes of the class
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private int width, height;

	private JPanel mainPanel;
	private JButton calculateButton, launchPaneButton;
	private JLabel displayLabel;
	private JTextField inputField;

	public Display(String title, int width, int height) {
		this.title = title;

		this.width = width;
		this.height = height;

		initFrame();

		initComponents();
	}

	private void initComponents() {

		mainPanel = new JPanel();
		setContentPane(mainPanel);

		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(32));

		// The Input
		inputField = new JTextField("", 15);
		mainBox.add(inputField);
		mainBox.setAlignmentX(CENTER_ALIGNMENT);
		mainBox.add(Box.createVerticalStrut(20));

		// The Display
		displayLabel = new JLabel("The Display");
		displayLabel.setAlignmentX(CENTER_ALIGNMENT);
		mainBox.add(displayLabel);
		mainBox.add(Box.createVerticalStrut(20));

		JPanel buttonPanel = new JPanel();

		calculateButton = new JButton("Calculate Fibonacci");

		calculateButton.addActionListener(btn -> {

			// Get the number
			String sNumber = String.valueOf(inputField.getText());

			// Check if empty
			if (!sNumber.isBlank()) {

				try {

					fibbonacciWorker.execute();

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "The input must be a number", "Number Validation",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(this, "Integer number required in the input", "Number Validation",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		buttonPanel.add(calculateButton);

		launchPaneButton = new JButton("Launch Pane");
		launchPaneButton.addActionListener(btn -> {
			JOptionPane.showMessageDialog(this, "Just checking whther UI has frozen", "Concurrency Check",
					JOptionPane.INFORMATION_MESSAGE);
		});
		buttonPanel.add(launchPaneButton);
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainBox.add(buttonPanel);
		mainBox.add(Box.createVerticalStrut(32));

		mainPanel.add(mainBox);

	}

	private void initFrame() {

		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private SwingWorker<Integer, Void> fibbonacciWorker = new SwingWorker<>() {

		@Override
		protected Integer doInBackground() throws Exception {

			int number = Integer.parseInt(inputField.getText().trim());

			int fibonacci = DoMath.fib(number);

			return fibonacci;
		}

		@Override
		protected void done() {

			try {
				displayLabel.setText(String.format("Fibonacci(%s) = %d", inputField.getText().trim(), get()));
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};

}
