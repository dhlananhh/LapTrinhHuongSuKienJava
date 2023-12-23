package thaoTacTrenJList;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.DimensionUIResource;


public class ThaoTacTrenJList extends JFrame implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnSoChan, btnSoLe, btnSoNguyenTo, btnBoTo, btnXoa, btnTong;
	private JButton btnThoat, btnNhap, btnTaoSoRandom;
	private JTextField txtInput;
	private JCheckBox chkSoAm;
	private JList<Integer> list;
	private DefaultListModel<Integer> listModel;
	
	
	public ThaoTacTrenJList() {
		setTitle("Thao tác trên JList");
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		// ** content panel **
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		// ** north panel **
		JPanel northPanel = new JPanel();
		northPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		JLabel headingLabel = new JLabel("Thao tác trên JList - Checkbox");
		northPanel.add(headingLabel);
		contentPanel.add(northPanel, BorderLayout.NORTH);
		headingLabel.setFont(new Font("Arial", Font.BOLD, 25));
		headingLabel.setForeground(Color.BLUE);
		
		// ** west panel **
		JPanel westPanel = new JPanel();
		contentPanel.add(westPanel, BorderLayout.WEST);
		westPanel.setPreferredSize(new Dimension(200, 0));
		Border westBorder = BorderFactory.createLineBorder(Color.RED);
		TitledBorder westTitledBorder = new TitledBorder(westBorder, "Chọn tác vụ");
		westPanel.setBorder(westTitledBorder);
		Box westBox = Box.createVerticalBox();
		westPanel.add(westBox);
				
		// -- tạo các button và thêm vào west panel --
		// tạo west box để các button được hiển thị theo yêu cầu đề bài
		btnSoChan = new JButton("Tô đen số chẵn");
		westBox.add(btnSoChan);
		westBox.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		btnSoLe = new JButton("Tô đen số lẻ");
		westBox.add(btnSoLe);
		westBox.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		btnSoNguyenTo = new JButton("Tô đen số nguyên tố");
		westBox.add(btnSoNguyenTo);
		westBox.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		btnBoTo = new JButton("Bỏ tô đen");
		westBox.add(btnBoTo);
		westBox.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		btnXoa = new JButton("Xóa các giá trị đang tô đen");
		westBox.add(btnXoa);
		westBox.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		btnTong = new JButton("Tổng giá trị trong JList");
		westBox.add(btnTong);
		westBox.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		
		// ** center panel **
		JPanel centerPanel = new JPanel();
		contentPanel.add(centerPanel, BorderLayout.EAST);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setPreferredSize(new Dimension(480, 0));
		Border centerBorder = BorderFactory.createLineBorder(Color.RED);
		TitledBorder centerTitledBorder = new TitledBorder(centerBorder, "Nhập thông tin: ");
		centerPanel.setBorder(centerTitledBorder);
		
		// tạo subPanel1
		JPanel subPanel1 = new JPanel();
		
		btnNhap = new JButton("Nhập");
		subPanel1.add(btnNhap);
		txtInput = new JTextField(10);
		subPanel1.add(txtInput);
		chkSoAm = new JCheckBox("Cho nhập số âm");
		subPanel1.add(chkSoAm);
		
		// tạo list		
		listModel = new DefaultListModel<Integer>();
        list = new JList<Integer>(listModel);
        list.setVisibleRowCount(10);
        list.setFixedCellWidth(300);
        
        // tạo subPanel2 và thêm nút random vào
        JPanel subPanel2 = new JPanel();
        btnTaoSoRandom = new JButton("Tạo số ngẫu nhiên");
        subPanel2.add(btnTaoSoRandom);
        
        // thêm vào center panel
        centerPanel.add(subPanel1, BorderLayout.NORTH);
        centerPanel.add(list, BorderLayout.CENTER);
        centerPanel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);
		centerPanel.add(subPanel2, BorderLayout.SOUTH);
		
		// ** south panel **
		JPanel southPanel = new JPanel();
		contentPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setBackground(Color.LIGHT_GRAY);
		southPanel.setPreferredSize(new Dimension(0, 60));
		Border southBorder = BorderFactory.createLineBorder(Color.RED);
		TitledBorder southTitledBorder = BorderFactory.createTitledBorder(southBorder);
		southPanel.setBorder(southTitledBorder);
		southPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		btnThoat = new JButton("Đóng chương trình");
		southPanel.add(btnThoat);
		
		// ** create container **
		Container container = getContentPane();
		container.add(contentPanel);
		
		// ** subscribe ActionListener **
		btnThoat.addActionListener(this);
		btnSoChan.addActionListener(this);
		btnSoLe.addActionListener(this);
		btnSoNguyenTo.addActionListener(this);
		btnBoTo.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTong.addActionListener(this);
		btnNhap.addActionListener(this);
		btnTaoSoRandom.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			System.exit(0);
		} else if (o.equals(btnNhap)) {
			nhapTuBanPhim();
		} else if (o.equals(btnBoTo)) {
			list.clearSelection();
		} else if (o.equals(btnSoChan)) {
			toDenSoChan();
		} else if (o.equals(btnSoLe)) {
			toDenSoLe();
		} else if (o.equals(btnTong)) {
			JOptionPane.showMessageDialog(this, "Tổng các phần tử: " + tinhTong());
		} else if (o.equals(btnSoNguyenTo)) {
			toDenSoNguyenTo();
		} else if (o.equals(btnXoa)) {
			xoaPhanTuDaChon();
		} else if (o.equals(btnTaoSoRandom)) {
			listModel.clear();
            phatSinhSo();
		}
	}
	
	
	@Override
	public void valueChanged (ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void nhapTuBanPhim() {
		int n;
        try {
            n = Integer.parseInt(txtInput.getText());
            if (!chkSoAm.isSelected() && n > 0)
                listModel.addElement(n);
            else if (chkSoAm.isSelected())
                listModel.addElement(n);
            else {
                JOptionPane.showMessageDialog(this, "Nhập số nguyên dương");
                return;
            }
            txtInput.setText("");
            focusTextField(txtInput);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nhập số nguyên");
            txtInput.setText("");
            focusTextField(txtInput);
            return;
        }
	}
	
	
	public void focusTextField (JTextField text) {
        text.selectAll();
        text.requestFocus();
        return;
    }
	
	
	private void phatSinhSo() {
        for (int i = 0; i < 100; i++) {
            if (chkSoAm.isSelected())
                listModel.addElement(new Random().nextInt(200) - 100);
            else
                listModel.addElement(new Random().nextInt(200));
        }
    }

    private void xoaPhanTuDaChon() {
        int soPhanTu = list.getSelectedValuesList().size();
        for (int i = 0; i < soPhanTu; i++)
            listModel.removeElementAt(list.getSelectedIndex());
    }
    
    
    public boolean isPrime(int n) {
        if (n == 2 || n == 3 || n == 5)
            return true;
        if (n % 2 == 0 || n % 5 == 0 || n % 3 == 0 || n < 2)
            return false;
        if (n < 49)
            return true;
        if (n % 7 == 0 || n % 11 == 0 || n % 13 == 0 || n % 17 == 0 || n % 19 == 0 || n % 23 == 0 || n % 29 == 0
                || n % 31 == 0 || n % 37 == 0 || n % 41 == 0 || n % 43 == 0 || n % 47 == 0)
            return false;
        if (n < 2809)
            return true;
        long maxRange = (int) (Math.sqrt(n) + 1);
        for (int i = 53; i < maxRange; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    

    private void toDenSoNguyenTo() {
        list.clearSelection();
        for (int i = 0; i < listModel.size(); i++) {
            if (isPrime(listModel.getElementAt(i)))
                list.addSelectionInterval(i, i);
        }
    }

    private int tinhTong() {
        int tong = 0;
        for (int i = 0; i < listModel.size(); i++) {
            tong += listModel.getElementAt(i);
        }
        return tong;
    }

    private void toDenSoLe() {
        list.clearSelection();
        for (int i = 0; i < listModel.size(); i++) {
            if (listModel.getElementAt(i) % 2 != 0) {
                list.addSelectionInterval(i, i);
            }
        }
    }

    private void toDenSoChan() {
        list.clearSelection();
        for (int i = 0; i < listModel.size(); i++) {
            if (listModel.getElementAt(i) % 2 == 0) {
                list.addSelectionInterval(i, i);
            }
        }
    }
	

	public static void main(String[] args) {
		new ThaoTacTrenJList().setVisible(true);
	}
}
