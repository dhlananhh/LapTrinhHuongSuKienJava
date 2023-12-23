package demo.gui;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class DemoGridBagConstraints extends JPanel {
	protected void makeButton (String name, GridBagLayout gridBag, GridBagConstraints c) {
		JButton button = new JButton(name);
		gridBag.setConstraints(button, c);
		add(button);
	}
	
	public DemoGridBagConstraints() {
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setLayout(gridBag);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		makeButton("Button1", gridBag, c);
		makeButton("Button2", gridBag, c);
		makeButton("Button3", gridBag, c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		makeButton("Button4", gridBag, c);
		
		c.weightx = 0.0;
		makeButton("Button5", gridBag, c);
		
		c.gridwidth = GridBagConstraints.RELATIVE;
		makeButton("Button6", gridBag, c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		makeButton("Button7", gridBag, c);
		
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weighty = 1.0;
		makeButton("Button8", gridBag, c);
		
		c.weighty = 0.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridwidth = 1;
		makeButton("Button9", gridBag, c);
		makeButton("Button10", gridBag, c);
		setSize(500, 300);
	}
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame("GridBag Layout");
		DemoGridBagConstraints ex = new DemoGridBagConstraints();
		f.add(ex);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing (WindowEvent e) {
				System.exit(0);
			}
		});
		
		f.pack();
		f.setSize(f.getPreferredSize());
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
