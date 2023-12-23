package demo.gui;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GiaiPhuongTrinhBacHai extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtA;
	private JTextField txtB;
	private JTextField txtC;
	private JTextField txtKQ;
	private JButton buttonGiai = new JButton("Giai");
	private JButton buttonXoa = new JButton("Xoa rong");
	private JButton buttonThoat = new JButton("Thoat");
	
	
	public GiaiPhuongTrinhBacHai() {
		setTitle("^-^");
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		northPanel.setBackground(Color.cyan);
		
		JLabel lblHeading = new JLabel("GIAI PHUONG TRINH BAC HAI");
		lblHeading.setFont(new Font("Arial", Font.BOLD, 20));
		northPanel.add(lblHeading);
		
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		JLabel lblNhapA = new JLabel("Nhap a: ");
		centerPanel.add(lblNhapA);
		int x = 20, y = 40, width = 100, height = 30;
		lblNhapA.setBounds(x, y, width, height);
	
		JLabel lblNhapB = new JLabel("Nhap b: ");
		centerPanel.add(lblNhapB);
		y += 50;
		lblNhapB.setBounds(x, y, width, height);
		
		
		JLabel lblNhapC = new JLabel("Nhap c: ");
		centerPanel.add(lblNhapC);
		y += 50;
		lblNhapC.setBounds(x, y, width, height);
		
		
		JLabel lblKetQua = new JLabel("Ket qua: ");
		centerPanel.add(lblKetQua);
		y += 50;
		lblKetQua.setBounds(x, y, width, height);
		
		txtA = new JTextField();
		centerPanel.add(txtA);
		x += 100; y = 40; width = 300;
		txtA.setBounds(x, y, width, height);	
		
		txtB = new JTextField();
		centerPanel.add(txtB);
		y += 50;
		txtB.setBounds(x, y, width, height);	
		
		txtC = new JTextField();
		centerPanel.add(txtC);
		y += 50;
		txtC.setBounds(x, y, width, height);	
		
		
		txtKQ = new JTextField();
		centerPanel.add(txtKQ);
		y += 50;
		txtKQ.setBounds(x, y, width, height);
		txtKQ.setEditable(false);
		
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setBorder(BorderFactory.createTitledBorder("Chon tac vu: "));
		southPanel.add(buttonGiai);
		southPanel.add(buttonXoa);
		southPanel.add(buttonThoat);
		
		buttonGiai.addActionListener(this);
		buttonXoa.addActionListener(this);
		buttonThoat.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(buttonThoat)) {
			System.exit(0);
		} else if (o.equals(buttonXoa)) {
			txtA.setText("");
			txtB.setText("");
			txtC.setText("");
			txtA.requestFocus();
		} else if (o.equals(buttonGiai)) {
			if (!isInt(txtA)) {
				focus(txtA);
			} else if (!isInt(txtB)) {
				focus(txtB);
			} else if (!isInt(txtC)) {
				focus(txtC);
			} else {
				int a = Integer.parseInt(txtA.getText());
				int b = Integer.parseInt(txtB.getText());
				int c = Integer.parseInt(txtC.getText());
				if (a == 0) {
					giaiPhuongTrinhBac1(b, c);
				} else {
					float delta = b * b - 4 * a * c;
					if (delta < 0) {
						txtKQ.setText("Vo nghiem");
					} else if (delta == 0) {
						txtKQ.setText("Nghiem kep x1 = x2 = " + (-b / 2 * (float) a));
					} else {
						txtKQ.setText("Co 2 nghiem x1 = " + ((-b + Math.sqrt(delta)) / (2 * (float) a)) +
						" ; x2 = " + (-b - Math.sqrt(delta)) / (2 * (float) a));
					}
				}
			}
		}
	}
	
	
	private void giaiPhuongTrinhBac1 (int a, int b) {
		if (a != 0) {
			txtKQ.setText("Nghiem x = " + (-b / (float) a));
		} else if (b == 0) {
			txtKQ.setText("Vo so nghiem");
		} else {
			txtKQ.setText("Vo nghiem");
		}
	}
	
	
	private boolean isInt (JTextField text) {
		boolean result = true;
		try {
			Integer.parseInt(text.getText());
		} catch (NumberFormatException ex) {
			result = false;
		}
		return result;
	}
	
	
	private void focus (JTextField text) {
		JOptionPane.showMessageDialog(null, "Loi nhap lieu !!!");
		text.selectAll();
		text.requestFocus();
	}
	
	
	public static void main(String[] args) {
		new GiaiPhuongTrinhBacHai().createAndDisplayGUI();
	}
}
