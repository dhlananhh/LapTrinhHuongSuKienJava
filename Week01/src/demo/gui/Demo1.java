package demo.gui;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class Demo1 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JButton btnExit;

	public Demo1() {
		setTitle("demo1");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// giaodien
		JLabel lblUser = new JLabel("ten dang nhap");
		JLabel lbllPassword = new JLabel("mat khau");
		txtUserName = new JTextField(15);
		txtPassword = new JTextField(15);
		btnLogin = new JButton("dang nhap");
		btnExit = new JButton("thoat");
		JPanel pn = new JPanel();
		pn.setBorder(BorderFactory.createTitledBorder("giao dien login"));
		add(pn);
		lbllPassword.setPreferredSize(lblUser.getPreferredSize());
		pn.add(lblUser);
		pn.add(txtUserName);
		pn.add(lbllPassword);
		pn.add(txtPassword);
		pn.add(btnLogin);
		pn.add(btnExit);

		// dang ki su kien
		btnExit.addActionListener(this);
		btnLogin.addActionListener(this);
	}

	public static void main(String[] args) {
		new Demo1().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLogin)) {
			if (txtPassword.getText().equals("123"))
				JOptionPane.showMessageDialog(this, "pass dung roi");
			else {
				JOptionPane.showMessageDialog(this, "pass sai");
				txtPassword.selectAll();
				txtPassword.requestFocus();
			}

		} else {
			System.exit(0);
		}
	}
}
