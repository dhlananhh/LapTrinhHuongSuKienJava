package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import dao.BoMonChuQuan_DAO;
import dao.MonHoc_DAO;
import entity.BoMonChuQuan;
import entity.MonHoc;

public class ThongTinMonHoc extends JFrame implements ActionListener{
	
	private JTextField txt_mamon;
	private JTextField txt_tenmon;

	private JTextField txt_sotiet;
	private JComboBox<String> cb_bmcq;
	private JButton btn_them;
	private JButton btn_luu;
	private JButton btn_xoa;
	private JButton btn_kt;
	private DefaultTableModel model;
	private JTable table;
	private MonHoc_DAO dsMH;
	private BoMonChuQuan_DAO dsBMCQ;
	private JButton btn_loc;
	public ThongTinMonHoc() {
		try {
			ConnectDB.getInstance().connect();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		dsMH = new MonHoc_DAO();
		dsBMCQ = new BoMonChuQuan_DAO();
		createGUI();
		setTitle("Bài thi cuối kỳ - LT HSK Java - HK2(2021-2022)");
		setSize(850, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void createGUI() {
		JPanel pNorth = new JPanel();
		JLabel jl_tieude = new JLabel("-THÔNG TIN MÔN HỌC-");
		Font font = new Font("Arial", Font.BOLD, 25);
		jl_tieude.setForeground(Color.blue);
		jl_tieude.setFont(font);
		pNorth.add(jl_tieude);
		add(pNorth, BorderLayout.NORTH);
		
		
		JPanel pCen = new JPanel();
		pCen.setLayout(null);
		JLabel jl_mamon = new JLabel("Mã môn học:");
		JLabel jl_tenmon = new JLabel("Tên môn học:");
		JLabel jl_bmcq = new JLabel("Bộ môn chủ quản:");
		JLabel jl_sotiet = new JLabel("Số tiết:");
		
		txt_mamon = new JTextField();
		txt_tenmon = new JTextField();
		cb_bmcq = new JComboBox<String>();
		txt_sotiet = new JTextField();
		
		jl_mamon.setBounds(38, 5, 90, 25);
		pCen.add(jl_mamon);
		
		jl_tenmon.setBounds(35, 45, 90, 25);
		pCen.add(jl_tenmon);
		
		jl_bmcq.setBounds(10, 85, 120, 25);
		pCen.add(jl_bmcq);
		
		jl_sotiet.setBounds(72, 125, 90, 25);
		pCen.add(jl_sotiet);
		
		txt_mamon.setBounds(120, 5, 680, 25);
		pCen.add(txt_mamon);
		
		txt_tenmon.setBounds(120, 45, 680, 25);
		pCen.add(txt_tenmon);
		
		cb_bmcq.setBounds(120, 85, 400, 25);
		pCen.add(cb_bmcq);
		
		txt_sotiet.setBounds(120, 125, 680, 25);
		pCen.add(txt_sotiet);
		
		JPanel p_button = new JPanel();
		btn_them = new JButton("Thêm Mới");
		btn_luu = new JButton("Lưu");
		btn_xoa = new JButton("Xóa");
		btn_kt = new JButton("Kết thúc");
		
		p_button.add(btn_them);
		p_button.add(btn_luu);
		p_button.add(btn_xoa);
		p_button.add(Box.createHorizontalStrut(40));
		p_button.add(btn_kt);
		
		btn_luu.addActionListener(this);
		btn_them.addActionListener(this);
		btn_xoa.addActionListener(this);
		btn_kt.addActionListener(this);
		
		p_button.setBounds(180, 160, 600, 50);
		pCen.add(p_button);
		
		JPanel pTable = new JPanel();
		pTable.setLayout(null);
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách môn học"));
		taoBang();
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
				, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10,15,810,150);
		pTable.add(sp);
		pTable.setBounds(0, 220, 830, 250);
		pCen.add(pTable);

		add(pCen,BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel();
		JLabel jl_footer = new JLabel("Họ tên sv: Trương Chi Bảo masv:21093561");
		jl_footer.setForeground(Color.red);
		btn_loc = new JButton("Lọc danh sách Môn học theo Bộ Môn Chủ Quản");
		btn_loc.setForeground(Color.blue);
		Font font2 = new Font("Arial", Font.ITALIC, 15);
		btn_loc.setFont(font2);
		pSouth.add(jl_footer);
		pSouth.add(Box.createHorizontalStrut(50));
		pSouth.add(btn_loc);
		btn_loc.addActionListener(this);
		
		add(pSouth, BorderLayout.SOUTH);
		for (BoMonChuQuan bmcq : dsBMCQ.getAllBMCQ()) {
			cb_bmcq.addItem(bmcq.getMa());
		}
		
		for (MonHoc mh : dsMH.getAllMonHoc()) {
			String[] data = {mh.getMaMH(), mh.getTenMH(), mh.getSoTiet()+"",mh.getBmcq().getMa()};
			model.addRow(data);
		}
	}
	public void taoBang() {
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("Mã môn học");
		model.addColumn("Tên môn học");
		model.addColumn("Số tiết");
		model.addColumn("Mã số bộ môn chủ quản");	
		
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
				txt_mamon.setText(table.getValueAt(row, 0).toString());
				txt_tenmon.setText(table.getValueAt(row, 1).toString());
				txt_sotiet.setText(table.getValueAt(row, 2).toString());
				cb_bmcq.setSelectedItem(table.getValueAt(row, 3));
				
			}
		});
	}
	public static void main(String[] args) {
		new ThongTinMonHoc();
	}
	public void reset() {
		txt_mamon.setText("");
		txt_tenmon.setText("");
		txt_sotiet.setText("");
		cb_bmcq.setSelectedIndex(0);
	}
	public boolean kiemTraHopLe() {
		String mamon = txt_mamon.getText().trim();
		String tenmon = txt_tenmon.getText().trim();
		String sotiet = txt_sotiet.getText().trim();
		if(!(mamon.length()>0 && mamon.matches("^\\d{7}$"))) {
			JOptionPane.showMessageDialog(this, "Mã môn học phải có 7 chữ số");
			return false;
		}
		if(!(tenmon.length()>0 && tenmon.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$"))) {
			JOptionPane.showMessageDialog(this, "Tên môn học không hợp lệ");
			return false;
		}
		if(sotiet.length()>0) {
			try {
				int st = Integer.parseInt(sotiet);
				if(st<15 || st>90) {
					JOptionPane.showMessageDialog(this, "Số tiết phải từ 15 đến 90 tiết!");
					return false;
				}
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Số tiết phải là số nguyên!");
				return false;
			}
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src.equals(btn_them)) {
			reset();
			txt_mamon.requestFocus();
		}
		else if(src.equals(btn_luu)) {
			if(kiemTraHopLe()) {
				String mamon = txt_mamon.getText();
				String tenmon = txt_tenmon.getText();
				int sotiet = Integer.parseInt(txt_sotiet.getText());
				String maBMCQ = cb_bmcq.getSelectedItem().toString();
				
				MonHoc mh = new MonHoc(mamon, tenmon, new BoMonChuQuan(maBMCQ), sotiet);
				if(dsMH.create(mh)) {
					String[] data = {mamon, tenmon,  sotiet+"",maBMCQ};
					model.addRow(data);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				}
			}
			
		}
		else if(src.equals(btn_xoa)) {
			int row = table.getSelectedRow();
			if(row != -1) {
				int tb =JOptionPane.showConfirmDialog(this, "Có chắc muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if(tb == JOptionPane.YES_OPTION) {
					if(dsMH.delete(table.getValueAt(row, 0).toString())) {
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
						reset();
					}
					else
						JOptionPane.showMessageDialog(this, "Xóa thất bại!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng để xóa!");
			}
		}
		else if(src.equals(btn_kt)) {
			int tb = JOptionPane.showConfirmDialog(this, "Việc đóng ứng dụng có chắc chắn đóng không?", "Chú ý", JOptionPane.YES_NO_OPTION);
			if(tb == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		else if(src.equals(btn_loc)) {
			String maBMCQ = cb_bmcq.getSelectedItem().toString();
			model.setRowCount(0);
			for (MonHoc mh : dsMH.getAllMonHoctheoMABMCQ(maBMCQ)) {
				String[] data = {mh.getMaMH(), mh.getTenMH(), mh.getSoTiet()+"",mh.getBmcq().getMa()};
				model.addRow(data);
			}
		}
		
	}
}
