package thongTinSach2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String FILENAME = "Sach.dat";
	JTextField txtMa, txtTen, txtSotrang, txtNXB, txtTim;
	JComboBox cbbTheLoai;
	JButton btTim, btThem, btXoa, btXoaTrang, btLuu;
	DataBase database;
	private DanhSachSach ds;
	DefaultTableModel tbmodle;
	JTable table;

	public Gui() {

		setTitle("Kiểm Tra Giữa Kỳ - Nguyễn Thị Thu Hồng - 21097741 - DHKTPM17BTT");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.createGui();

		this.createDB();
	}

	private void createDB() {
		database = new DataBase();
		try {
			File file = new File(FILENAME);
			if (file.exists())
				loadData();
			else
				file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadData() {
		ds = (DanhSachSach) database.readFile(FILENAME);
		if (ds == null) {
			ds = new DanhSachSach();
		} else {
			for (Sach s : ds.getDs()) {
				String[] row = { s.getMaSach(), s.getTenSach(), s.getSoTrang() + "", s.getTheLoai(), s.getnXB() };
				tbmodle.addRow(row);
			}
			// upDateComBoBox();
		}
	}

	private void upDateComBoBox() {
		int n = ds.getSize();
		String[] item = new String[n];
		int i = 0;
		for (Sach s : ds.getDs()) {
			item[i] = s.getMaSach();
			i++;
		}
		cbbTheLoai.setModel(new DefaultComboBoxModel<String>(item));
	}

	private void createTable() {
		String[] header = { "Mã sách", "Tên sách", "Số trang", "Thể loại", "Nhà xuất bản" };
		tbmodle = new DefaultTableModel(header, 0);
		table = new JTable(tbmodle);
		JScrollPane scp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scp, BorderLayout.CENTER);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

	}

	private void createGui() {
		this.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		this.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));
		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));

		JLabel lbMa, lbten, lbSoTrang, lbTheLoai, lbNXB;

		JLabel label = new JLabel("THÔNG TIN SÁCH");
		label.setFont(new Font("Arial", Font.BOLD, 25));
		label.setForeground(Color.blue);
		b1.add(label);
		b2.add(lbMa = new JLabel("Mã sách:"));
		b2.add(Box.createHorizontalStrut(15));
		b2.add(txtMa = new JTextField());
		b3.add(lbten = new JLabel("Tên sách: "));
		b3.add(Box.createHorizontalStrut(15));
		b3.add(txtTen = new JTextField());
		b3.add(Box.createHorizontalStrut(10));
		b3.add(lbSoTrang = new JLabel("Số trang: "));
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txtSotrang = new JTextField());
		b4.add(lbTheLoai = new JLabel("Thể loại:"));
		b4.add(Box.createHorizontalStrut(15));
		String[] theLoai = { "Toán", "Tin học", "Ngữ văn", "Hóa học", "Vật lý" };
		b4.add(cbbTheLoai = new JComboBox<>(theLoai));
		b4.add(lbNXB = new JLabel("Nhà xuất bản: "));
		b4.add(txtNXB = new JTextField());

		lbMa.setPreferredSize(lbten.getPreferredSize());
		lbTheLoai.setPreferredSize(lbten.getPreferredSize());
		cbbTheLoai.setPreferredSize(new Dimension(80, 20));
		cbbTheLoai.setSelectedItem(null);
		panelNorth.add(Box.createHorizontalStrut(15));
		panelNorth.add(b);

		createTable();

		JPanel panelSouth = new JPanel();
		this.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.add(new JLabel("Nhập mã sách cần tìm: "));
		panelSouth.add(txtTim = new JTextField(10));
		panelSouth.add(btTim = new JButton("Tìm"));
		panelSouth.add(btThem = new JButton("Thêm"));
		panelSouth.add(btXoaTrang = new JButton("Xóa trắng"));
		panelSouth.add(btXoa = new JButton("Xóa"));
		panelSouth.add(btLuu = new JButton("Lưu"));

		btTim.addActionListener(this);
		btThem.addActionListener(this);
		btXoaTrang.addActionListener(this);
		btXoa.addActionListener(this);
		btLuu.addActionListener(this);
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
				int vitri = table.getSelectedRow();
				if(vitri >=0 && vitri < ds.getSize()) {
					Sach s = ds.getSach(vitri);
					napDuLieuVaoO(s);
				}
				
			}
			
		});

	}
	private void napDuLieuVaoO(Sach s) {
		txtMa.setText(s.getMaSach());
		txtTen.setText(s.getMaSach());
		txtSotrang.setText(s.getSoTrang()+"");
		cbbTheLoai.setSelectedItem(s.getTheLoai());
		txtNXB.setText(s.getnXB());
		
	}

	/**
	 * @sự kiện
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btThem))
			them();
		else if (o.equals(btLuu)) {
			luu();
			JOptionPane.showMessageDialog(null, "Lưu thành công");
		}
		else if (o.equals(btXoaTrang))
			xoaTrang();
		else if (o.equals(btXoa))
			xoa();
		else if(o.equals(btTim))
			tim();

	}
	

	public static void main(String[] args) {
		new Gui().setVisible(true);
	}
	private void showMessage(String mesage, JTextField txt) {
		JOptionPane.showMessageDialog(null, mesage);
		txt.requestFocus();
		txt.setText("");
	}

	private boolean validData() {
		String maSach = txtMa.getText().trim();
		String tenSach = txtTen.getText().trim();
		String nXB = txtNXB.getText().trim();
		String soTrang = txtSotrang.getText().trim();
		if(maSach.isEmpty()) {
			showMessage("Cần nhập mã sách", txtMa);
			return false;
		}
		if(tenSach.isEmpty()) {
			showMessage("Cần nhập tên sách", txtTen);
			return false;
		}
		if(soTrang.isEmpty()) {
			showMessage("Bạn chưa nhập số trang", txtSotrang);
			return false;
		}
		else {
			try {
				int x = Integer.parseInt(soTrang);
				if(x<0) {
					showMessage("Số trang là số nguyên dương", txtSotrang);
				return false;
				}
			} catch (NumberFormatException e) {
				showMessage("Số trang là số nguyên", txtSotrang);
				return false;
			}
			
		}
		if(cbbTheLoai.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn thể loại");
			return false;
		}
		if(nXB.isEmpty()) {
			showMessage("Cần nhập nhà xuất bản", txtNXB);
			return false;
		}
	
	
		return true;
	
		
			
	}
	private void them() {
		if(validData()) {
			Sach s = revertSachFromTXT();
			if (!(ds.themSach(s))) {
				showMessage("Trùng mã sách", txtMa);
				txtMa.requestFocus();
			} else {
				String[] row = { s.getMaSach(), s.getTenSach(), s.getSoTrang() + "", s.getTheLoai(), s.getnXB() };
				tbmodle.addRow(row);
				// upDateComBoBox();
			}
		}
	}

	private Sach revertSachFromTXT() {
		Sach s = null;
		String ma, ten, theLoai, NXB;
		int soTrang;
		ma = txtMa.getText();
		ten = txtTen.getText();
		soTrang = Integer.parseInt(txtSotrang.getText());
		theLoai = (String) cbbTheLoai.getSelectedItem();
		NXB = txtNXB.getText();
		s = new Sach(ma, ten, theLoai, NXB, soTrang);
		return s;
	}

	private void luu() {
		try {
			database.saveFile(FILENAME, ds);
			// JOptionPane.showMessageDialog(null, "Lưu thành công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtTen.setText("");
		txtSotrang.setText("");
		txtNXB.setText("");
		cbbTheLoai.setSelectedItem(null);
	}

	private void xoa() {
		int i = table.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa");
			return;
		} else {
			int tb = JOptionPane.showConfirmDialog(null, "Bạn có chắc chăn muốn xóa", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaTaiViTri(i);
				tbmodle.removeRow(i);
				xoaTrang();
				luu();
			}
		}
	}
	private void tim() {
		String maCanTim = txtTim.getText();
		Sach s = ds.timKiemSach(maCanTim);
		if(s == null) {
			JOptionPane.showMessageDialog(null, "Không tồn tại sách có mã: "+ maCanTim);
		}else {
			napDuLieuVaoO(s);
			int index = table.convertRowIndexToView(ds.indexOf(s));
			table.setRowSelectionInterval(index, index);
		}
		txtTim.setText("");
	}
}
