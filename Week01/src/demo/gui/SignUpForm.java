package demo.gui;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignUpForm extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JButton btnExit;
	
	
	public SignUpForm() {
		setTitle("demo");
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		JLabel lblUser = new JLabel("Username: ");
		JLabel lblPassword = new JLabel("Password: ");
		txtUsername = new JTextField(15);
		txtPassword = new JTextField(15);
		btnLogin = new JButton("Login");
		btnExit = new JButton("Exit");
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Username: "));
		add(panel);
		
		lblPassword.setPreferredSize(lblUser.getPreferredSize());
		panel.add(lblUser);
		panel.add(txtUsername);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(btnLogin);
		panel.add(btnExit);
		
		btnExit.addActionListener(this);
		btnLogin.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLogin)) {
			if (txtPassword.getText().equals("123")) {
				JOptionPane.showMessageDialog(this, "The password is correct");
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect password");
				txtPassword.selectAll();
				txtPassword.requestFocus();
			}
		} else {
			System.exit(0);
		}
	}
	
	
	public static void main(String[] args) {
		new SignUpForm().setVisible(true);
	}
}