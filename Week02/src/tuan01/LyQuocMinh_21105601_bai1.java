package tuan01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LyQuocMinh_21105601_bai1 extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton buttonGiai, buttonXoaRong, buttonThoat;
	private JTextField txtA, txtB, txtC, txtKq;
	
	public LyQuocMinh_21105601_bai1() {
		setTitle("^-^");
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		createGUI();
	}
	private void createGUI() {
		JPanel title = new JPanel();
		this.add(title, BorderLayout.NORTH);
		title.add(new JLabel("GIẢI PHƯƠNG TRÌNH BẬC HAI"));
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBackground(Color.cyan);
		
		JPanel body = new JPanel();
		this.add(body, BorderLayout.CENTER);
		body.setLayout(null);
		
		JLabel nhapA, nhapB, nhapC, ketQua;
		body.add(nhapA = new JLabel("Nhập a: "));
		int x= 20, y=40, width=100, height=30;
		nhapA.setBounds(x, y, width, height);
		
		body.add(nhapB = new JLabel("Nhập b: "));
		y+=50;
		nhapB.setBounds(x, y, width, height);
		
		body.add(nhapC = new JLabel("Nhập c: "));
		y+=50;
		nhapC.setBounds(x, y, width, height);
		
		body.add(ketQua = new JLabel("kết quả: "));
		y+=50;
		ketQua.setBounds(x, y, width, height);
		
		body.add(txtA = new JTextField());
		x+=100; y=40; width=300;
		txtA.setBounds(x, y, width, height);
		
		body.add(txtB = new JTextField());
		y+=50;
		txtB.setBounds(x, y, width, height);
		
		body.add(txtC = new JTextField());
		y+=50;
		txtC.setBounds(x, y, width, height);
		
		body.add(txtKq = new JTextField());
		y+=50;
		txtKq.setBounds(x, y, width, height);
		txtKq.setEditable(false);
		
		JPanel action = new JPanel();
		this.add(action, BorderLayout.SOUTH);
		action.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));;
		action.add(buttonGiai = new JButton("Giải"));
		action.add(buttonXoaRong = new JButton("Xóa rỗng"));
		action.add(buttonThoat = new JButton("Thoát"));
		
		buttonGiai.addActionListener(this);
		buttonXoaRong.addActionListener(this);
		buttonThoat.addActionListener(this);
		
		
	}
	public static void main(String[] args) {
		new LyQuocMinh_21105601_bai1().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(buttonThoat)) {
			System.exit(0);
		}
		if (obj.equals(buttonXoaRong)) {
			txtA.setText("");
			txtB.setText("");
			txtC.setText("");
			txtKq.setText("");
			txtA.requestFocus();
		}
		if (obj.equals(buttonGiai)) {
			double a =0,b = 0,c = 0,x1 = 0,x2 = 0,delta = 0;
			try {
				a = Double.parseDouble(txtA.getText());
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Nhap lai a");
				txtA.selectAll();
				txtA.requestFocus();
				return;
			}
			try {
				b = Double.parseDouble(txtB.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Nhap lai b");
				txtB.selectAll();
				txtB.requestFocus();
				return;
			}
			try {
				c = Double.parseDouble(txtC.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Nhap lai C");
				txtC.selectAll();
				txtC.requestFocus();
				return;
			}
			if (a== 0) {
				x1 = -c/b;
				
				txtKq.setText(String.format("Nghiem x: %.2f",x1));
				return;
			}
			delta = (b*b)-(4*a*c);
			if (delta <0) {
				txtKq.setText("Phuong trinh vo nghiem");
			}
			else{
				if (delta == 0) {
					x1 = x2 = -b/(2*a);
				}
				else {
					x1 = (-b+Math.sqrt(delta))/(2*a);
					x2 = (-b-Math.sqrt(delta))/(2*a);
				}
				txtKq.setText(String.format("Nghiem x1: %.2f Nghiem x2: %.2f delta %.2f",x1,x2,delta));
			}
			
		}
	}
}
