package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.LopHoc_DAO;
import dao.SinhVien_DAO;
import entity.LopHoc;
import entity.SinhVien;

public class quanlySinhVien extends JFrame implements ActionListener{
	
	private JTextField txt_masv;
	private JTextField txt_hoten;
	private JTextField txt_email;
	private JTextField txt_dc;
	private JButton btn_1;
	private JButton btn_2;
	private JButton btn_3;
	private JButton btn_4;
	private JButton btn_them;
	private JButton btn_luu;
	private JButton btn_sua;
	private JButton btn_xoa;
	private JTable table;
	private SinhVien_DAO sinhvien;
	private DefaultTableModel model;
	private String maLopFK;
	private LopHoc_DAO dslop;
	public quanlySinhVien(String maLop) {
		try {
			ConnectDB.getInstance().connect();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		maLopFK = maLop;
		createGUI();
		setTitle("Thông tin lớp học");
		setSize(800, 580);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void createGUI() {
		JPanel pNorth  = new JPanel();
		JLabel jl_tieude = new JLabel("THÔNG TIN SINH VIÊN");
		Font font = new Font("Arial", Font.BOLD, 20);
		jl_tieude.setFont(font);
		jl_tieude.setForeground(Color.blue);
		pNorth.add(jl_tieude);
		add(pNorth, BorderLayout.NORTH);
		
		JPanel pCen = new JPanel();
		pCen.setLayout(null);
		JLabel jl_masv = new JLabel("Mã số sinh viên:");
		JLabel jl_hoten = new JLabel("Họ tên:");
		JLabel jl_email = new JLabel("Email:");
		JLabel jl_dc = new JLabel("Địa chỉ:");
		txt_masv = new JTextField();
		txt_hoten = new JTextField();
		txt_email = new JTextField();
		txt_dc = new JTextField();
		khoatxt();
		jl_masv.setBounds(0, 5, 120, 25);
		jl_hoten.setBounds(50, 35, 80, 25);
		jl_email.setBounds(50, 65, 80, 25);
		jl_dc.setBounds(50, 95, 80, 25);
		pCen.add(jl_masv);
		pCen.add(jl_hoten);
		pCen.add(jl_email);
		pCen.add(jl_dc);
		txt_masv.setBounds(100, 5, 680, 20);
		txt_hoten.setBounds(100, 35, 680, 20);
		txt_email.setBounds(100, 65, 680, 20);
		txt_dc.setBounds(100, 95, 680, 20);
		pCen.add(txt_masv);
		pCen.add(txt_hoten);
		pCen.add(txt_email);
		pCen.add(txt_dc);
		JPanel pButton = new JPanel();
		btn_1 = new JButton("<<");
		btn_2 = new JButton("<");
		btn_3 = new JButton(">");
		btn_4 = new JButton(">>");
		btn_them = new JButton("Thêm");
		btn_luu = new JButton("Lưu");
		btn_sua = new JButton("Sửa");
		btn_xoa = new JButton("Xóa");
		btn_luu.setEnabled(false);
		btn_1.addActionListener(this);
		btn_2.addActionListener(this);
		btn_3.addActionListener(this);
		btn_4.addActionListener(this);
		btn_them.addActionListener(this);
		btn_luu.addActionListener(this);
		btn_sua.addActionListener(this);
		btn_xoa.addActionListener(this);
		
		pButton.add(btn_1);
		pButton.add(btn_2);
		pButton.add(btn_3);
		pButton.add(btn_4);
		pButton.add(Box.createHorizontalStrut(55));
		pButton.add(btn_them);
		pButton.add(btn_luu);
		pButton.add(btn_sua);
		pButton.add(btn_xoa);
		pButton.setBounds(50, 125, 800, 50);
		pCen.add(pButton);
		taoBang();
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
				, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel pTable = new JPanel();
		pTable.setLayout(null);
		sp.setBounds(5, 20, 770, 150);
//		table.setBounds(0, 15, 770, 250);
		pTable.add(sp);
		dslop = new LopHoc_DAO();
		String titleLop = "";
		for (LopHoc lh : dslop.getAllLopHoc()) {
			if(lh.getMaLop().equals(maLopFK))
				titleLop = lh.getTenLop();
		}
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên của lớp: "+titleLop));
		pTable.setBounds(0, 175, 780, 325);
		pCen.add(pTable);
		add(pCen,BorderLayout.CENTER);
		
		sinhvien = new SinhVien_DAO();
		for (SinhVien sv : sinhvien.getAllSinhVien(maLopFK)) {
			Object[] rowData = {sv.getMaSV()+"",sv.getHoTen(), sv.getEmail(),sv.getDiaChi()};
			model.addRow(rowData);
		}
		
//		JPanel pSouth = new JPanel();
//		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		btn_dssv = new JButton("Xem danh sách sinh viên của lớp hiện tại");
//		btn_dssv.setEnabled(false);
//		btn_dssv.setForeground(Color.red);
//		pSouth.add(btn_dssv);
//		add(pSouth,BorderLayout.SOUTH);
	}
	public void taoBang() {
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã số sinh viên");
		model.addColumn("Họ và tên");
		model.addColumn("Email liên hệ");
		model.addColumn("Địa chỉ");
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
				txt_masv.setText(table.getValueAt(row, 0).toString());
				txt_hoten.setText(table.getValueAt(row, 1).toString());
				txt_email.setText(table.getValueAt(row, 2).toString());
				txt_dc.setText(table.getValueAt(row, 3).toString());
//				btn_dssv.setEnabled(true);
			}
		});
		
	}
	public void moKhoatxt() {
		if(btn_sua.getText().equals("Hủy"))
			txt_masv.setEditable(false);
		else
			txt_masv.setEditable(true);
		txt_hoten.setEditable(true);
		txt_email.setEditable(true);
		txt_dc.setEditable(true);
	}
	public void khoatxt() {
		txt_masv.setEditable(false);
		txt_hoten.setEditable(false);
		txt_email.setEditable(false);
		txt_dc.setEditable(false);
	}
	public void reset() {
		khoatxt();
		if(btn_them.getText().equals("Hủy")) {
			btn_them.setText("Thêm");
			btn_luu.setEnabled(false);
			btn_sua.setEnabled(true);
			btn_xoa.setEnabled(true);
		}
		if(btn_sua.getText().equals("Hủy")) {
			btn_sua.setText("Sửa");
			btn_luu.setEnabled(false);
			btn_them.setEnabled(true);
			btn_xoa.setEnabled(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src.equals(btn_them)) {
			if(btn_them.getText().equals("Thêm")) {
				moKhoatxt();
				btn_them.setText("Hủy");
				btn_luu.setEnabled(true);
				btn_sua.setEnabled(false);
				btn_xoa.setEnabled(false);		
			}else {
				khoatxt();
				btn_them.setText("Thêm");
				btn_luu.setEnabled(false);
				btn_sua.setEnabled(true);
				btn_xoa.setEnabled(true);
			}
		}
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
		else if(src.equals(btn_luu)) {
			int row = table.getSelectedRow();
			if(btn_them.getText().equals("Hủy")) {
				SinhVien sv = new SinhVien(Integer.parseInt(txt_masv.getText()), 
						txt_hoten.getText(), txt_email.getText(), txt_dc.getText(), maLopFK);
				if(sinhvien.create(sv)) {
					String[] data = {txt_masv.getText()
							,txt_hoten.getText(), txt_email.getText()
							, txt_dc.getText(), maLopFK};
					model.addRow(data);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
			}
			else if(btn_sua.getText().equals("Hủy")) {	
				SinhVien sv = new SinhVien(Integer.parseInt(txt_masv.getText()), 
						txt_hoten.getText(), txt_email.getText(), txt_dc.getText(), maLopFK);
				if(sinhvien.update(sv)) {
					String[] data = {txt_masv.getText()
							,txt_hoten.getText(), txt_email.getText()
							, txt_dc.getText(), maLopFK};
					model.insertRow(row, data);
					model.removeRow(row+1);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
			}
			
		}
		else if(src.equals(btn_xoa)) {
			int row = table.getSelectedRow();
			if(row != -1) {
				int masv = Integer.parseInt(table.getValueAt(row, 0).toString()) ;
				if(sinhvien.delete(masv)) {
					model.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Bạn chua chọn dòng cần xóa!");
	}
}
//	public static void main(String[] args) {
//		new quanlySinhVien("DHTH10A");
//	}
}
