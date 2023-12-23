package demoGUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class ThongTinNhanVien extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnCenter;
	private JLabel lblMaNV, lblHo, lblTen, lblTuoi, lblPhai, lblTienLuong, lblTimKiem;
	private JTextField txtMaNV, txtHo, txtTen, txtTuoi, txtTienLuong, txtTimKiem;
	private JRadioButton radNam, radNu;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnLuu;
	private DefaultTableModel model;
	private JTable table;
	
	
	public ThongTinNhanVien() {
		setTitle("^-^");
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
		JPanel pnNorth = new JPanel();
		pnNorth.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel heading = new JLabel("THÔNG TIN NHÂN VIÊN");
		pnNorth.add(heading);
		pnContent.add(pnNorth, BorderLayout.NORTH);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLUE);
		
		// ** panel center **
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		// panel center chia làm 2 phần: pnTop và pnBottom
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
		
		// thêm các box vào panel center
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
		pnChucNang.add(btnXoaTrang);
		pnChucNang.add(btnXoa);
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
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		new ThongTinNhanVien().setVisible(true);
	}
}
