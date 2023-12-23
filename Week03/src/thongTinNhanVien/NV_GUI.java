package thongTinNhanVien;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class NV_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter;
	private JLabel lblMaNV, lblHo, lblTen, lblTuoi, lblPhai, lblTienLuong, lblTimKiem;
	private JTextField txtMaNV, txtHo, txtTen, txtTuoi, txtTienLuong, txtTimKiem;
	private JRadioButton radNam, radNu;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnLuu;
	private DefaultTableModel model;
	private JTable table;
	private DanhSachNhanVien ds;
	private NV_Database database;
	
	
	public NV_GUI() {
		super("Thông tin Nhân viên");
		database = new NV_Database();
		ds = new DanhSachNhanVien();
		buildGUI();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadData() throws Exception {
		ds = database.readNV("Nhanvien.txt");
		if (ds == null) {
			ds = new DanhSachNhanVien();
		} else {
			for (NhanVien nv : ds.getList()) {
				String[] row = {nv.getMaNV(), nv.getHoNV(), nv.getTenNV(), nv.getPhai(), nv.getTuoi() + "", nv.getTienLuong() + ""};
				model.addRow(row);
			}
		}
	}
	
	
	public void buildGUI() {
		setTitle("Thông tin Nhân viên");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		// ** panel content **
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
				
		// ** panel north **
		// tạo heading và thêm vào north panel
		pnNorth = new JPanel();
		pnNorth.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel heading = new JLabel("THÔNG TIN NHÂN VIÊN");
		pnNorth.add(heading);
		pnContent.add(pnNorth, BorderLayout.NORTH);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLUE);
		
		// ** panel center **
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		// panel center chia làm 2 phần: pnInfo và pnTable
		// pnInfo chứa các thông tin nhập của nhân viên
		JPanel pnInfo = new JPanel();
		pnCenter.add(pnInfo);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		//row 1
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(60);
		b1.add(lblMaNV);
		b1.add(txtMaNV);
		
		//row 2
		lblHo = new JLabel("Họ: ");
		txtHo = new JTextField(20);
		lblTen = new JLabel(" Tên nhân viên: ");
		txtTen = new JTextField(20);
		lblHo.setPreferredSize(lblMaNV.getPreferredSize());
		b2.add(lblHo);
		b2.add(txtHo);
		b2.add(lblTen);
		b2.add(txtTen);
		
		//row 3
		lblTuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField(20);
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());
		b3.add(lblTuoi);
		b3.add(txtTuoi);
		lblPhai = new JLabel(" Phái: ");
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		ButtonGroup group = new ButtonGroup();
		group.add(radNam);
		group.add(radNu);
		b3.add(lblPhai);
		b3.add(radNam);
		b3.add(radNu);
		
		//row 4
		lblTienLuong = new JLabel("Tiền lương: ");
		txtTienLuong = new JTextField(60);
		lblTienLuong.setPreferredSize(lblMaNV.getPreferredSize());
		b4.add(lblTienLuong);
		b4.add(txtTienLuong);
		
		// thêm các box vào panel info
		pnInfo.add(b);
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b4);
		
		createTable();
				
		// ** panel south **
		JPanel pnSouth = new JPanel();
		
		// tạo split pane
		JSplitPane splitPane;
		pnContent.add(splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.SOUTH);
		splitPane.setResizeWeight(0.5);
		
		// tạo pnTimKiem và thêm các components vào
		JPanel pnTimKiem = new JPanel();
		lblTimKiem = new JLabel("Nhập mã số cần tìm: ");
		txtTimKiem = new JTextField(10);
		btnTim = new JButton("Tìm");
		
		pnTimKiem.add(lblTimKiem);
		pnTimKiem.add(txtTimKiem);
		pnTimKiem.add(btnTim);
		splitPane.add(pnTimKiem);
		
		// tạo pnChucNang
		JPanel pnChucNang = new JPanel();
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		
		pnChucNang.add(btnThem);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnXoaTrang);
		pnChucNang.add(btnLuu);
		splitPane.add(pnChucNang);
		
		// set border cho pnChucNang và pnTimKiem
		Border subBorder = BorderFactory.createLineBorder(Color.GRAY);
		pnChucNang.setBorder(subBorder);
		pnTimKiem.setBorder(subBorder);
		
		// ** create container **
		Container container = getContentPane();
		container.add(pnContent);
		
		// ** subscribe ActionListener **
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã NV");
		model.addColumn("Họ");
		model.addColumn("Tên");
		model.addColumn("Phái");
		model.addColumn("Tuổi");
		model.addColumn("Tiền lương");
		TableColumn columnPhai = table.getColumnModel().getColumn(3);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Nam");
		comboBox.addItem("Nữ");
		columnPhai.setCellEditor(new DefaultCellEditor(comboBox));
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
												JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(650, 250));
		pnCenter.add(scrollPane);
		pnCenter.add(pnTable);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked (MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaNV.setText(model.getValueAt(row, 0).toString());
				txtHo.setText(model.getValueAt(row, 1).toString());
				txtTen.setText(model.getValueAt(row, 2).toString());
				if (model.getValueAt(row, 3).toString().equalsIgnoreCase("Nam")) {
					radNam.setSelected(true);
					radNu.setSelected(false);
				} else {
					radNu.setSelected(true);
					radNam.setSelected(false);
				}
				txtTuoi.setText(model.getValueAt(row, 4).toString());
				txtTienLuong.setText(model.getValueAt(row, 5).toString());
			}
			
			
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
		});
	}
	
	/*
	if (txtMaNV.getText().equals("") || txtHo.getText().equals("") || txtTen.getText().equals("") || txtTuoi.getText().equals("") || txtTienLuong.getText().equals(""))
		JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin !");
//	else if (!isInt(txtTuoi))
//		JOptionPane.showMessageDialog(null, "Tuổi phải là chữ số !");
	try {
		luu();
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	xoaTrang();
	txtMaNV.requestFocus();
	*/
		
	@Override
	public void actionPerformed (ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				btnXoa.setEnabled(false);
				btnThem.setText("Hủy");
			} else {
				btnXoa.setEnabled(true);
				btnThem.setText("Thêm");
			}
		} else if (o.equals(btnLuu)) {
			if (txtMaNV.getText().equals("") || txtHo.getText().equals("") || txtTen.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
			} else {
				try {
					luu();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			xoaTrang();
			txtMaNV.requestFocus();
			this.btnThem.setText("Thêm");
			this.btnXoa.setEnabled(true);
		} else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnTim)) {
			int pos = ds.timKiemNV(this.txtTimKiem.getText());
			if (pos == -1) {
				JOptionPane.showMessageDialog(this, "Không tồn tại nhân viên có mã này !");
			} else {
				table.setRowSelectionInterval(pos, pos);
			}
		}
	}
	
	
	public void luu() throws Exception {
		String gt = "";
		String ma = txtMaNV.getText();
		String ho = txtHo.getText();
		String ten = txtTen.getText();
		if (radNam.isSelected())
			gt = radNam.getText();
		if (radNu.isSelected())
			gt = radNu.getText();
		String tuoi = txtTuoi.getText();
		String luong = txtTienLuong.getText();
		
		NhanVien nv = new NhanVien(ma, ho, ten, gt, Integer.parseInt(tuoi), Double.parseDouble(luong));
		if (ds.themNV(nv)) {
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			String[] row = {ma, ho, ten, gt, tuoi, luong};
			model.addRow(row);
			
			database.writeNV("NhanVien.txt", ds);
//			database.saveFile("NhanVien.dat", ds);
		} else {
			JOptionPane.showMessageDialog(this, "ID này tồn tại");
			txtMaNV.setText("");
			txtMaNV.requestFocus();
		}
	}
	
	
	public void xoa() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này không ?", "Delete", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaNVViTri(r);
				model.removeRow(r);
				xoaTrang();
//				database.saveFile("NhanVien.dat", ds);
				database.writeNV("Nhanvien.txt", ds);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa !");
		}
	}
	
	
	public void save() throws Exception {
		database.writeNV("NhanVien.txt", ds);
	}
	
	
	public void xoaTrang() {
		txtMaNV.setText("");
		txtHo.setText("");
		txtTen.setText("");
		radNam.setSelected(false);
		radNu.setSelected(false);
		txtTuoi.setText("");
		txtTienLuong.setText("");
		txtMaNV.requestFocus();
	}
	
	
	public static void main(String[] args) {
		new NV_GUI().setVisible(true);
	}
}
