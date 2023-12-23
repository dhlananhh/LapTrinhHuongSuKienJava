package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.BoMonChuQuan_DAO;
import dao.MonHoc_DAO;
import entity.BoMonChuQuan;
import entity.MonHoc;



public class ThongTinMonHoc_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblMaMonHoc, lblTenMonHoc, lblBoMonChuQuan, lblSoTiet;
	private JTextField txtMaMonHoc, txtTenMonHoc, txtSoTiet;
	private JComboBox cmbBoMonChuQuan;
	private JButton btnThemMoi, btnLuu, btnXoa, btnKetThuc, btnLoc;
	
	private JTable table;
	private DefaultTableModel model;
	
	private MonHoc_DAO monHoc;
	private BoMonChuQuan_DAO boMonChuQuan;
	
	private ArrayList<MonHoc> dsMonHoc;
	public ArrayList<BoMonChuQuan> dsBoMonChuQuan;
	
	
	public ThongTinMonHoc_GUI() {
		try {
//			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		monHoc = new MonHoc_DAO();
		boMonChuQuan = new BoMonChuQuan_DAO();
		buildGUI();
		dsMonHoc = new ArrayList<MonHoc>();
		dsBoMonChuQuan = new ArrayList<BoMonChuQuan>();
	}
	
	
	public void buildGUI() {
		setTitle("Bài thi Cuối kỳ - LT HSK Java");
		setSize(800, 700);
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
		JLabel heading = new JLabel("-THÔNG TIN MÔN HỌC-");
		pnNorth.add(heading);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLUE);
		
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
		Box b5 = Box.createHorizontalBox();
		
		lblMaMonHoc = new JLabel("Mã môn học: ");
		txtMaMonHoc = new JTextField(50);
		b1.add(lblMaMonHoc);
		b1.add(txtMaMonHoc);
		
		lblTenMonHoc = new JLabel("Tên môn học: ");
		txtTenMonHoc = new JTextField(50);
		b2.add(lblTenMonHoc);
		b2.add(txtTenMonHoc);
		
		lblBoMonChuQuan = new JLabel("Bộ Môn Chủ Quản: ");
		cmbBoMonChuQuan = new JComboBox<>();
		b3.add(lblBoMonChuQuan);
		b3.add(cmbBoMonChuQuan);
		
		lblSoTiet = new JLabel("Số tiết: ");
		txtSoTiet = new JTextField(50);
		b4.add(lblSoTiet);
		b4.add(txtSoTiet);
		
		JPanel pnBtn = new JPanel();
		btnThemMoi = new JButton("Thêm mới");
		btnLuu = new JButton("Lưu");
		btnXoa = new JButton("Xóa");
		btnKetThuc = new JButton("Kết thúc");
		pnBtn.add(btnThemMoi);
		pnBtn.add(btnLuu);
		pnBtn.add(btnXoa);
		pnBtn.add(Box.createHorizontalStrut(50));
		pnBtn.add(btnKetThuc);
		b5.add(pnBtn);
		
		lblMaMonHoc.setPreferredSize(lblBoMonChuQuan.getPreferredSize());
		lblTenMonHoc.setPreferredSize(lblBoMonChuQuan.getPreferredSize());
		lblSoTiet.setPreferredSize(lblBoMonChuQuan.getPreferredSize());
		
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
		
		createTable();
		
		//pnSouth
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		
		JPanel pnMyInfo = new JPanel();
		pnSouth.add(pnMyInfo);
		JLabel lblMyInfo = new JLabel("Họ tên: Dương Hoàng Lan Anh - MSSV: 21087481");
		pnMyInfo.add(lblMyInfo);
		lblMyInfo.setFont(new FontUIResource("Times New Roman", Font.ITALIC, 15));
		lblMyInfo.setForeground(Color.RED);
		
		pnSouth.add(Box.createHorizontalStrut(50));
		btnLoc = new JButton("Lọc danh sách Môn Học theo Bộ Môn Chủ Quản");
		pnSouth.add(btnLoc);
		btnLoc.setFont(new FontUIResource("Times New Roman", Font.ITALIC, 15));
		btnLoc.setForeground(Color.BLUE);
		
		btnThemMoi.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		btnKetThuc.addActionListener(this);
		btnLoc.addActionListener(this);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		pnCenter.add(pnTable);
		pnTable.setBorder(BorderFactory.createTitledBorder(null, "Danh sách Môn Học: "));
		
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("Mã Môn Học");
		model.addColumn("Tên Môn Học");
		model.addColumn("Số Tiết");
		model.addColumn("Mã Số Bộ Môn Chủ Quản");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(300);
		
		JScrollPane scrollPane = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(700, 300));
		
		pnTable.add(scrollPane);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new ThongTinMonHoc_GUI().setVisible(true);
	}
}
