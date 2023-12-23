package GUI;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.LopHoc_DAO;
import entity.LopHoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class LopHoc_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth, pnTable, pnBtn;
	private JLabel lblMaLop, lblTenLop, lblGVCN;
	private JTextField txtMaLop, txtTenLop, txtGVCN;
	private JButton btnThem, btnLuu, btnSua, btnXoa, btnXem;
	private JButton btnFirst, btnLast, btnLeft, btnRight;
	private DefaultTableModel model;
	private JTable table;
	private LopHoc_DAO lopHoc;
	private ArrayList<LopHoc> ds;
	
	
	public LopHoc_GUI() throws Exception {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		lopHoc = new LopHoc_DAO();
		buildGUI();
//		setExtendedState(MAXIMIZED_BOTH);
		
		ds = new ArrayList<LopHoc>();
	}
	
	
	public void buildGUI() {
		setTitle("Thông tin lớp học");
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
		JLabel heading = new JLabel("THÔNG TIN LỚP HỌC");
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
		
		lblMaLop = new JLabel("Mã lớp: ");
		txtMaLop = new JTextField(40);
		b1.add(lblMaLop);
		b1.add(txtMaLop);
		
		lblTenLop = new JLabel("Tên lớp: ");
		txtTenLop = new JTextField(40);
		b2.add(lblTenLop);
		b2.add(txtTenLop);
		
		lblGVCN = new JLabel("Giáo viên chủ nhiệm: ");
		txtGVCN = new JTextField(40);
		b3.add(lblGVCN);
		b3.add(txtGVCN);
				
		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());
		
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
		
		btnXem = new JButton("Xem danh sách sinh viên của lớp hiện tại");
		btnXem.setForeground(Color.RED);
		pnSouth.add(btnXem);
		
		
		//đăng ký sự kiện cho các button
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnFirst.addActionListener(this);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		btnLast.addActionListener(this);
		btnXem.addActionListener(this);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã lớp");
		model.addColumn("Tên lớp");
		model.addColumn("Giáo viên chủ nhiệm");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));
		scrollPane.setPreferredSize(new Dimension(650, 330));
		
		pnCenter.add(scrollPane);
		pnCenter.add(pnTable);
		
		lopHoc = new LopHoc_DAO();
		table.setRowHeight(25);
		
		try {
			for (LopHoc lh : lopHoc.getAllLopHoc()) {
				Object[] row = {lh.getMaLop(), lh.getTenLop(), lh.getGvcn()};
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
				txtMaLop.setText((String) model.getValueAt(row, 0));
				txtTenLop.setText((String) model.getValueAt(row, 1));
				txtGVCN.setText((String) model.getValueAt(row, 2));
			}
		});
	}
	
	
	public void moKhoaTxt() {
		if (btnSua.getText().equals("Hủy"))
			txtMaLop.setEditable(false);
		else
			txtMaLop.setEditable(true);
		
		txtTenLop.setEditable(true);
		txtGVCN.setEditable(true);
	}
	
	
	public void khoaTxt() {
		txtMaLop.setEditable(false);
		txtTenLop.setEditable(false);
		txtGVCN.setEditable(false);
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
		String maLop = txtMaLop.getText();
		String tenLop = txtTenLop.getText();
		String gvcn = txtGVCN.getText();
		
		return new LopHoc(maLop, tenLop, gvcn);
	}

	
	public void xoaTrang() {
		txtMaLop.setText("");
		txtTenLop.setText("");
		txtGVCN.setText("");
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
			int row = table.getSelectedRow();
			
			if (btnThem.getText().equals("Hủy")) {
				LopHoc lh = revertLopHocFromTextField();
				
				if (lopHoc.addLopHoc(lh)) {
					String[] data = {txtMaLop.getText(), txtTenLop.getText(), txtGVCN.getText()};
					model.addRow(data);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
			} else if (btnSua.getText().equals("Hủy")) {
				LopHoc lh = revertLopHocFromTextField();
				
				if (lopHoc.updateLopHoc(lh)) {
					String[] data = {txtMaLop.getText(), txtTenLop.getText(), txtGVCN.getText()};
					model.insertRow(row, data);
					model.removeRow(row+1);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
			}
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
			int row = table.getSelectedRow();
			
			if (row != -1) {
				int tb = 	JOptionPane.showConfirmDialog(this, "Bạn có chắc là bạn muốn xóa dòng này không ?", "Delete",
							JOptionPane.YES_NO_CANCEL_OPTION);
				String maLop = table.getValueAt(row, 0).toString();
				if (tb == JOptionPane.YES_OPTION) {
					if (lopHoc.deleteLopHoc(maLop)) {
						model.removeRow(row);
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xóa thành công !");
					} else {
						JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng muốn xóa !");
					}
				}
			}
		} else if (o.equals(btnXem)) {
			int row = table.getSelectedRow();
			
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		new LopHoc_GUI().setVisible(true);
	}
}
