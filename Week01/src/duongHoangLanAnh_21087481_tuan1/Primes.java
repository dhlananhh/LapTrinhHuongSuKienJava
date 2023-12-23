package duongHoangLanAnh_21087481_tuan1;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Primes extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtN;
	private JButton generateButton;
	private JTextArea taPrimes;
	
	
	public Primes() {
		setTitle("Primes");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		createAndDisplayGUI();
	}

	
	public void createAndDisplayGUI() {
		setLayout(null);
		txtN = new JTextField();
		add(txtN);
		txtN.setBounds(50, 30, 200, 30);
		txtN.setToolTipText("Nhap so nguyen to can hien thi");
		
		generateButton = new JButton("Generate");
		add(generateButton);
		generateButton.setBounds(250, 30, 100, 30);
		
		taPrimes = new JTextArea();
		JScrollPane scroll;
		add(scroll = new JScrollPane(taPrimes));
		scroll.setBounds(50, 70, 300, 170);
		taPrimes.setToolTipText("Danh sach cac so nguyen to");
		taPrimes.setEditable(false);
		
		generateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				taPrimes.setText("");
				try {
					int n = Integer.parseInt(txtN.getText());
					if (n > 0) {
						int i = 2;
	                    while (n > 0) {
	                        if (isPrime(i)) {
	                        	taPrimes.append(i + "\n");
	                            n--;
	                        }
	                        i++;
	                    }
					} else {
						JOptionPane.showMessageDialog(null, "Nhap so nguyen duong");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Loi nhap lieu !!!");
				}
				focus();
			}
		});
	}
		
	
	private boolean isPrime (int n) {
		for (int i=2; i<n; i++)
			if (n % i == 0)
				return false;
		return true;
	}
	
	
	private void focus() {
		txtN.selectAll();
		txtN.requestFocus();
		return;
	}
	
	
	public static void main(String[] args) {
		new Primes().createAndDisplayGUI();
	}
}
