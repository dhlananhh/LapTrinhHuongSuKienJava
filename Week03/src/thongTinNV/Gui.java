package thongTinNV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTextField txtMa, txtHo, txtTen, txtTuoi, txtTienLuong, txtTim;
	JButton btThem, btXoa, btXoaTrang, btLuu, btTim, btSua;
	JRadioButton rdnam, rdNu;
	JPanel panelCenter;
	DefaultTableModel tableModel;
	JTable table;
	DSNV data;
	private Database database;
	private DSNV ds;

	public Gui() {

		setTitle("^-^");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.createGui();
		
		database = new Database();
		try {
			File file = new File("data.dat");
			if (file.exists()) {
				loadData();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadData() {
		ds = (DSNV) database.readFile("Nhanvien.dat");
		if (ds == null) {
			ds = new DSNV();
		} else {
			for (NhanVien nv : ds.getDs()) {
				String[] row = { nv.getManv(), nv.getHo(), nv.getTen(), nv.getPhai(), nv.getTuoi() + "",
						nv.getTienLuong() + "" };
				tableModel.addRow(row);
			}
		}
	}

	private void createGui() {
		this.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
		this.add(panelNorth, BorderLayout.NORTH);

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));

		JLabel label = new JLabel("THÔNG TIN NHÂN VIÊN");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Color.BLUE);
		JLabel lbMa, lbHo, lbten, lbtuoi, lbPhai, lbTienLuong;

		b1.add(label);
		b2.add(lbMa = new JLabel("Mã nhân viên: "));
		b2.add(txtMa = new JTextField());
		b3.add(lbHo = new JLabel("Họ: "));
		b3.add(txtHo = new JTextField());
		b3.add(lbten = new JLabel("Tên nhân viên: "));
		b3.add(txtTen = new JTextField());
		b4.add(lbtuoi = new JLabel("Tuổi: "));
		b4.add(txtTuoi = new JTextField());
		b4.add(lbPhai = new JLabel("Phái: "));
		b4.add(rdnam = new JRadioButton("Nam"));
		b4.add(rdNu = new JRadioButton("Nữ"));
		b5.add(lbTienLuong = new JLabel("Tiền lương:"));
		b5.add(txtTienLuong = new JTextField());

		lbHo.setPreferredSize(lbMa.getPreferredSize());
		lbtuoi.setPreferredSize(lbMa.getPreferredSize());
		lbTienLuong.setPreferredSize(lbMa.getPreferredSize());

		ButtonGroup btGroup = new ButtonGroup();
		btGroup.add(rdnam);
		btGroup.add(rdNu);
		panelNorth.add(b);

		this.createTable();
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.X_AXIS));
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
		panelSouth.add(sp);
		this.add(panelSouth, BorderLayout.SOUTH);

		panelLeft.add(new JLabel("Nhập mã số cần tìm:"));
		panelLeft.add(txtTim = new JTextField(10));
		panelLeft.add(btTim = new JButton("Tìm"));

		panelRight.add(btThem = new JButton("Thêm"));
		panelRight.add(btXoaTrang = new JButton("Xóa trắng"));
		panelRight.add(btXoa = new JButton("Xóa"));
		panelRight.add(btSua = new JButton("Sửa"));
		panelRight.add(btLuu = new JButton("Lưu"));

		btThem.addActionListener(this);
		btXoaTrang.addActionListener(this);
		btXoa.addActionListener(this);
		btLuu.addActionListener(this);
		btTim.addActionListener(this);
		btSua.addActionListener(this);

	}

	/**
	 * DefaultCellEditor cung cấp trình chỉnh sửa ô mặc định cho các thành phần của
	 * JTable,Jcombox, JCheckBox, JSpinner, JColorChooser
	 * 
	 */
	private void createTable() {
		String[] header = { "Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		// tạo đối tượng TableCellEditor cho cột phái
		JComboBox<String> cbSex = new JComboBox<>(new String[] { "Nam", "Nữ" });
		DefaultCellEditor sexEditor = new DefaultCellEditor(cbSex);
		table.getColumnModel().getColumn(3).setCellEditor(sexEditor);

		JScrollPane scp = new JScrollPane(table);
		this.add(scp, BorderLayout.CENTER);
		table.addMouseListener(new MouseListener() {

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
				int viTri = table.getSelectedRow();
				if (viTri >= 0 && viTri < ds.getSize()) {
					NhanVien nv = ds.getNhanVien(viTri);
					napVaotxt(nv);
				}
			}

			private void napVaotxt(NhanVien nv) {
				txtMa.setText(nv.getManv());
				txtHo.setText(nv.getHo());
				txtTen.setText(nv.getTen());
				if (nv.getPhai().equalsIgnoreCase("Nam"))
					rdnam.setSelected(true);
				else if (nv.getPhai().equalsIgnoreCase("Nữ"))
					rdNu.setSelected(true);
				txtTuoi.setText(nv.getTuoi() + "");
				txtTienLuong.setText(nv.getTienLuong() + "");
				txtMa.requestFocus();
			}
		});

	}

	public static void main(String[] args) {
		new Gui().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btThem)) {
			try {
				them();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		else if (o.equals(btLuu))
			luu();
		else if (o.equals(btXoaTrang))
			xoaTrang();
		else if (o.equals(btXoa))
			xoaDong();
		else if (o.equals(btTim))
			tim();
		else if (o.equals(btSua))
			sua();

	}

	private void napVaotxt(NhanVien nv) {
		txtMa.setText(nv.getManv());
		txtHo.setText(nv.getHo());
		txtTen.setText(nv.getTen());
		if (nv.getPhai().equalsIgnoreCase("Nam"))
			rdnam.setSelected(true);
		else if (nv.getPhai().equalsIgnoreCase("Nữ"))
			rdNu.setSelected(true);
		txtTuoi.setText(nv.getTuoi() + "");
		txtTienLuong.setText(nv.getTienLuong() + "");
		txtMa.requestFocus();
	}

	private void xoaDong() {
		int i = table.getSelectedRow();
		if (i != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Bạn có chăc chắc muốn xóa dòng này", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaViTri(i);
				tableModel.removeRow(i);
				xoaTrang();
				database.saveFile("Nhanvien.dat", ds);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dong cần xóa");
		}
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		rdnam.setSelected(false);
		rdNu.setSelected(false);
		txtTienLuong.setText("");
		txtMa.requestFocus();

	}

	private void them() throws Exception {
		NhanVien nv = null;
		String ma, ho, ten;
		String phai ="";
		int tuoi;
		Double tienLuong;
		try {
			ma = txtMa.getText();
			ho = txtHo.getText();
			ten = txtTen.getText();
			if (rdnam.isSelected())
				phai = rdnam.getText();
			else if(rdNu.isSelected())
				phai = rdNu.getText();
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phái");
				return;
			}
			tuoi = Integer.parseInt(txtTuoi.getText());
			tienLuong = Double.parseDouble(txtTienLuong.getText());
			nv = new NhanVien(ma, ho, ten, phai, tuoi, tienLuong);
			if (!ds.them(nv)) {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
				txtMa.requestFocus();
			}
			else {
				String[] row = { nv.getManv(), nv.getHo(), nv.getTen(), nv.getPhai(), nv.getTuoi() + "",
						nv.getTienLuong() + "" };
				tableModel.addRow(row);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}

	}

	/**
	 * Load data lên table
	 */

	private void luu() {
		try {
			database.saveFile("Nhanvien.dat", ds);
			JOptionPane.showMessageDialog(null, "Lưu thành công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		// database.saveFile("Nhanvien.dat", ds);

	}

	/**
	 * Xóa toàn bộ dữ liệu trên table
	 */
//	private void clearDataOnTable() {
//		while (tableModel.getRowCount() > 0)
//			tableModel.removeRow(0);
//	}

	private void tim() {
		if (txtTim.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần tìm");
		else {
			int pos = ds.timKiemNV(txtTim.getText());
			if (pos == -1)
				JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên có mã " + txtTim.getText());
			else {
				table.setRowSelectionInterval(pos, pos);
				napVaotxt(ds.tim(txtTim.getText()));
			}
		}
	}

	private void sua() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa");
			return;
		}
		String maNV = table.getValueAt(row, 0).toString();
		// Kiểm tra nếu người dùng thay đổi mã nhân viên
		String newMaNV = txtMa.getText();
		if (!maNV.equals(newMaNV)) {
			JOptionPane.showMessageDialog(null, "Không thể sửa mã nhân viên");
			return;
		}
		// lấy thông tin mới từ các trường dữ liệu trên giao diện
		String ho = txtHo.getText();
		String ten = txtTen.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String phai = "";
		if (rdnam.isSelected())
			phai = (String) rdnam.getText();
		else if (rdNu.isSelected())
			phai = (String) rdNu.getText();
		double tienLuong = Double.parseDouble(txtTienLuong.getText());

		// cập nhật thông tin nhân viên trong danh sách và trên bảng

		NhanVien nv = new NhanVien(maNV, ho, ten, phai, tuoi, tienLuong);
		int index = ds.indexOf(nv);
		if (index != -1) {
			// Thay đổi thông tin nhân viên trong danh sách
			ds.set(index, nv);
			// Cập nhật lại thông tin trên bảng
			table.getModel().setValueAt(ho, row, 1);
			table.getModel().setValueAt(ten, row, 2);
			table.getModel().setValueAt(phai, row, 3);
			table.getModel().setValueAt(tuoi, row, 4);
			table.getModel().setValueAt(tienLuong, row, 5);
		}
	}

}
