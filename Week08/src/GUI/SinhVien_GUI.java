package GUI;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.LopHoc_DAO;
import dao.SinhVien_DAO;
import entity.LopHoc;
import entity.SinhVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class SinhVien_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth, pnTable, pnBtn;
	private JLabel lblMSSV, lblTenSV, lblEmail, lblDiaChi;
	private JTextField txtMSSV, txtTenSV, txtEmail, txtDiaChi;
	private JButton btnThem, btnLuu, btnSua, btnXoa;
	private JButton btnFirst, btnLast, btnLeft, btnRight;
	private String maLop;
	private DefaultTableModel model;
	private JTable table;
	private SinhVien_DAO sinhVien;
//	private ArrayList<SinhVien> dssv;
	
	
	public SinhVien_GUI() throws Exception {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		sinhVien = new LopHoc_DAO();
		buildGUI();
//		setExtendedState(MAXIMIZED_BOTH);
		
//		dssv = new ArrayList<SinhVien>();
	}
	
	
	public void buildGUI() {
		setTitle("Thông tin sinh viên");
		setSize(new Dimension(700, 700));
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
		pnContent.add(pnNorth, BorderLayout.NORTH);
		JLabel heading = new JLabel("THÔNG TIN SINH VIÊN");
		pnNorth.add(heading);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLACK);
		
		
		//pnCenter
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		//pnInfo
		JPanel pnInfo = new JPanel();
		pnCenter.add(pnInfo);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		lblMSSV = new JLabel("Mã số sinh viên: ");
		txtMSSV = new JTextField(40);
		b1.add(lblMSSV);
		b1.add(txtMSSV);
		
		lblTenSV = new JLabel("Tên lớp: ");
		txtTenSV = new JTextField(40);
		b2.add(lblTenSV);
		b2.add(txtTenSV);
		
		lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField(40);
		b3.add(lblDiaChi);
		b3.add(txtDiaChi);
		
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField(40);
		b4.add(lblEmail);
		b4.add(txtEmail);
				
		lblTenSV.setPreferredSize(lblMSSV.getPreferredSize());
		lblEmail.setPreferredSize(lblMSSV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMSSV.getPreferredSize());
		
		pnInfo.add(b);
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		
		//pnBtn
		pnBtn = new JPanel();
		pnCenter.add(pnBtn);
				
		btnFirst = new JButton("<<");
		btnFirst.setForeground(new Color(33, 87, 50));
		btnFirst.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnLeft = new JButton("<");
		btnLeft.setForeground(new Color(33, 87, 50));
		btnLeft.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnRight = new JButton(">");
		btnRight.setForeground(new Color(33, 87, 50));
		btnRight.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnLast = new JButton(">>");
		btnLast.setForeground(new Color(33, 87, 50));
		btnLast.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnThem = new JButton("Thêm");
		btnLuu = new JButton("Lưu");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		
		pnBtn.add(btnFirst);
		pnBtn.add(btnLeft);
		pnBtn.add(btnRight);
		pnBtn.add(btnLast);
		pnBtn.add(Box.createHorizontalStrut(50));
		pnBtn.add(btnThem);
		pnBtn.add(btnLuu);
		pnBtn.add(btnSua);
		pnBtn.add(btnXoa);
		pnBtn.add(Box.createVerticalStrut(50));
	
		createTable();
		
		
		//pnSouth
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));

		
		//đăng ký sự kiện cho các button
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnFirst.addActionListener(this);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		btnLast.addActionListener(this);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã số sinh viên");
		model.addColumn("Tên sinh viên");
		model.addColumn("Địa chỉ");
		model.addColumn("Email");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên"));
		scrollPane.setPreferredSize(new Dimension(650, 330));
		
		pnCenter.add(scrollPane);
		pnCenter.add(pnTable);
		
		sinhVien = new SinhVien_DAO();
		table.setRowHeight(25);
		
		try {
			for (SinhVien sv : sinhVien.getAllSinhVien(maLop)) {
				Object[] row = {sv.getMaSV(), sv.getTenSV(), sv.getDiaChi(), sv.getEmail(), sv.getMaLop()};
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
				txtMSSV.setText((String) model.getValueAt(row, 0));
				txtTenSV.setText((String) model.getValueAt(row, 1));
				txtDiaChi.setText((String) model.getValueAt(row, 2));
				txtEmail.setText((String) model.getValueAt(row, 3));
			}
		});
	}
	
	
	public void moKhoaTxt() {
		if (btnSua.getText().equals("Hủy"))
			txtMSSV.setEditable(false);
		else
			txtMSSV.setEditable(true);
		
		txtTenSV.setEditable(true);
		txtDiaChi.setEditable(true);
		txtEmail.setEditable(true);
	}
	
	
	public void khoaTxt() {
		txtMSSV.setEditable(false);
		txtTenSV.setEditable(false);
		txtDiaChi.setEditable(false);
		txtEmail.setEditable(false);
	}
	
	
	public void reset() {
		khoaTxt();
		
		if (btnThem.getText().equals("Hủy")) {
			btnThem.setText("Thêm");
			btnLuu.setEnabled(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		}
		
		if (btnSua.getText().equals("Hủy")) {
			btnSua.setText("Sửa");
			btnLuu.setEnabled(false);
			btnThem.setEnabled(true);
			btnXoa.setEnabled(true);
		}
	}
		
	
	public LopHoc revertLopHocFromTextField() {
		String mssv = txtMSSV.getText();
		String ten = txtTenSV.getText();
		String diachi = txtDiaChi.getText();
		String email = txtEmail.getText();
		
//		return new SinhVien(mssv, ten, diachi, email, null)
		return null;
	}

	
	public void xoaTrang() {
		txtMSSV.setText("");
		txtTenSV.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				moKhoaTxt();
				btnThem.setText("Hủy");
				btnLuu.setEnabled(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
			} else {
				khoaTxt();
				btnThem.setText("Thêm");
				btnLuu.setEnabled(false);
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
			}
		} else if (o.equals(btnLuu)) {
			
		} else if (o.equals(btnSua)) {
			int row = table.getSelectedRow();
			
			if (row != -1) {
				if (btnSua.getText().equals("Sửa")) {
					btnSua.setText("Hủy");
					moKhoaTxt();
					btnLuu.setEnabled(true);
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
				} else {
					khoaTxt();
					btnSua.setText("Sửa");
					btnLuu.setEnabled(false);
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa !");
			}
		} else if (o.equals(btnXoa)) {
			
		}
	}
	
	
	/*
	else if(src.equals(btn_sua)) {
			int row = table.getSelectedRow();
			if(row != -1) {
				if(btn_sua.getText().equals("Sửa")) {
					btn_sua.setText("Hủy");
					moKhoatxt();
					btn_luu.setEnabled(true);
					btn_them.setEnabled(false);
					btn_xoa.setEnabled(false);
				}
				else {
					khoatxt();
					btn_sua.setText("Sửa");
					btn_luu.setEnabled(false);
					btn_them.setEnabled(true);
					btn_xoa.setEnabled(true);
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa!");
		}
	 */

	
	public static void main(String[] args) throws Exception {
		new SinhVien_GUI().setVisible(true);
	}
}
