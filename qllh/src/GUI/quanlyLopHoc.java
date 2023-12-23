package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.table.TableModel;

import connectDB.ConnectDB;
import dao.LopHoc_DAO;
import entity.LopHoc;

public class quanlyLopHoc extends JFrame implements ActionListener{
	private JTextField txt_malop;
	private JTextField txt_tenlop;
	private JTextField txt_gv;
	private DefaultTableModel model;
	private JTable table;
	private LopHoc_DAO lophoc;
	private JButton btn_1;
	private JButton btn_2;
	private JButton btn_3;
	private JButton btn_4;
	private JButton btn_them;
	private JButton btn_luu;
	private JButton btn_sua;
	private JButton btn_xoa;
	private JButton btn_dssv;
	private LopHoc_DAO dsLopHoc;
	
	public quanlyLopHoc() {
		try {
			ConnectDB.getInstance().connect();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		dsLopHoc = new LopHoc_DAO();
		createGUI();
		setTitle("Thông tin lớp học");
		setSize(800, 580);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void createGUI() {
		JPanel pNorth  = new JPanel();
		JLabel jl_tieude = new JLabel("THÔNG TIN LỚP HỌC");
		Font font = new Font("Arial", Font.BOLD, 20);
		jl_tieude.setFont(font);
		pNorth.add(jl_tieude);
		add(pNorth, BorderLayout.NORTH);
		
		JPanel pCen = new JPanel();
		pCen.setLayout(null);
		JLabel jl_malop = new JLabel("Mã lớp:");
		JLabel jl_tenlop = new JLabel("Tên lớp:");
		JLabel jl_gv = new JLabel("Giáo viên chủ nhiệm:");
		txt_malop = new JTextField();
		txt_tenlop = new JTextField();
		txt_gv = new JTextField();
		khoatxt();
		jl_malop.setBounds(75, 5, 80, 25);
		jl_tenlop.setBounds(70, 35, 80, 25);
		jl_gv.setBounds(0, 65, 150, 25);
		pCen.add(jl_malop);
		pCen.add(jl_tenlop);
		pCen.add(jl_gv);
		txt_malop.setBounds(120, 5, 660, 20);
		txt_tenlop.setBounds(120, 35, 660, 20);
		txt_gv.setBounds(120, 65, 660, 20);
		pCen.add(txt_malop);
		pCen.add(txt_tenlop);
		pCen.add(txt_gv);
		
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
		pButton.setBounds(50, 95, 800, 50);
		pCen.add(pButton);
		taoBang();
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
				, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel pTable = new JPanel();
		pTable.setLayout(null);
		sp.setBounds(5, 20, 770, 150);
//		table.setBounds(0, 15, 770, 250);
		pTable.add(sp);
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));
		pTable.setBounds(0, 145, 780, 325);
		pCen.add(pTable);
		add(pCen,BorderLayout.CENTER);
		lophoc = new LopHoc_DAO();
		for (LopHoc lh : lophoc.getAllLopHoc()) {
			Object[] rowData = {lh.getMaLop(),lh.getTenLop(),lh.getGiaoVienCN()};
			model.addRow(rowData);
		}
		
		JPanel pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn_dssv = new JButton("Xem danh sách sinh viên của lớp hiện tại");
		btn_dssv.setEnabled(false);
		btn_dssv.setForeground(Color.red);
		btn_dssv.addActionListener(this);
		pSouth.add(btn_dssv);
		add(pSouth,BorderLayout.SOUTH);
	}
	public void taoBang() {
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã số lớp");
		model.addColumn("Tên lớp");
		model.addColumn("Giáo viên chủ nhiệm");
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
				txt_malop.setText(table.getValueAt(row, 0).toString());
				txt_tenlop.setText(table.getValueAt(row, 1).toString());
				txt_gv.setText(table.getValueAt(row, 2).toString());
				btn_dssv.setEnabled(true);
			}
		});
		
	}
	public void moKhoatxt() {
		if(btn_sua.getText().equals("Hủy"))
			txt_malop.setEditable(false);
		else
			txt_malop.setEditable(true);
		txt_tenlop.setEditable(true);
		txt_gv.setEditable(true);
	}
	public void khoatxt() {
		txt_malop.setEditable(false);
		txt_tenlop.setEditable(false);
		txt_gv.setEditable(false);
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
				LopHoc lh = new LopHoc(txt_malop.getText()
						, txt_tenlop.getText(), txt_gv.getText());
				if(lophoc.create(lh)) {
					String[] data = {txt_malop.getText()
							, txt_tenlop.getText(), txt_gv.getText()};
					model.addRow(data);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
			}
			else if(btn_sua.getText().equals("Hủy")) {	
				LopHoc lh = new LopHoc(txt_malop.getText()
						, txt_tenlop.getText(), txt_gv.getText());
				if(lophoc.update(lh)) {
					String[] data = {txt_malop.getText()
							, txt_tenlop.getText(), txt_gv.getText()};
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
				String malop = table.getValueAt(row, 0).toString();
				if(lophoc.delete(malop)) {
					model.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Bạn chua chọn dòng cần xóa!");
	}
		else if(src.equals(btn_dssv)) {
			int row = table.getSelectedRow();
			new quanlySinhVien(table.getValueAt(row, 0).toString());
		}
}
	public static void main(String[] args) {
		new quanlyLopHoc();
	}
	
}
