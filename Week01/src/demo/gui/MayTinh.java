package demo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class MayTinh extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonGiai, buttonXoa, buttonThoat;
	private JButton bBlue, bRed, bYellow;
	private JTextField txtA, txtB, txtKQ;
	private JRadioButton rbCong, rbTru, rbNhan, rbChia;
	private JLabel tieuDe;

	public MayTinh() {
		setTitle("Cộng - Trừ - Nhân - Chia");
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		createGUI();
	}

	private void createGUI() {
		// Tiêu đề
		JPanel panelNorth;
		add(panelNorth = new JPanel(), BorderLayout.NORTH);
		panelNorth.add(tieuDe = new JLabel("Cộng Trừ Nhân Chia"));
		tieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		tieuDe.setForeground(Color.blue);

		// Nhập & Kết quả
		JPanel panelCenter;
		add(panelCenter = new JPanel(), BorderLayout.CENTER);
		panelCenter.setLayout(null);
		panelCenter.setBorder(BorderFactory.createTitledBorder("Tính toán"));

		JLabel nhapA, nhapB, ketQua;
		panelCenter.add(nhapA = new JLabel("Nhập a: "));
		int x = 51, y = 20, width = 100, height = 30;
		nhapA.setBounds(x, y, width, height);
		panelCenter.add(nhapB = new JLabel("Nhập b: "));
		y += 50;
		nhapB.setBounds(x, y, width, height);
		panelCenter.add(ketQua = new JLabel("Kết quả: "));
		y += 150;
		ketQua.setBounds(x, y, width, height);

		panelCenter.add(txtA = new JTextField());
		x += 55;
		y = 20;
		width = 250;
		txtA.setBounds(x, y, width, height);
		panelCenter.add(txtB = new JTextField());
		y += 50;
		txtB.setBounds(x, y, width, height);
		panelCenter.add(txtKQ = new JTextField());
		y += 150;
		txtKQ.setBounds(x, y, width, height);
		txtKQ.setEditable(false);

		// Phép toán
		panelCenter.add(rbCong = new JRadioButton("Cộng"));
		rbCong.setBounds(110, 125, 100, 30);
		panelCenter.add(rbTru = new JRadioButton("Trừ"));
		rbTru.setBounds(220, 125, 100, 30);
		panelCenter.add(rbNhan = new JRadioButton("Nhân"));
		rbNhan.setBounds(110, 165, 100, 30);
		panelCenter.add(rbChia = new JRadioButton("Chia"));
		rbChia.setBounds(220, 165, 100, 30);

		// Border phép toán
		JPanel tinh = new JPanel();
		panelCenter.add(tinh = new JPanel());
		tinh.setBorder(BorderFactory.createTitledBorder("Phép toán"));
		tinh.setBounds(105, 105, 252, 105);

		//Tránh chọn cùng lúc
		ButtonGroup group = new ButtonGroup();
		group.add(rbCong);
		group.add(rbTru);
		group.add(rbNhan);
		group.add(rbChia);

		// Tác vụ
		JPanel panelWest;
		add(panelWest = new JPanel(), BorderLayout.WEST);
		panelWest.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		panelWest.setPreferredSize(new Dimension(100, 400));
		panelWest.add(buttonGiai = new JButton("Giải"));
		panelWest.add(buttonXoa = new JButton("Xóa"));
		panelWest.add(buttonThoat = new JButton("Thoát"));
		buttonGiai.setPreferredSize(new Dimension(78, 30));
		buttonXoa.setPreferredSize(new Dimension(78, 30));
		buttonThoat.setPreferredSize(new Dimension(78, 30));

		// Đổi màu tiêu đề
		JPanel panelSouth;
		add(panelSouth = new JPanel(), BorderLayout.SOUTH);
		panelSouth.setBackground(Color.pink);
		panelSouth.setPreferredSize(new Dimension(500, 60));
		bBlue = new JButton("");
		bBlue.setBackground(Color.decode("#3f48cc"));
		bBlue.add(Box.createRigidArea(new Dimension(1, 20)));
		bRed = new JButton("");
		bRed.setBackground(Color.RED);
		bRed.add(Box.createRigidArea(new Dimension(1, 20)));
		bYellow = new JButton("");
		bYellow.setBackground(Color.YELLOW);
		bYellow.add(Box.createRigidArea(new Dimension(1, 20)));
		panelSouth.add(bBlue);
		panelSouth.add(bRed);
		panelSouth.add(bYellow);

		// Nghe sự kiện
		buttonGiai.addActionListener(this);
		buttonXoa.addActionListener(this);
		buttonThoat.addActionListener(this);
		bBlue.addActionListener(this);
		bRed.addActionListener(this);
		bYellow.addActionListener(this);
	}

	public static void main(String[] args) {
		new MayTinh().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(bBlue)) {
			tieuDe.setForeground(Color.BLUE);
		} else if (o.equals(bRed)) {
			tieuDe.setForeground(Color.RED);
		} else if (o.equals(bYellow)) {
			tieuDe.setForeground(Color.YELLOW);
		} else if (o.equals(buttonThoat)) {
			System.exit(0);
		} else if (o.equals(buttonXoa)) {
			txtA.setText("");
			txtB.setText("");
			txtKQ.setText("");
			txtA.requestFocus();
		} else if (o.equals(buttonGiai)) {
			if (!isInt(txtA)) {
				focus(txtA);
			} else if (!isInt(txtB)) {
				focus(txtB);
			} else {
				Double a = Double.parseDouble(txtA.getText());
				Double b = Double.parseDouble(txtB.getText());
				Double kq = 0.0;
				if (rbCong.isSelected()) {
					kq = a + b;
				} else if (rbTru.isSelected()) {
					kq = a - b;
				} else if (rbNhan.isSelected()) {
					kq = a * b;
				} else if (rbChia.isSelected()) {
					if (b != 0)
						kq = a / b;
					else
						focus(txtA);
				}txtKQ.setText(String.valueOf(kq));
			}
		}
	}

	private boolean isInt(JTextField text) {
		boolean result = true;
		try {
			Integer.parseInt(text.getText());
		} catch (NumberFormatException ex) {
			result = false;
		}
		return result;
	}

	private void focus(JTextField text) {
		JOptionPane.showMessageDialog(null, "Lỗi nhập liệu!!!");
		text.selectAll();
		text.requestFocus();
	}
}
