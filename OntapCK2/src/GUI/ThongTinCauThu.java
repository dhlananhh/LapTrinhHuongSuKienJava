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
import dao.CauThu_DAO;
import dao.ViTriThiDau_DAO;
import entity.CauThu;
import entity.ViTriThiDau;


public class ThongTinCauThu extends JFrame implements ActionListener{
	

	private JComboBox<String> cb_vttd;
	private JButton btn_them;
	private JButton btn_luu;
	private JButton btn_xoa;
	private JButton btn_kt;
	private DefaultTableModel model;
	private JTable table;
	private JButton btn_loc;
	private JTextField txt_mact;
	private JTextField txt_tenct;
	private JTextField txt_tuoi;
	private ViTriThiDau_DAO dsVTTD;
	private CauThu_DAO dsCT;
	public ThongTinCauThu() {
		try {
			ConnectDB.getInstance().connect();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		dsVTTD = new ViTriThiDau_DAO();
		dsCT = new CauThu_DAO();
		createGUI();
		setTitle("Bài thi cuối kỳ - LT HSK Java - HK2(2021-2022)");
		setSize(850, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void createGUI() {
		JPanel pNorth = new JPanel();
		JLabel jl_tieude = new JLabel("-THÔNG TIN CẦU THỦ-");
		Font font = new Font("Arial", Font.BOLD, 25);
		jl_tieude.setForeground(Color.blue);
		jl_tieude.setFont(font);
		pNorth.add(jl_tieude);
		add(pNorth, BorderLayout.NORTH);
		
		
		JPanel pCen = new JPanel();
		pCen.setLayout(null);
		JLabel jl_mact = new JLabel("Mã số cầu thủ:");
		JLabel jl_tenct = new JLabel("Tên cầu thủ:");
		JLabel jl_vttd = new JLabel("Vị trí thi đấu:");
		JLabel jl_tuoi = new JLabel("Tuổi:");
		
		txt_mact = new JTextField();
		txt_tenct = new JTextField();
		cb_vttd = new JComboBox<String>();
		txt_tuoi = new JTextField();
		
		jl_mact.setBounds(35, 5, 90, 25);
		pCen.add(jl_mact);
		
		jl_tenct.setBounds(48, 45, 90, 25);
		pCen.add(jl_tenct);
		
		jl_vttd.setBounds(48, 85, 120, 25);
		pCen.add(jl_vttd);
		
		jl_tuoi.setBounds(90, 125, 90, 25);
		pCen.add(jl_tuoi);
		
		txt_mact.setBounds(120, 5, 680, 25);
		pCen.add(txt_mact);
		
		txt_tenct.setBounds(120, 45, 680, 25);
		pCen.add(txt_tenct);
		
		cb_vttd.setBounds(120, 85, 400, 25);
		pCen.add(cb_vttd);
		
		txt_tuoi.setBounds(120, 125, 680, 25);
		pCen.add(txt_tuoi);
		
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
		btn_loc = new JButton("Lọc danh sách cầu thủ theo vị trí thi đấu");
		btn_loc.setForeground(Color.blue);
		Font font2 = new Font("Arial", Font.ITALIC, 15);
		btn_loc.setFont(font2);
		pSouth.add(jl_footer);
		pSouth.add(Box.createHorizontalStrut(50));
		pSouth.add(btn_loc);
		btn_loc.addActionListener(this);
		
		add(pSouth, BorderLayout.SOUTH);
		for (ViTriThiDau vttd : dsVTTD.getAllvttd()) {
			cb_vttd.addItem(vttd.getMa());
		}
		
		for (CauThu ct : dsCT.getAllCauThu()) {
			String[] data = {ct.getMaCT(), ct.getTenCT(),ct.getTuoi()+"",ct.getVttd().getMa()};
			model.addRow(data);
		}
	}
	public void taoBang() {
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("Mã số");
		model.addColumn("Tên cầu thủ");
		model.addColumn("Tuổi");
		model.addColumn("Vị trí thi đấu");	
		
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
				txt_mact.setText(table.getValueAt(row, 0).toString());
				txt_tenct.setText(table.getValueAt(row, 1).toString());
				txt_tuoi.setText(table.getValueAt(row, 2).toString());
				cb_vttd.setSelectedItem(table.getValueAt(row, 3));
				
			}
		});
	}
	public static void main(String[] args) {
		new ThongTinCauThu();
	}
	public void reset() {
		txt_mact.setText("");
		txt_tenct.setText("");
		txt_tuoi.setText("");
		cb_vttd.setSelectedIndex(0);
	}
	public boolean kiemTraHopLe() {
		String mact = txt_mact.getText().trim();
		String tenct= txt_tenct.getText().trim();
		String tuoi = txt_tuoi.getText().trim();
		if(!(mact.length()>0 && mact.matches("^VDV\\d{2}$"))) {
			JOptionPane.showMessageDialog(this, "Mã cầu thủ không hợp lệ!");
			return false;
		}
		if(!(tenct.length()>0 && tenct.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$"))) {
			JOptionPane.showMessageDialog(this, "Tên cầu thủ không hợp lệ");
			return false;
		}
		if(tuoi.length()>0) {
			try {
				int st = Integer.parseInt(tuoi);
				if(st<18 || st>23) {
					JOptionPane.showMessageDialog(this, "Tuổi phải từ 18 đến 23 tuổi!");
					return false;
				}
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Tuổi phải là số nguyên!");
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
			txt_mact.requestFocus();
		}
		else if(src.equals(btn_luu)) {
			if(kiemTraHopLe()) {
				String mact = txt_mact.getText();
				String tenct = txt_tenct.getText();
				int tuoi = Integer.parseInt(txt_tuoi.getText());
				String maVTTD = cb_vttd.getSelectedItem().toString();
				
				CauThu ct = new CauThu(mact, tenct, new ViTriThiDau(maVTTD), tuoi);
				if(dsCT.create(ct)) {
					String[] data = {mact, tenct, tuoi+"",maVTTD};
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
					if(dsCT.delete(table.getValueAt(row, 0).toString())) {
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
			String maVTTD = cb_vttd.getSelectedItem().toString();
			model.setRowCount(0);
			for (CauThu ct : dsCT.getAllCauThuTheoVTTD(maVTTD)) {
				String[] data = {ct.getMaCT(), ct.getTenCT(),ct.getTuoi()+"",ct.getVttd().getMa()};
				model.addRow(data);
			}
		}
		
	}
}
