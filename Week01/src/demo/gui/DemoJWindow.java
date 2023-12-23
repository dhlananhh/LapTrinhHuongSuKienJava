package demo.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DemoJWindow {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JLabel msgLabel;
	private JPanel controlPanel;
	
	public DemoJWindow() {
		prepareGUI();	
	}
	
	public static void main(String[] args) {
		DemoJWindow DemoJWindowExample = new DemoJWindow();
	}
	
	private void prepareGUI() {
		mainFrame = new JFrame("Demo JWindow");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		
		statusLabel.setSize(350, 100);
		
		msgLabel = new JLabel("Welcome to SWING Tutorial", JLabel.CENTER);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}
}
