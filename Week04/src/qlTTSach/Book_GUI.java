package qlTTSach;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



public class Book_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblMaSach, lblTuaSach, lblNamXB, lblSoTrang, lblISBN, lblTacGia, lblNhaXB, lblDonGia, lblTimMaSach, lblError;
	private JTextField txtMaSach, txtTuaSach, txtNamXB, txtSoTrang, txtISBN, txtTacGia, txtNhaXB, txtDonGia;
	private JButton btnThem, btnXoaRong, btnSua, btnXoa, btnLuu;
	private JComboBox<String> cmbMaSach;
	private DefaultTableModel model;
	private JTable table;
	private DanhMucSach ds;
	private BookDatabase database;
	
	
	public Book_GUI() {
		super("Quản lý sách");
		database = new BookDatabase();
		ds = new DanhMucSach();
		buildGUI();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadData() throws Exception {
		ds = database.readFile("Books.txt");
		if (ds == null) {
			ds = new DanhMucSach();
		} else {
			for (Sach s : ds.getList()) {
//				Sach sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
				String[] row = {s.getMaSach(), s.getTuaSach(), s.getTacGia(), s.getNhaXuatBan(), s.getISBN(), s.getSoTrang() + "", s.getNamXuatBan() + "", s.getDonGia() + ""};
				model.addRow(row);
			}
		}
	}
	
	
	public void buildGUI() {
		setTitle("Quản lý sách");
		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		// pnContent
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnNorth
		pnNorth = new JPanel();
		
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));
		pnContent.add(pnNorth, BorderLayout.NORTH);
		
		JPanel pnInfo = new JPanel();
		pnInfo.setLayout(new GridLayout());
		pnInfo.setBorder(BorderFactory.createTitledBorder(null, "Records Editor"));
		pnNorth.add(pnInfo);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		
		lblMaSach = new JLabel("Mã sách: ");
		txtMaSach = new JTextField(15);
		b1.add(lblMaSach);
		b1.add(txtMaSach);
		b1.add(Box.createHorizontalStrut(600));
		
		lblTuaSach = new JLabel("Tựa sách: ");
		txtTuaSach = new JTextField(15);
		lblTacGia = new JLabel("Tác giả: ");
		txtTacGia = new JTextField(15);
		b2.add(lblTuaSach);
		b2.add(txtTuaSach);
		b2.add(Box.createHorizontalStrut(40));
		b2.add(lblTacGia);
		b2.add(txtTacGia);
		
		lblNamXB = new JLabel("Năm xuất bản: ");
		txtNamXB = new JTextField(15);
		lblNhaXB = new JLabel("Nhà xuất bản: ");
		txtNhaXB = new JTextField(15);
		b3.add(lblNamXB);
		b3.add(txtNamXB);
		b3.add(Box.createHorizontalStrut(40));
		b3.add(lblNhaXB);
		b3.add(txtNhaXB);
		
		lblSoTrang = new JLabel("Số trang: ");
		txtSoTrang = new JTextField(15);
		lblDonGia = new JLabel("Đơn giá: ");
		txtDonGia = new JTextField(15);
		b4.add(lblSoTrang);
		b4.add(txtSoTrang);
		b4.add(Box.createHorizontalStrut(40));
		b4.add(lblDonGia);
		b4.add(txtDonGia);
		
		lblISBN = new JLabel("International Standard Book Number (ISBN): ");
		txtISBN = new JTextField(15);
		b5.add(lblISBN);
		b5.add(txtISBN);
		b5.add(Box.createHorizontalStrut(505));
		
		
		lblMaSach.setPreferredSize(lblNamXB.getPreferredSize());
		lblTuaSach.setPreferredSize(lblNamXB.getPreferredSize());
		lblSoTrang.setPreferredSize(lblNamXB.getPreferredSize());
		lblTacGia.setPreferredSize(lblNhaXB.getPreferredSize());
		lblDonGia.setPreferredSize(lblNhaXB.getPreferredSize());
		
		// thêm các box vào pnInfo
		pnInfo.add(b);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b5);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		
		//pnCenter
		pnCenter = new JPanel();
		pnCenter.add(Box.createRigidArea(new Dimension(0, 30)));
		pnContent.add(pnCenter, BorderLayout.CENTER);
				
		btnThem = new JButton("Thêm");
		btnXoaRong = new JButton("Xóa rỗng");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		lblTimMaSach = new JLabel("Tìm theo mã sách");
		cmbMaSach = new JComboBox<String>();
		
		pnCenter.add(btnThem);
		pnCenter.add(btnXoaRong);
		pnCenter.add(btnSua);
		pnCenter.add(btnXoa);
		pnCenter.add(btnLuu);
		pnCenter.add(Box.createHorizontalStrut(50));
		pnCenter.add(lblTimMaSach);
		pnCenter.add(cmbMaSach);
		
		//pnSouth
		pnSouth = new JPanel();
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.X_AXIS));
		pnSouth.setBorder(BorderFactory.createTitledBorder(null, "Danh sách các cuốn sách"));
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		createTable();
				
		// create container
		Container container = getContentPane();
		container.add(pnContent);
		
		// subscribe ActionListener
		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		cmbMaSach.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedMaSach = (String) cmbMaSach.getSelectedItem();
					Sach selectedSach = ds.timKiemSach(selectedMaSach);
					if(selectedSach!=null) {
						napDuLieu(selectedSach);
					}
				
				}
			}
		});
	}
	
	
	private void napDuLieu (Sach sach) {
		txtMaSach.setText(sach.getMaSach());
		txtTuaSach.setText(sach.getTuaSach());
		txtTacGia.setText(sach.getTacGia());
		txtNamXB.setText(sach.getNamXuatBan() + "");
		txtNhaXB.setText(sach.getNhaXuatBan());
		txtSoTrang.setText(sach.getSoTrang() + "");
		txtDonGia.setText(sach.getDonGia() + "");
		txtISBN.setText(sach.getISBN());
		cmbMaSach.setSelectedItem(sach.getMaSach());
		txtMaSach.requestFocus();

	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
//		Sach sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
		model.addColumn("Mã sách");
		model.addColumn("Tựa sách");
		model.addColumn("Tác giả");
		model.addColumn("Nhà xuất bản");
		model.addColumn("ISBN");
		model.addColumn("Số trang");
		model.addColumn("Năm xuất bản");
		model.addColumn("Đơn giá");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(200);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
												JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(1000, 300));
		pnSouth.add(scrollPane);
		pnSouth.add(pnTable);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaSach.setText((String) model.getValueAt(row, 0));
				txtTuaSach.setText((String) model.getValueAt(row, 1));
				txtTacGia.setText((String) model.getValueAt(row, 1));
				txtNamXB.setText((String) model.getValueAt(row, 2));
				txtNhaXB.setText((String) model.getValueAt(row, 2));
				txtSoTrang.setText((String) model.getValueAt(row, 3));
				txtDonGia.setText((String) model.getValueAt(row, 3));
				txtISBN.setText((String) model.getValueAt(row, 4));
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
	
	
	private void showMessege (String message, JTextField txt) {
		lblError.setText(message);
		txt.requestFocus();
	}
	
	
	private Sach revertSachFromTextField() {
		Sach sach = null;
		String maSach, tuaSach, tacGia, nhaXB, ISBN;
		int soTrang, namXB;
		double donGia;
		maSach = txtMaSach.getText();
		tuaSach = txtTuaSach.getText();
		tacGia = txtTacGia.getText();
		namXB = Integer.parseInt(txtNamXB.getText());
		nhaXB = txtNhaXB.getText();
		soTrang = Integer.parseInt(txtSoTrang.getText());
		donGia = Double.parseDouble(txtDonGia.getText());
		ISBN = txtISBN.getText();
		sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
		return sach;

	}
	
	
	@SuppressWarnings("unused")
	private boolean isDouble (JTextField text) {
		boolean result = true;
		try {
			Double.parseDouble(text.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void xoaTrang() {
		txtMaSach.setText("");
		txtTuaSach.setText("");
		txtTacGia.setText("");
		txtNamXB.setText("");
		txtNhaXB.setText("");
		txtSoTrang.setText("");
		txtDonGia.setText("");
		txtISBN.setText("");
	}
	
	
	public void them() throws Exception {
		String maSach = txtMaSach.getText();
		String tuaSach = txtTuaSach.getText();
		String tacGia = txtTacGia.getText();
		String namXB = txtNamXB.getText();
		String nhaXB = txtNhaXB.getText();
		String soTrang = txtSoTrang.getText();
		String donGia = txtDonGia.getText();
		String ISBN = txtISBN.getText();
		
//		public Sach(int maSach, String tuaSach, int namXuatBan, int soTrang, String ISBN, String tacGia,
//				String nhaXuatBan, double donGia) 
		
		Sach sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, Integer.parseInt(soTrang), Integer.parseInt(namXB), Double.parseDouble(donGia));
		
		if (ds.themSach(sach)) {
//			JOptionPane.showMessageDialog(this, "Thêm thành công !");
			String[] row = {maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia};
			model.addRow(row);
		} else {
			JOptionPane.showMessageDialog(this, "Trùng mã sách !");
			txtMaSach.setText("");
			txtMaSach.requestFocus();
		}
	}
	
	/*
	//viết hàm sửa sách
	public void sua() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			String maSach = txtMaSach.getText();
			String tenSach = txtTuaSach.getText();
			String tacGia = txtTacGia.getText();
			String namXB = txtNamXB.getText();
			String nhaXB = txtNhaXB.getText();
			String soTrang = txtSoTrang.getText();
			String gia = txtDonGia.getText();
			String ISBN = txtISBN.getText();
			
			Sach sach = new Sach(Integer.parseInt(maSach), tenSach, Integer.parseInt(namXB), Integer.parseInt(soTrang), ISBN, tacGia, nhaXB, Double.parseDouble(gia));
			
			if (ds.suaSach(sach)) {
				JOptionPane.showMessageDialog(this, "Sửa thành công !");
				model.setValueAt(maSach, r, 0);
				model.setValueAt(tenSach, r, 1);
				model.setValueAt(tacGia, r, 2);
				model.setValueAt(namXB, r, 3);
				model.setValueAt(nhaXB, r, 4);
				model.setValueAt(soTrang, r, 5);
				model.setValueAt(gia, r, 6);
				model.setValueAt(ISBN, r, 7);
			} else {
				JOptionPane.showMessageDialog(this, "Sửa thất bại !");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng muốn sửa !");
			txtMaSach.setText("");
			txtMaSach.requestFocus();
		}
	}
	*/
	
	
	public void xoa() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = 	JOptionPane.showConfirmDialog(null, "Bạn có chắc là bạn muốn xóa dòng này không ?", "Delete",
						JOptionPane.YES_NO_CANCEL_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaSachTaiViTri(r);
				model.removeRow(r);
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa !");
			txtMaSach.setText("");
			txtMaSach.requestFocus();
		}
	}
	
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				them();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnXoaRong)) {
			xoaTrang();
		} else if (o.equals(btnSua)) {
			try {
//				sua();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnLuu)) {
			try {
				them();
				database.writeFile("Books.txt", ds);
				JOptionPane.showMessageDialog(null, "Lưu file thành công !");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		new Book_GUI().setVisible(true);
	}
}
