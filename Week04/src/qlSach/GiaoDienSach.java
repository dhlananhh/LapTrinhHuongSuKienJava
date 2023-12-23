package qlSach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TabableView;

public class GiaoDienSach extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static String FILENAME = "sach.dat";
	JTextField tfMaSach, tfTuaSach, tfTacGia, tfNamSX, tfNhaXB, tfSoTrang, tfDonGia, tfISBN, tfTimMa;
	JButton btThem, btXoaRong, btSua, btXoa;
	JComboBox cbbmaSach;
	JTable tableSach;
	DefaultTableModel tbModel;
	DanhSachSach ds;
	Database database;
	JLabel labelError;

	public GiaoDienSach() {
		setTitle("Quan Ly Sach");
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.createGui();
		this.createTable();
		this.createData();

	}

	private void createData() {
		database = new Database();
		try {
			File file = new File(FILENAME);
			if (file.exists()) {
				try {
					loadData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadData() {
		ds = (DanhSachSach) database.readFile(FILENAME);
		if (ds == null)
			ds = new DanhSachSach();
		else {
			for (Sach sach : ds.getDsSach()) {
				String[] row = { sach.getMaSach(), sach.getTuaSach(), sach.getTacGia(), sach.getNamSX() + "",
						sach.getNhaXB(), sach.getSoTrang() + "", new DecimalFormat("#,##0.00").format(sach.getDonGia()),
						sach.getiSBN() };
				tbModel.addRow(row);
			}
			updateCombobox();
		}

	}

	/// Dùng cho file text
//	private void createData() {
//		database = new Database();
//	    try {
//	        loadData();
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
//
//
//	private void loadData() throws Exception {
//		try {
//			ds = (DanhSachSach) database.read_Sach(FILENAME);
//			if (ds == null)
//				ds = new DanhSachSach();
//			else {
//				for (Sach sach : ds.getDsSach()) {
//					String[] row = { sach.getMaSach(), sach.getTuaSach(), sach.getTacGia(), sach.getNamSX() + "",
//							sach.getNhaXB(), sach.getSoTrang() + "",
//							new DecimalFormat("#,##0.00").format(sach.getDonGia()), sach.getiSBN() };
//					tbModel.addRow(row);
//				}
//			}
//		} catch (FileNotFoundException e) {
//			File file = new File(FILENAME);
//			file.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	private void createGui() {
		this.setLayout(new BorderLayout());
		Box bL = Box.createVerticalBox();
		Box bR = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, bLoi, bThem;

		JLabel lbMa, lbtua, lbTacGia, lbNhaXB, lbNamXB, lbSoTrang, lbISBN, lbDonGia;

		bL.add(b1 = Box.createHorizontalBox());
		bL.add(Box.createVerticalStrut(5));
		bL.add(b2 = Box.createHorizontalBox());
		bL.add(Box.createVerticalStrut(5));
		bL.add(b3 = Box.createHorizontalBox());
		bL.add(Box.createVerticalStrut(5));
		bL.add(b4 = Box.createHorizontalBox());
		bL.add(Box.createVerticalStrut(5));
		bL.add(b5 = Box.createHorizontalBox());
		bL.add(Box.createVerticalStrut(5));
		bL.add(bLoi = Box.createHorizontalBox());
		bL.add(Box.createVerticalStrut(5));

		bR.add(b6 = Box.createHorizontalBox());
		bR.add(Box.createVerticalStrut(5));
		bR.add(b7 = Box.createHorizontalBox());
		bR.add(Box.createVerticalStrut(5));
		bR.add(b8 = Box.createHorizontalBox());
		bR.add(Box.createVerticalStrut(5));
		bR.add(b9 = Box.createHorizontalBox());
		bR.add(Box.createVerticalStrut(5));
		bR.add(b10 = Box.createHorizontalBox());
		bR.add(Box.createVerticalStrut(5));
		bR.add(bThem = Box.createHorizontalBox());
		bR.add(Box.createVerticalStrut(5));

		b1.add(lbMa = new JLabel("Mã sách:"));
		b1.add(tfMaSach = new JTextField());
		b1.add(Box.createHorizontalStrut(100));
		b2.add(lbtua = new JLabel("Tựa sách:"));
		b2.add(tfTuaSach = new JTextField());
		b3.add(lbNamXB = new JLabel("Năm xuất bản:"));
		b3.add(tfNamSX = new JTextField());
		b4.add(lbSoTrang = new JLabel("Số Trang: "));
		b4.add(tfSoTrang = new JTextField());
		b5.add(lbISBN = new JLabel("International Stardard Book Number (ISBN): "));
		b5.add(tfISBN = new JTextField());
		bLoi.add(labelError = new JLabel(" "));
		labelError.setForeground(Color.red);

		b6.add(new JLabel("  "));
		b7.add(lbTacGia = new JLabel("Tác giả: "));
		b7.add(tfTacGia = new JTextField());
		b8.add(lbNhaXB = new JLabel("Nhà xuất bản: "));
		b8.add(tfNhaXB = new JTextField(15));
		b9.add(lbDonGia = new JLabel("Đơn giá: "));
		b9.add(tfDonGia = new JTextField());
		b10.add(new JLabel("  "));
		bThem.add(new JLabel("  "));

		lbMa.setPreferredSize(lbNamXB.getPreferredSize());
		lbtua.setPreferredSize(lbNamXB.getPreferredSize());
		lbSoTrang.setPreferredSize(lbNamXB.getPreferredSize());
		lbTacGia.setPreferredSize(lbNhaXB.getPreferredSize());
		lbDonGia.setPreferredSize(lbNhaXB.getPreferredSize());

		JPanel panelNorth = new JPanel();
		this.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout());

		JPanel panelField = new JPanel();
		panelField.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		panelField.setLayout(new BoxLayout(panelField, BoxLayout.X_AXIS));
		panelField.add(bL);
		panelField.add(Box.createHorizontalStrut(30));
		panelField.add(bR);
		panelNorth.add(panelField, BorderLayout.CENTER);

		JPanel panelButton = new JPanel();
		JLabel lbTim;
		panelButton.add(btThem = new JButton("Thêm"));
		panelButton.add(btXoaRong = new JButton("Xóa Rỗng"));
		panelButton.add(btSua = new JButton("Sửa"));
		panelButton.add(btXoa = new JButton("Xóa"));
		panelButton.add(Box.createRigidArea(new Dimension(50, 0)));
		panelButton.add(lbTim = new JLabel("Tìm theo mã sách "));
		panelButton.add(cbbmaSach = new JComboBox());
		cbbmaSach.setPreferredSize(new Dimension(80, 20));
		panelNorth.add(panelButton, BorderLayout.SOUTH);

		btThem.addActionListener(this);
		btXoaRong.addActionListener(this);
		btSua.addActionListener(this);
		btXoa.addActionListener(this);
		cbbmaSach.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String selectedMaSach = (String) cbbmaSach.getSelectedItem();
					Sach selectedSach = ds.timTheoMa(selectedMaSach);
					if(selectedSach!=null) {
						napVaoCacONhapLieu(selectedSach);
					}
				
				}
				
			}
		});

	}

	private void createTable() {
		String[] header = { "Mã sách", "Tựa sách", "Tác giả", "Năm xuất bản", "Nhà xuất bản", "Số trang", "Đơn giá",
				"ISBN" };
		tbModel = new DefaultTableModel(header, 0);
		tableSach = new JTable(tbModel);
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách"));
		JScrollPane spane = new JScrollPane(tableSach, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelTable.add(spane, BorderLayout.CENTER);
		this.add(panelTable, BorderLayout.CENTER);
		tableSach.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				tableAction();

			}

			private void tableAction() {
				int vitri = tableSach.getSelectedRow();
				if (vitri >= 0 && vitri < ds.getSize()) {
					Sach sach = ds.getSach(vitri);
					napVaoCacONhapLieu(sach);
				}

			}

		});

	}

	private void napVaoCacONhapLieu(Sach sach) {
		tfMaSach.setText(sach.getMaSach());
		tfTuaSach.setText(sach.getTuaSach());
		tfTacGia.setText(sach.getTacGia());
		tfNamSX.setText(sach.getNamSX() + "");
		tfNhaXB.setText(sach.getNhaXB());
		tfSoTrang.setText(sach.getSoTrang() + "");
		tfDonGia.setText(sach.getDonGia() + "");
		tfISBN.setText(sach.getiSBN());
		cbbmaSach.setSelectedItem(sach.getMaSach());
		tfMaSach.requestFocus();

	}

	public static void main(String[] args) {
		new GiaoDienSach().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btThem))
			them();
		else if (o.equals(btXoaRong))
			xoaRong();
		else if (o.equals(btXoa))
			Xoa();
		else if (o.equals(btSua))
			sua();

	}

	private void showMessege(String message, JTextField txt) {
		labelError.setText(message);
		txt.requestFocus();
	}

	/**
	 * Kiểm tra dữ liệu nhập vào từ JtextField
	 * 
	 * @return
	 */
	private boolean validData() {
		String maSach = tfMaSach.getText().trim();
		String tuaSach = tfTuaSach.getText().trim();
		String tacGia = tfTacGia.getText().trim();
		String nam = tfNamSX.getText().trim();
		String nhaXB = tfNhaXB.getText().trim();
		String soTrang = tfSoTrang.getText().trim();
		String donGia = tfDonGia.getText().trim();
		String isBN = tfISBN.getText().trim();
		if (!(maSach.length() > 0 && maSach.matches("[A-Z]\\d{3}"))) {
			showMessege("Error: Mã sách cập nhật theo mẫu [A-Z]\\d{3}", tfMaSach);
			return false;
		}
		if (!(tuaSach.length() > 0 && tuaSach.matches("[a-zA-Z ]+"))) {
			showMessege("Error: Tựa sách cập nhật theo mẫu [a-zA-Z ]+", tfTuaSach);
			return false;
		}
		if (!(tacGia.length() > 0 && tacGia.matches("[a-zA-Z ]+"))) {
			showMessege("Error: Tác giả cập nhật theo mẫu [a-zA-Z ]+", tfTacGia);
			return false;
		}
		if (nam.length() > 0) {
			try {
				int x = Integer.parseInt(nam);
				int namHienHanh = Calendar.getInstance().get(Calendar.YEAR);
				if (!(x >= 1990 && x < namHienHanh)) {
					showMessege("Error năm xuất bản >=1990 & <= " + namHienHanh, tfNamSX);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessege("Error năm xuất bản nhập vào phải là số", tfNamSX);
				return false;
			}
		}
		if (soTrang.length() > 0) {
			try {
				int x = Integer.parseInt(soTrang);
				if (x < 0) {
					showMessege("Eror: Số trang phải là số nguyên dương", tfSoTrang);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessege("Eror: Số trang phải là số nguyên ", tfSoTrang);
				return false;
			}
		}
		if (donGia.length() > 0) {
			try {
				double x = Double.parseDouble(donGia);
				if (x < 0) {
					showMessege("Eror: Đơn giá phải không âm", tfSoTrang);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessege("Eror: Đơn giá phải là chuỗi số ", tfSoTrang);
				return false;
			}

		}
		if (isBN.length() > 0) {
			if (!(isBN.matches("\\d+(-\\d+){3,4}"))) {
				showMessege("Error: ISBN cần nhập theo định dạng X-X-X-X (hoặc X-X-X-X-X)", tfISBN);
				return false;
			}
		}
		return true;
	}

	private void them() {
		if (validData()) {
			Sach s = revertSachFromTextField();
			if (!(ds.themSach(s))) {
				showMessege("Trùng mã sách", tfMaSach);
				tfMaSach.requestFocus();
			} else {
				String[] row = { s.getMaSach(), s.getTuaSach(), s.getTacGia(), s.getNamSX() + "", s.getNhaXB(),
						s.getSoTrang() + "", new DecimalFormat("#,##0.00").format(s.getDonGia()) + "", s.getiSBN() };
				tbModel.addRow(row);
				updateCombobox();
				labelError.setText("");
				luu();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}
		}
	}

	private void updateCombobox() {
		int n = ds.getSize();
		String[] item = new String[n];
		int i = 0;
		for (Sach s : ds.getDsSach()) {
			item[i] = s.getMaSach();
			i++;

		}
		cbbmaSach.setModel(new DefaultComboBoxModel<String>(item));

	}

	private Sach revertSachFromTextField() {
		Sach sach = null;
		String maSach, tuaSach, tacGia, nhaXB, ISBN;
		int soTrang, namXB;
		double donGia;
		maSach = tfMaSach.getText();
		tuaSach = tfTuaSach.getText();
		tacGia = tfTacGia.getText();
		namXB = Integer.parseInt(tfNamSX.getText());
		nhaXB = tfNhaXB.getText();
		soTrang = Integer.parseInt(tfSoTrang.getText());
		donGia = Double.parseDouble(tfDonGia.getText());
		ISBN = tfISBN.getText();
		sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
		return sach;

	}

	private void updateTableData() {

	}

	/**
	 * sửa trên component, không sửa trên table
	 */
	private void sua() {
		
//		int row = tableSach.getSelectedRow();
//		if(row == -1) {
//			JOptionPane.showMessageDialog(null, "Vui long chon dong can sua");
//			return;
//		}
		int row = tableSach.getSelectedRow();
		Sach sach = revertSachFromTextField();
		int index = ds.indexOf(sach);
		if(index != -1) {
			//thay doi thong sach trong danh sach
			ds.set(index, sach);
			//cap nhat thong tin tren bang
			tbModel.setValueAt(sach.getMaSach(), row, 0);
			tbModel.setValueAt(sach.getTuaSach(), row, 1);
			tbModel.setValueAt(sach.getTacGia(), row, 2);
			tbModel.setValueAt(sach.getNamSX(), row, 3);
			tbModel.setValueAt(sach.getNhaXB(), row, 4);
			tbModel.setValueAt(sach.getSoTrang(), row, 5);
			tbModel.setValueAt(sach.getDonGia(), row, 6);
			tbModel.setValueAt(sach.getiSBN(), row, 7);
			tbModel.fireTableRowsUpdated(row, row);
			ds.set(index, sach);
			
		}
	}

	private void luu() {
		try {
			database.saveFile(FILENAME, ds);
			// database.writeSach(FILENAME, ds);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void xoaRong() {
		tfMaSach.setText("");
		tfTuaSach.setText("");
		tfTacGia.setText("");
		tfNamSX.setText("");
		tfNhaXB.setText("");
		tfSoTrang.setText("");
		tfISBN.setText("");
		tfDonGia.setText("");
	}

	private void Xoa() {
		int i = tableSach.getSelectedRow();
		if (i != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Ban co chac muon xoa", "delte", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaTaiViTri(i);
				tbModel.removeRow(i);
				xoaRong();

			}
		}
	}

}
