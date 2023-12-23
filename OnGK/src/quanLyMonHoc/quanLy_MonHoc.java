package quanLyMonHoc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
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
import javax.swing.table.TableColumn;

public class quanLy_MonHoc extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnConTent, pnCenter, pnChucNang;
	private JLabel lbMaMon, lbTenMon, lbHocKy, lbSoTinChi, lbTim;
	private JTextField txtMaMon, txtTenMon, txtSoTinChi, txtTim;
	private JComboBox cbHocKy;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim;
	private DefaultTableModel model;
	private JTable table;
	private DanhSachMonHoc ds;
	private Database database;

	public quanLy_MonHoc() {
		super("Quan ly mon hoc");
		ds = new DanhSachMonHoc();
		database = new Database();
		createGui();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadData() throws Exception{
		ds = database.read_MH("MonHoc.txt");
		if(ds == null) {
			ds = new DanhSachMonHoc();
		}else {
			for (MonHoc mh : ds.getList()) {
				String[] row = {mh.getMaMon()+"", mh.getTenMon(), mh.getHocKy(), mh.getSoTinChi()+""};
				model.addRow(row);
			}
		}
	}

	public void createGui() {
		setTitle("Chương trình quản lý môn học");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		quanLy_MonHoc();
	}

	public void quanLy_MonHoc() {
		pnConTent = new JPanel();
		pnConTent.setLayout(new BorderLayout());

		pnCenter = new JPanel();
		pnConTent.add(pnCenter, BorderLayout.CENTER);
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		lbMaMon = new JLabel("Mã môn: ");
		txtMaMon = new JTextField(60);
		b1.add(lbMaMon);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtMaMon);

		lbTenMon = new JLabel("Tên môn: ");
		txtTenMon = new JTextField(60);
		b2.add(lbTenMon);
		b2.add(Box.createHorizontalStrut(15));
		b2.add(txtTenMon);

		lbHocKy = new JLabel("Học kỳ: ");
		cbHocKy = new JComboBox();
		cbHocKy.addItem("Học kỳ 1");
		cbHocKy.addItem("Học kỳ 2");
		cbHocKy.addItem("Học kỳ 3");
		b3.add(lbHocKy);
		b3.add(Box.createHorizontalStrut(25));
		b3.add(cbHocKy);

		lbSoTinChi = new JLabel("Số tín chỉ: ");
		txtSoTinChi = new JTextField(60);
		b4.add(lbSoTinChi);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txtSoTinChi);

//		lbMaMon.setPreferredSize(lbSoTinChi.getPreferredSize());
//		lbTenMon.setPreferredSize(lbSoTinChi.getPreferredSize());
//		lbHocKy.setPreferredSize(lbSoTinChi.getPreferredSize());

		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(b4);
		pnCenter.add(b);

		pnChucNang = new JPanel();
		pnConTent.add(pnChucNang, BorderLayout.SOUTH);
		pnChucNang.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		lbTim = new JLabel("Nhập mã môn cần tìm");
		txtTim = new JTextField(10);
		btnTim = new JButton("Tìm");

		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnLuu = new JButton("Lưu");
		pnChucNang.add(lbTim);
		pnChucNang.add(txtTim);
		pnChucNang.add(btnTim);
		pnChucNang.add(Box.createHorizontalStrut(20));
		pnChucNang.add(btnThem);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnXoaTrang);
		pnChucNang.add(btnLuu);

		this.add(pnConTent);
		createTable();

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
	}

	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã môn");
		model.addColumn("Tên môn");
		model.addColumn("Học kỳ");
		model.addColumn("Số tín chỉ");
		TableColumn columnHocKy = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Học kỳ 1");
		comboBox.addItem("Học kỳ 2");
		comboBox.addItem("Học kỳ 3");
		columnHocKy.setCellEditor(new DefaultCellEditor(comboBox));

//		String[] row = { "123", "JAVA", "Học kỳ 2", "4" };
//		model.addRow(row);

		pnTable.add(Box.createHorizontalStrut(65));
		pnTable.add(table);
		pnCenter.add(pnTable);
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(550, 350));
		pnTable.add(sp);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaMon.setText(model.getValueAt(row, 0).toString());
				txtTenMon.setText(model.getValueAt(row, 1).toString());
				txtSoTinChi.setText(model.getValueAt(row, 2).toString());
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
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (txtMaMon.getText().equals("") || txtTenMon.getText().equals("") || txtSoTinChi.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin");
			else if (!isInt(txtSoTinChi))
				JOptionPane.showMessageDialog(null, "Số tín chỉ phải là chữ số");
			else if (!isInt(txtMaMon))
				JOptionPane.showMessageDialog(null, "Mã môn phải là chữ số");
			try {
				luu();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			xoaTrang();
			txtMaMon.requestFocus();
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if(o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if(o.equals(btnTim)) {
			int pos = ds.timMonHoc(Integer.parseInt(txtTim.getText()));
			if(pos == -1) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy !");
			}
			else {
				table.setRowSelectionInterval(pos, pos);
			}
		}
		else if(o.equals(btnLuu)) {
			try {
				database.write_MH("MonHoc.txt", ds);
				JOptionPane.showMessageDialog(null, "Lưu thành công");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	private boolean isInt(JTextField text) {
		boolean result = true;
		try {
			Integer.parseInt(text.getText());
		} catch (NumberFormatException ex) {
			result = false;
		}
		return result;
	}

	public void luu() throws Exception {
		String ma = txtMaMon.getText();
		String ten = txtTenMon.getText();
		String hocKy = "";
		String soTinChi = txtSoTinChi.getText();
		hocKy = (String) cbHocKy.getSelectedItem();
		MonHoc mh = new MonHoc(Integer.parseInt(ma), ten, hocKy, Integer.parseInt(soTinChi));
		if (ds.themMonHoc(mh)) {
			JOptionPane.showMessageDialog(this, "Thêm thành công ! Vui lòng nhấn lưu");
			String[] row = { ma, ten, hocKy, soTinChi };
			model.addRow(row);
//			database.write_MH("MonHoc.txt", ds);
		} else {
			JOptionPane.showMessageDialog(this, "Trùng mã môn học !");
			txtMaMon.setText("");
			txtMaMon.requestFocus();
		}
	}

	public void xoaTrang() {
		txtMaMon.setText("");
		txtTenMon.setText("");
		txtSoTinChi.setText("");
		txtMaMon.requestFocus();
	}

	public void xoa() throws Exception{
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa dòng này không !", "Delete",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if(tb == JOptionPane.YES_OPTION);
			ds.xoaMonHoc(r);
			model.removeRow(r);
			xoaTrang();
//			database.write_MH("MonHoc.txt", ds);
		}else
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa !");
	}

	public static void main(String[] args) {
		new quanLy_MonHoc().setVisible(true);
	}
}
