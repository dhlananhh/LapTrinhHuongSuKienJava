package qlThongTinNV;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class NV_GUI extends JFrame implements ActionListener {
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblMaNV, lblTenNV, lblDiaChi, lblTuoi, lblEmail, lblTimKiem, lblError;
	private JTextField txtMaNV, txtTenNV, txtDiaChi, txtTuoi, txtEmail, txtTimKiem;
	private JButton btnThem, btnXoaTrang, btnXoa, btnLuu, btnTim;
	private DanhSachNhanVien ds;
	private NV_Database database;
	private DefaultTableModel model;
	private JTable table;
	
	
	public NV_GUI() {
		ds = new DanhSachNhanVien();
		database = new NV_Database();
		buildGUI();
		
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadData() throws Exception {
		ds = database.readFile("Nhanvien.txt");
		if (ds == null) {
			ds = new DanhSachNhanVien();
		} else {
			for (NhanVien nv : ds.getList()) {
				String[] row = {nv.getMaNV(), nv.getTenNV(), nv.getDiaChi(), nv.getTuoi() + "", nv.getEmail()};
				model.addRow(row);
			}
		}
	}

	
	public void buildGUI() {
		setTitle("Chương trình quản lý nhân viên");
		setSize(new Dimension(700, 700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		JLabel heading = new JLabel("THÔNG TIN NHÂN VIÊN");
		pnNorth.add(heading);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLUE);
		
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box bError = Box.createHorizontalBox();
		
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(20);
		b1.add(lblMaNV);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(txtMaNV);
		
		lblTenNV = new JLabel("Tên nhân viên: ");
		txtTenNV = new JTextField(40);
		b2.add(lblTenNV);
		b2.add(Box.createHorizontalStrut(50));
		b2.add(txtTenNV);
		
		lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField(40);
		b3.add(lblDiaChi);
		b3.add(Box.createHorizontalStrut(50));
		b3.add(txtDiaChi);
		
		lblTuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField(20);
		b4.add(lblTuoi);
		b4.add(Box.createHorizontalStrut(50));
		b4.add(txtTuoi);
		
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField(40);
		b5.add(lblEmail);
		b5.add(Box.createHorizontalStrut(50));
		b5.add(txtEmail);
		
		lblError = new JLabel(" ");
		bError.add(lblError);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Arial", Font.BOLD, 15));
		
		lblMaNV.setPreferredSize(lblTenNV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTenNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblTenNV.getPreferredSize());
		lblEmail.setPreferredSize(lblTenNV.getPreferredSize());
		
		pnCenter.add(b);
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b5);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(bError);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		
		createTable();
		
		pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createTitledBorder(null, "Chọn tác vụ"));
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		
		lblTimKiem = new JLabel("Tìm theo mã nhân viên: ");
		txtTimKiem = new JTextField(10);
		btnTim = new JButton("Tìm");
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Làm mới");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu File");
		
		pnSouth.add(lblTimKiem);
		pnSouth.add(txtTimKiem);
		pnSouth.add(btnTim);
		pnSouth.add(btnThem);
		pnSouth.add(btnXoaTrang);
		pnSouth.add(btnXoa);
		pnSouth.add(btnLuu);
		
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("Mã nhân viên");
		model.addColumn("Tên nhân viên");
		model.addColumn("Địa chỉ");
		model.addColumn("Tuổi");
		model.addColumn("Email");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		
		JScrollPane scrollPane = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(600, 230));
		
		pnCenter.add(scrollPane);
		pnCenter.add(pnTable);
		
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
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				txtMaNV.setText((String) model.getValueAt(row, 0));
				txtTenNV.setText((String) model.getValueAt(row, 1));
				txtDiaChi.setText((String) model.getValueAt(row, 2));
				txtTuoi.setText((String) model.getValueAt(row, 3));
				txtEmail.setText((String) model.getValueAt(row, 4));
			}
		});
	}
	
	
	/*
	private void showMessage (String message, JTextField text) {
		text.requestFocus();
		lblError.setText(message);
	}
	
	
	private boolean validData() {
		String maNV = txtMaNV.getText().trim();
		String tenNV = txtTenNV.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		String email = txtEmail.getText().trim();
		
		if (maNV.equals("") || tenNV.equals("") || tuoi.equals("") || email.equals("")) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin !");
			return false;
		}
		if (!maNV.matches("NV[0-9]{8}")) {
			showMessage("Mã nhân viên phải bắt đầu bằng 2 chữ cái “NV”, theo sau là 8 chữ số.", txtMaNV);
			return false;
		}
		if (!tenNV.matches("[A-Za-z ]+")) {
			showMessage("Tên nhân viên không được chứa các ký tự đặc biệt !", txtTenNV);
			return false;
		}
		if (!tuoi.matches("[0-9]{2}")) {
			showMessage("Tuổi nhân viên phải từ 18-60 tuổi.", txtTuoi);
			return false;
		}
		if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			showMessage("Email phải nhập theo tiêu chuẩn: tenEmail@DomainName.com", txtEmail);
			return false;
		}
		
		return true;
	}
	
	
	private NhanVien revertNhanVienFromJTextField() {
		String ma = txtMaNV.getText();
		String ten = txtTenNV.getText();
		String diaChi = txtDiaChi.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String email = txtEmail.getText();
		
		NhanVien nv = new NhanVien(ma, ten, diaChi, tuoi, email);
		
		return nv;
	}
	
	
	public void them() throws Exception {
		if (validData() == true) {
			NhanVien nv = revertNhanVienFromJTextField();
			if (!ds.themNhanVien(nv)) {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên !");
				txtMaNV.requestFocus();
			} else {
				String[] row = {nv.getMaNV(), nv.getTenNV(), nv.getDiaChi(), nv.getTuoi() + "", nv.getEmail()};
				model.addRow(row);
				lblError.setText("");
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công !");
			}
		}
	}
	*/
	
	
	private void showMessage (String message, JTextField text) {
		text.requestFocus();
		lblError.setText(message);
	}
	
	
	public boolean validData() {
		String ma = txtMaNV.getText().trim();
		String ten = txtTenNV.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		String email = txtEmail.getText().trim();
		
		if (txtMaNV.equals("") || txtTenNV.equals("") || txtDiaChi.equals("") || txtTuoi.equals("") || txtEmail.equals("")) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin !");
			return false;
		}
		if (!ma.matches("NV[0-9]{8}")) {
			showMessage("Mã nhân viên phải bắt đầu bằng 2 chữ cái “NV”, theo sau là 8 chữ số", txtMaNV);
			return false;
		}
		if (!ten.matches("[A-Za-z0-9 ]+")) {
			showMessage("Tên nhân viên có thể gồm nhiều từ ngăn cách bởi khoảng trắng, nhưng không được chứa các ký tự đặc biệt", txtTenNV);
			return false;
		}
		if (!diaChi.matches("[A-Za-z0-9 ]+" )) {
			showMessage("Địa chỉ có thể gồm nhiều từ ngăn cách bởi khoảng trắng, có thể chứa số, nhưng không được chứa các ký tự đặc biệt", txtDiaChi);
			return false;
		}
		if (!tuoi.matches("[0-9]{2}")) {
			showMessage("Tuổi nhân viên từ 18-60 tuổi mới hợp lệ", txtTuoi);
			return false;
		}
		if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._]+\\.[a-zA-Z]{2,}$")) {
			showMessage("Email phải nhập theo tiêu chuẩn: tenEmail@DomainName", txtEmail);
			return false;
		}
		return true;
	}
	
	
	public NhanVien revertNhanVienFromJTextField() {
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		String diaChi = txtDiaChi.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String email = txtEmail.getText();
		
		NhanVien nv = new NhanVien(maNV, tenNV, diaChi, tuoi, email);
		
		return nv;
	}
	
	
	public void them() throws Exception {
		if (validData() == true) {
			NhanVien nv = revertNhanVienFromJTextField();
			if (!ds.themNhanVien(nv)) {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên !");
				txtMaNV.requestFocus();
			} else {
				String[] row = {nv.getMaNV(), nv.getTenNV(), nv.getDiaChi(), nv.getTuoi() + "", nv.getEmail()};
				model.addRow(row);
				lblError.setText("");
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công !");
			}
		}
	}
	
	
	
	
	public void luu() throws Exception {
		try {
			database.writeFile("Nhanvien.txt", ds);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public void xoa() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = 	JOptionPane.showConfirmDialog(null, "Bạn có chắc là bạn muốn xóa dòng này không ?", "Delete",
						JOptionPane.YES_NO_CANCEL_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaNhanVienTaiViTri(r);
				model.removeRow(r);
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa !");
		}
	}
	
	
	public void xoaTrang() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtTuoi.setText("");
		txtEmail.setText("");
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				them();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnLuu)) {
			try {
				luu();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			int pos = ds.timNhanVien(txtTimKiem.getText());
			if (pos == -1) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy !");
			} else {
				table.setRowSelectionInterval(pos, pos);
			}
		}
	}
	
	
	public static void main(String[] args) {
		new NV_GUI().setVisible(true);
	}
}
