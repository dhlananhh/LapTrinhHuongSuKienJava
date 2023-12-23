package tuan01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class LyQuocMinh_21105601_bai3 extends JFrame implements ActionListener {
	/**
	 * 
	 */
	JPanel title = new JPanel();
	JPanel left = new JPanel();
	JPanel center = new JPanel();
	JPanel bottom = new JPanel();
	JPanel blue,red,yellow;
	JButton btnGiai, btnXoa,btnThoat;
	JTextField txtA,txtB,txtKq;
	JRadioButton radioCong = new JRadioButton("Cộng");
	JRadioButton radioTru = new JRadioButton("Trừ");
	JRadioButton radioNhan = new JRadioButton("Nhân");
	JRadioButton radioChia = new JRadioButton("Chia");
	public LyQuocMinh_21105601_bai3() {
		
		btnGiai = new JButton("Giải");
//		btnGiai.setPreferredSize(new Dimension(1000, 10000));
		btnXoa = new JButton("Xóa");
		btnThoat = new JButton("Thoát");
		Box bLeft = Box.createVerticalBox();
		Box bButtom = Box.createVerticalBox();
		Box line = Box.createHorizontalBox();
		Box bCenter = Box.createVerticalBox();
		Box bfCenter = Box.createHorizontalBox();
		
		setSize(500,500);
		setTitle("Cộng trừ Nhân Chia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Set cac noi dung trong box
		JLabel content = new JLabel("Cộng Trừ Nhân Chia");
		content.setFont(new Font("Arial",Font.BOLD,23));
		content.setForeground(Color.blue);
		left.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		bLeft.add(btnGiai);
		bLeft.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		bLeft.add(btnXoa);
		bLeft.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		bLeft.add(btnThoat);
		
		//Reference kích cỡ button
		btnGiai.setPreferredSize(btnThoat.getPreferredSize());
		btnXoa.setPreferredSize(btnThoat.getPreferredSize());
		title.add(content);
		left.add(Box.createRigidArea(new Dimension(10,10)));
		left.add(bLeft);
		left.add(Box.createRigidArea(new Dimension(10,10)));
		blue = new JPanel();
		blue.setBackground(Color.BLUE);
		red = new JPanel();
		red.setBackground(Color.red);
		yellow = new JPanel();
		yellow.setBackground(Color.yellow);
		line.add(Box.createRigidArea(new Dimension(100, 10)));
		line.add(blue);
		line.add(Box.createRigidArea(new Dimension(10, 10)));
		line.add(red);
		line.add(Box.createRigidArea(new Dimension(10, 10)));
		line.add(yellow);
		line.add(Box.createRigidArea(new Dimension(100, 10)));
		bButtom.setBackground(Color.pink);
		bButtom.add(line);
		bButtom.add(Box.createRigidArea(new Dimension(100, 20)));
		left.setBackground(Color.lightGray);
		bottom.add(bButtom);
		bottom.setBackground(Color.pink);
		//Tao center
		bCenter.add(center);
		Box bL1 = Box.createHorizontalBox();
		Label nhapA = new Label("Nhập a: ");
		nhapA.setFont(new Font("Arial", Font.BOLD, 12));
		bL1.add(nhapA);
		bL1.add(txtA = new JTextField(20));
		bCenter.add(bL1);
		bCenter.add(Box.createRigidArea(new Dimension(10,10)));
		
		Box bL2 = Box.createHorizontalBox();
		Label nhapB = new Label("Nhập b: ");
		nhapB.setFont(new Font("Arial", Font.BOLD, 12));
		bL2.add(nhapB);
		bL2.add(txtB = new JTextField(20));
		bCenter.add(bL2);
		bCenter.add(Box.createRigidArea(new Dimension(10,10)));
		
		Box bL3 = Box.createHorizontalBox();
		bL3.setBorder(BorderFactory.createTitledBorder("Phep toan"));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioCong);
		bg.add(radioNhan);
		bg.add(radioTru);
		bg.add(radioChia);
		Box bL31 = Box.createVerticalBox();
		Box bL32 = Box.createVerticalBox();
		bL31.add(radioCong);
		bL31.add(radioNhan);
		bL32.add(radioTru);
		bL32.add(radioChia);
		bL3.add(bL31);
		bL3.add(Box.createRigidArea(new Dimension(80, 10)));
		bL3.add(bL32);
		bL3.add(Box.createRigidArea(new Dimension(80, 10)));
		bCenter.add(bL3);
		bfCenter.setBorder(BorderFactory.createTitledBorder("Tính toán"));
		bCenter.add(Box.createRigidArea(new Dimension(10,10)));
		
		Box bL4 = Box.createHorizontalBox();
		Label lblKetQua	 = new Label("Kết quả:");
		lblKetQua.setFont(new Font("Arial", Font.BOLD, 12));
		txtKq = new JTextField(20);
		txtKq.setEditable(false);
		bL4.add(lblKetQua);
		bL4.add(txtKq);
		bCenter.add(bL4);
		
		bfCenter.add(Box.createRigidArea(new Dimension(40, 10)));
		bfCenter.add(bCenter);
		bfCenter.add(Box.createRigidArea(new Dimension(40, 10)));
		this.add(bfCenter,BorderLayout.CENTER);
		this.add(title,BorderLayout.NORTH);
		this.add(bottom,BorderLayout.SOUTH);
		this.add(left,BorderLayout.WEST);
		btnGiai.addActionListener(this);
		btnThoat.addActionListener(this);
		btnXoa.addActionListener(this);
		radioCong.addActionListener(this);
		radioChia.addActionListener(this);
		radioTru.addActionListener(this);
		radioNhan.addActionListener(this);
	}
	private static final long serialVersionUID = 1L;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnThoat)) {
			System.exit(0);
		}
		if (obj.equals(btnXoa)) {
			txtA.setText("");
			txtB.setText("");
			txtKq.setText("");
			txtA.requestFocus();
		}
		if (obj.equals(btnGiai)) {
			double a=0,b=0;
			try {
				a = Double.parseDouble(txtA.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Du lieu A bi loi");
				txtA.requestFocus();
				txtA.selectAll();
				return;
			}
			try {
				b = Double.parseDouble(txtB.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Du lieu B bi loi");
				txtB.requestFocus();
				txtB.selectAll();
				return;
			}
			String temp;
			
			if (radioCong.isSelected()==true) {
				temp = String.format("%.2f", a+b);
				txtKq.setText(temp);
				return;
			}
			if (radioTru.isSelected()==true) {
				temp = String.format("%.2f", a-b);
				txtKq.setText(temp);
			}
			if (radioNhan.isSelected()==true) {
				temp = String.format("%.2f", a*b);
				txtKq.setText(temp);
			}
			if (radioChia.isSelected()==true) {
				if (b==0) {
					JOptionPane.showMessageDialog(this, "khong chia duoc cho 0");
					txtB.requestFocus();
					txtB.selectAll();
					return;
				}
				temp = String.format("%.2f", a/b);
				txtKq.setText(temp);
			}
		}
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new LyQuocMinh_21105601_bai3().setVisible(true);
	}

}
