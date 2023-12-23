package quanLyMayTinh;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class quanly_MAYTINH extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnSouth;
	private JLabel lblMaMay, lblTenMay, lblGia, lblLoaiMay, lblGhiChu, lblTimKiem;
	private JTextField txtMaMay, txtTenMay, txtGia, txtGhiChu, txtTimKiem;
	private JComboBox<String> cmbLoaiMay;
	private String[] loaiMay = {"Máy laptop", "Máy tính bảng", "Máy bàn"};
	private JButton btnThem, btnXoaTrang, btnXoa, btnLuu, btnTim;
	private DanhSachMayTinh ds;
	private MayTinh_Database database;
	private DefaultTableModel model;
	private JTable table;
	
	
	public quanly_MAYTINH() {
		super("Kiểm tra giữa kỳ - Dương Hoàng Lan Anh - 21087481 - DHKTPM17BTT");
		ds = new DanhSachMayTinh();
		database = new MayTinh_Database();
		buildGUI();
		
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadData() throws Exception {
		ds = database.readFile("Maytinh.txt");
		if (ds == null) {
			ds = new DanhSachMayTinh();
		} else {
			for (MayTinh mt : ds.getList()) {
				String[] row = {mt.getMaMay() + "", mt.getTenMay(), mt.getDonGia() + "", mt.getLoaiMay(), mt.getGhiChu()};
				model.addRow(row);
			}
		}
	}
	
	
	public void buildGUI() {
		setTitle("Kiểm tra giữa kỳ - Dương Hoàng Lan Anh - 21087481 - DHKTPM17BTT");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		new quanly_MAYTINH().setVisible(true);
	}
}
