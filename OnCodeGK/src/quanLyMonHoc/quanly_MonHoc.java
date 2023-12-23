package quanLyMonHoc;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class quanly_MonHoc extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblMaMon, lblTenMon, lblHocKy, lblSoTinChi, lblTimKiem;
	private JTextField txtMaMon, txtTenMon, txtHocKy, txtSoTinChi, txtTimKiem;
	private JComboBox<String> cmbHocKy;
	private String[] hocKy = {"Học kỳ 1", "Học kỳ 2", "Học kỳ 3"};
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim;
	private DanhSachMonHoc ds;
	private MonHoc_Database database;
	private DefaultTableModel model;
	private JTable table;
	
	
	public quanly_MonHoc() {
		super("Chương trình quản lý môn học");
		database = new MonHoc_Database();
		ds = new DanhSachMonHoc();
		buildGUI();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadData() throws Exception {
		ds = database.readFile("Monhoc.txt");
		if (ds == null) {
			ds = new DanhSachMonHoc();
		} else {
			for (MonHoc mh : ds.getList()) {
				String[] row = {mh.getMaMon() + "", mh.getTenMon(), mh.getHocKy(), mh.getSoTinChi() + ""};
				model.addRow(row);
			}
		}
	}
	
	
	public void buildGUI() {
		setTitle("Chương trình quản lý môn học");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		//pnContent
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnNorth
		pnNorth = new JPanel();
		pnNorth.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel heading = new JLabel("Chương trình quản lý môn học");
		pnNorth.add(heading);
		pnContent.add(pnNorth, BorderLayout.NORTH);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLUE);
		
		//pnCenter
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		JPanel pnInfo = new JPanel();
		pnCenter.add(pnInfo);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		//row 1
		lblMaMon = new JLabel("Mã môn: ");
		txtMaMon = new JTextField(50);
		b1.add(lblMaMon);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(txtMaMon);
		
		//row 2
		lblTenMon = new JLabel("Tên môn: ");
		txtTenMon = new JTextField(50);
		b2.add(lblTenMon);
		b2.add(Box.createHorizontalStrut(50));
		b2.add(txtTenMon);
		
		//row 3
		lblHocKy = new JLabel("Học kỳ: ");
		cmbHocKy = new JComboBox<String>();
		for (int i=0; i<hocKy.length; i++) {
			cmbHocKy.addItem(hocKy[i]);
		}
		b3.add(lblHocKy);
		b3.add(Box.createHorizontalStrut(50));
		b3.add(cmbHocKy);
		
		//row 4
		lblSoTinChi = new JLabel("Số tín chỉ: ");
		txtSoTinChi = new JTextField(50);
		b4.add(lblSoTinChi);
		b4.add(Box.createHorizontalStrut(50));
		b4.add(txtSoTinChi);
		
		lblMaMon.setPreferredSize(lblSoTinChi.getPreferredSize());
		lblTenMon.setPreferredSize(lblSoTinChi.getPreferredSize());
		lblHocKy.setPreferredSize(lblSoTinChi.getPreferredSize());
		
		
		pnInfo.add(b);
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0,  20)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0,  20)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0,  20)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0,  20)));
		
		createTable();
		
		pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createTitledBorder(null, "Chọn tác vụ"));
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		
		lblTimKiem = new JLabel("Nhập mã môn cần tìm: ");
		txtTimKiem = new JTextField(10);
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnLuu = new JButton("Lưu");
		btnTim = new JButton("Tìm");
		
		pnSouth.add(lblTimKiem);
		pnSouth.add(txtTimKiem);
		pnSouth.add(btnTim);
		pnSouth.add(btnThem);
		pnSouth.add(btnXoa);
		pnSouth.add(btnXoaTrang);
		pnSouth.add(btnLuu);
		
		Container container = getContentPane();
		container.add(pnContent);
		
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
				int row = table.getSelectedRow();
				txtMaMon.setText((String)model.getValueAt(row, 0));
				txtTenMon.setText((String)model.getValueAt(row, 1));
				for (int i=0; i<hocKy.length; i++) {
					if (cmbHocKy.getItemAt(i).equalsIgnoreCase((String)model.getValueAt(row, 2))) {
						comboBox.setSelectedIndex(i);
					}
				}
				txtSoTinChi.setText((String)model.getValueAt(row, 3));
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
		} else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnLuu)) {
			try {
				database.writeFile("Monhoc.txt", ds);
				JOptionPane.showMessageDialog(null, "Lưu thành công !");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			int pos = ds.timMonHoc(Integer.parseInt(txtTimKiem.getText()));
			if (pos == -1) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy !");
			} else {
				table.setRowSelectionInterval(pos, pos);
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private boolean isInt (JTextField text) {
		boolean result = true;
		try {
			Integer.parseInt(text.getText());
		} catch (NumberFormatException ex) {
			result = false;
		}
		return result;
	}
	
	
	public void xoaTrang() {
		txtMaMon.setText("");
		txtTenMon.setText("");
		txtHocKy.setText("");
		txtSoTinChi.setText("");
	}
	
	
	public void luu() throws Exception {
		String ma = txtMaMon.getText();
		String ten = txtTenMon.getText();
		String hocKy = "";
		String soTinChi = txtSoTinChi.getText();
		hocKy = (String) cmbHocKy.getSelectedItem();
		MonHoc mh = new MonHoc(Integer.parseInt(ma), ten, hocKy, Integer.parseInt(soTinChi));
		
		if (ds.themMonHoc(mh)) {
			JOptionPane.showMessageDialog(this, "Thêm thành công ! Vui lòng nhấn nút lưu");
			String[] row = {ma, ten, hocKy, soTinChi};
			model.addRow(row);
		} else {
			JOptionPane.showMessageDialog(this, "Trùng mã môn học !");
			txtMaMon.setText("");
			txtMaMon.requestFocus();
		}
	}
	
	
	public void xoa() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = 	JOptionPane.showConfirmDialog(null, "Bạn có chắc là bạn muốn xóa dòng này không ?", "Delete", 
						JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaMonHoc(r);
				model.removeRow(r);
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa !");
		}
	}
	
	
	public static void main(String[] args) {
		new quanly_MonHoc().setVisible(true);
	}
}
