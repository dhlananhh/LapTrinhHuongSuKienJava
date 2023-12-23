package gui;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.CauThu_DAO;
import dao.ViTriThiDau_DAO;
import entity.CauThu;
import entity.ViTriThiDau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ThongTinCauThu_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblMaSoCauThu, lblTenCauThu, lblViTriThiDau, lblTuoi;
	private JTextField txtMaSoCauThu, txtTenCauThu, txtViTriThiDau, txtTuoi;
	private JButton btnThemMoi, btnLuu, btnXoa, btnKetThuc, btnLoc;
	private JComboBox cmbViTriThiDau;
	
	private JTable table;
	private DefaultTableModel model;
	
	private CauThu_DAO cauThu;
	private ViTriThiDau_DAO viTriThiDau;
	
	private ArrayList<CauThu> dsCauThu;
	private ArrayList<ViTriThiDau> dsViTriThiDau;
	
	
	public ThongTinCauThu_GUI() {
		try {
//			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		buildGUI();
		
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
		JLabel heading = new JLabel("-THÔNG TIN CẦU THỦ-");
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
		
		lblMaSoCauThu = new JLabel("Mã số cầu thủ: ");
		txtMaSoCauThu = new JTextField(50);
		b1.add(lblMaSoCauThu);
		b1.add(txtMaSoCauThu);
		
		lblTenCauThu = new JLabel("Tên cầu thủ: ");
		txtTenCauThu = new JTextField(50);
		b2.add(lblTenCauThu);
		b2.add(txtTenCauThu);
		
		lblViTriThiDau = new JLabel("Vị trí thi đấu: ");
		cmbViTriThiDau = new JComboBox<>();
		b3.add(lblViTriThiDau);
		b3.add(cmbViTriThiDau);
		
		lblTuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField(50);
		b4.add(lblTuoi);
		b4.add(txtTuoi);
		
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
		
		lblTenCauThu.setPreferredSize(lblMaSoCauThu.getPreferredSize());
		lblViTriThiDau.setPreferredSize(lblMaSoCauThu.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaSoCauThu.getPreferredSize());
		
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
		btnLoc = new JButton("Lọc danh sách cầu thủ theo vị trí thi đấu");
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
		pnTable.setBorder(BorderFactory.createTitledBorder(null, "Danh sách cầu thủ: "));
		
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("Mã Số");
		model.addColumn("Tên cầu thủ");
		model.addColumn("Tuổi");
		model.addColumn("Vị trí thi đấu");
		
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
		new ThongTinCauThu_GUI().setVisible(true);
	}
}
