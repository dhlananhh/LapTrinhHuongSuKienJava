package tuan01;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LyQuocMinh_21105601_bai2 extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8344668651970896155L;
	JTextField input;
	JTextArea output;
	JButton btnGenerate;
	public LyQuocMinh_21105601_bai2() {
		setTitle("Primes");
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		// TODO Auto-generated constructor stub
		b1.add(Box.createRigidArea(new Dimension(50,50)));
		b1.add(input = new JTextField(20));
		b1.add(btnGenerate = new JButton("Generate"));
		b1.add(Box.createRigidArea(new Dimension(50,50)));
		b2.add(output = new JTextArea());
		btnGenerate.addActionListener(this);
		this.add(b1,BorderLayout.NORTH);
		this.add(Box.createRigidArea(new Dimension(50,50)),BorderLayout.EAST);
		
		this.add(b2,BorderLayout.CENTER);
		this.add(Box.createRigidArea(new Dimension(50,50)),BorderLayout.WEST);
		this.add(Box.createRigidArea(new Dimension(50,50)),BorderLayout.SOUTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		int n;
		if (obj.equals(btnGenerate)) {
			try {
				n = Integer.parseUnsignedInt(input.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,"Vui long nhap lai");
				input.selectAll();
				input.requestFocus();
				return;
			}
			int step = 1;
			String txt= "";
			for (int i = 2; step <=n;i++) {
				if (checkPrime(i)) {
					txt += String.format("%d\n", i);
					step++;
				}
			}
			output.setText(txt);
		}
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new LyQuocMinh_21105601_bai2().setVisible(true);
	}
	private boolean checkPrime(int a) {
		for (int i = 2; i*i <=a;i++) {
			if (a%i==0) return false;
		}
		return true;
		
	}
}
