package regexNhanVien;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Form extends JFrame{
	JPanel pnBox;
	JLabel lbMa, lbTen, lbTuoi, lbEmail;
	JTextField txtMa, txtTen, txtTuoi, txtEmail;
	JButton btnThem;
	public void GUI() {
		pnBox = new JPanel();
		pnBox.setLayout(new BoxLayout(pnBox, BoxLayout.Y_AXIS));
		
		Box b1,b2,b3,b4;
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		
		lbMa = new JLabel("Mã: ");
		lbTen = new JLabel("Tên: ");
		lbTuoi = new JLabel("Tuổi: ");
		lbEmail = new JLabel("Email: ");
		txtMa = new JTextField();
		txtTen = new JTextField();
		txtTuoi = new JTextField();
		txtEmail = new JTextField();
		
		pnBox.add(b1);
		pnBox.add(Box.createVerticalStrut(10));
		pnBox.add(b2);
		pnBox.add(Box.createVerticalStrut(10));
		pnBox.add(b3);
		pnBox.add(Box.createVerticalStrut(10));
		pnBox.add(b4);
		pnBox.add(Box.createVerticalStrut(10));
		
		b1.add(lbMa);
		b1.add(txtMa);
		b2.add(lbTen);
		b2.add(txtTen);
		b3.add(lbTuoi);
		b3.add(txtTuoi);
		b4.add(lbEmail);
		b4.add(txtEmail);
		
		btnThem = new JButton("Thêm");
		pnBox.add(btnThem);
		
		this.add(pnBox);
	}
	public Form(){
		this.setTitle("Regex");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		GUI();
		Event event = new Event(this);
		btnThem.addActionListener(event);
	}
	public static void main(String[] args) {
		new Form().setVisible(true);
	}
}
