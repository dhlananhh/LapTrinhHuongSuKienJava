package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import dao.CauLacBo_DAO;
import dao.VanDongVien_DAO;
import entity.CauLacBo;
import entity.VanDongVien;

public class VanDongVien_GUI extends JFrame  implements ActionListener, MouseListener {

	private static final long serialVersionUID = -1554680235689968471L;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnXoa;
	private JButton btnKetThuc;

	private DefaultTableModel dataModel;
	private JTable table;

	private JScrollPane scroll;

	private JComboBox<String> cboCauLB;
	private JTextField txtMaVDV;
	private JTextField txtTenVDV;
	private JTextField txtTuoi;

	private JButton btnLoc;
	private CauLacBo_DAO dsCLB;
	private VanDongVien_DAO dsVDV;

	public VanDongVien_GUI() {
		try {
			ConnectDB.getInstance().connect();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		dsCLB = new CauLacBo_DAO();
		dsVDV = new VanDongVien_DAO();
		setSize(630, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Box b, b1, b2, b3, b4, b5, b6, b7, b8;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());

		b.add(Box.createVerticalStrut(10));
		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		JLabel lblTieuDe, lblMaVDV, lblTenVDV, lblTuoi, lblCLB;
		b1.add(lblTieuDe = new JLabel("-THÔNG TIN VẬN ĐỘNG VIÊN- ", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTieuDe.setForeground(Color.BLUE);

		b2.add(lblMaVDV = new JLabel("  Mã số vận động viên:  ", JLabel.RIGHT));
		b2.add(txtMaVDV = new JTextField());
		b2.add(Box.createHorizontalStrut(50));
		b3.add(lblTenVDV = new JLabel("Tên Vận động viên:  ", JLabel.RIGHT));
		b3.add(txtTenVDV = new JTextField());
		b3.add(Box.createHorizontalStrut(50));
		b4.add(lblTuoi = new JLabel("Câu lạc bộ:  ", JLabel.RIGHT));
		b4.add(cboCauLB = new JComboBox<String>());
		b4.add(Box.createHorizontalStrut(300));

		b5.add(lblCLB = new JLabel("Tuổi:  ", JLabel.RIGHT));
		b5.add(txtTuoi = new JTextField());
		b5.add(Box.createHorizontalStrut(50));

		DefaultComboBoxModel<String> dataModelLop = new DefaultComboBoxModel<String>();

		cboCauLB.setModel(dataModelLop);

		lblTuoi.setPreferredSize(lblMaVDV.getPreferredSize());
		lblTenVDV.setPreferredSize(lblMaVDV.getPreferredSize());
		lblCLB.setPreferredSize(lblMaVDV.getPreferredSize());

		b6.add(Box.createHorizontalStrut(40));
		b6.add(btnThem = new JButton("Thêm Mới "));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnLuu = new JButton("Lưu "));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnXoa = new JButton("Xóa"));
		b6.add(Box.createHorizontalStrut(50));
		b6.add(btnKetThuc = new JButton("Kết Thúc"));

		String[] tieuDe = { "Mã Số", "Tên vận động viên", "Tuổi", "Câu Lạc Bộ" };
		b7.add(scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0))));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách vận động viên:"));

		JLabel lblName;
		b8.add(lblName = new JLabel("Họ tên sv: ................massv:.............."));
		lblName.setFont(new Font("Times", Font.ITALIC, 12));
		lblName.setForeground(Color.RED);
		b8.add(Box.createHorizontalStrut(50));
		b8.add(btnLoc= new JButton("   Lọc danh sách VĐV theo câu lạc bộ "));
		btnLoc.setFont(new Font("Times New Roman", Font.ITALIC,14 ));
		btnLoc.setForeground(Color.BLUE);
		table.addMouseListener(this);
		btnKetThuc.addActionListener(this);
		btnLoc.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		
		for (CauLacBo clb : dsCLB.getAllCLB()) {
			cboCauLB.addItem(clb.getMa());
		}
		for (VanDongVien vdv : dsVDV.getAllVDV()) {
			String[] data = {vdv.getMaVDV(), vdv.getTenVDV(), vdv.getTuoi()+"", vdv.getClb().getMa()};
			dataModel.addRow(data);
		}
	}

	public void reset() {
		txtMaVDV.setText("");
		txtTenVDV.setText("");
		txtTuoi.setText("");
		cboCauLB.setSelectedIndex(0);
		txtMaVDV.requestFocus();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnThem)) {
			reset();
		}
		else if(src.equals(btnLuu)) {
			String ma = txtMaVDV.getText();
			String ten = txtTenVDV.getText();
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String maclb = cboCauLB.getSelectedItem().toString();
			
			VanDongVien vdv = new VanDongVien(ma, ten, new CauLacBo(maclb), tuoi);
			if(dsVDV.create(vdv)) {
				String[] data = {ma, ten, tuoi+"", maclb};
				dataModel.addRow(data);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			}
		}
		else if(src.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row != -1) {
				int tb = JOptionPane.showConfirmDialog(this, "Có chắc muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if(tb == JOptionPane.YES_OPTION) {
					String mavdv = table.getValueAt(row, 0).toString();
					if(dsVDV.delete(mavdv)) {
						dataModel.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công!");
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng để xóa!");
		}
		else if(src.equals(btnKetThuc)) {
			int row = table.getSelectedRow();
			int tb = JOptionPane.showConfirmDialog(this, "Có chắc muốn đóng chương trình này không?", "Chú ý", JOptionPane.YES_NO_OPTION);
			if(tb == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		else if(src.equals(btnLoc)){
			String maCLB = cboCauLB.getSelectedItem().toString();
			dataModel.setRowCount(0);
			for (VanDongVien vdv : dsVDV.getAllVDVTheoCLB(maCLB)) {
				String[] data = {vdv.getMaVDV(), vdv.getTenVDV(), vdv.getTuoi()+"", vdv.getClb().getMa()};
				dataModel.addRow(data);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaVDV.setText(table.getValueAt(row, 0).toString());
		txtTenVDV.setText(table.getValueAt(row, 1).toString());
		txtTuoi.setText(table.getValueAt(row, 2).toString());
		cboCauLB.setSelectedItem(table.getValueAt(row, 3));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
