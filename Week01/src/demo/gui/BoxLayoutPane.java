package demo.gui;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class BoxLayoutPane extends JPanel {
	public BoxLayoutPane() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		Box row = Box.createHorizontalBox();
		
		for (int i=0; i<4; i++) {
			JButton b = new JButton("B" + i);
			b.setFont(new Font("serif", Font.BOLD, 12 + i * 2));
			row.add(b);
		}
		
		this.add(row, BorderLayout.NORTH);
		JPanel col = new JPanel();
		col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
		col.setBorder(new TitledBorder(new EtchedBorder(), "Column"));
		
		for (int i=0; i<4; i++) {
			JButton b = new JButton("Button " + i);
			b.setFont(new Font("sanserif", Font.BOLD, 10 + i * 2));
			col.add(b);
		}
		this.add(col, BorderLayout.EAST);
		
		Box buttonbox = Box.createHorizontalBox();
		buttonbox.add(Box.createHorizontalGlue());
		buttonbox.add(new JButton("Okay"));
		buttonbox.add(Box.createHorizontalGlue());
		buttonbox.add(new JButton("Cancel"));
		buttonbox.add(Box.createHorizontalGlue());
		buttonbox.add(new JButton("Help"));
		buttonbox.add(Box.createHorizontalGlue());
		this.add(buttonbox, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("This component has 12-pixel margins on left and top"
				+ " and has 72-pixel margins on right and bottom.");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		Box fixedcol = Box.createVerticalBox();
		fixedcol.add(Box.createVerticalStrut(12));
		fixedcol.add(textArea);
		fixedcol.add(Box.createVerticalStrut(72));
		
		Box fixedrow = Box.createHorizontalBox();
		fixedrow.add(Box.createHorizontalStrut(12));
		fixedrow.add(fixedcol);
		fixedrow.add(Box.createHorizontalStrut(72));
		this.add(fixedrow, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				System.exit(0);
			}
		});
		
		f.setTitle("Layout Manager");
		f.setContentPane(new BoxLayoutPane());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
