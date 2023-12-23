package thamKhao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeCellRenderer;

public class QuanLySach extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnCenter, pnSouth;
	private JLabel lblMaSach, lblTuaSach, lblTacGia, lblNamXuatBan, lblNhaXuatBan, lblSoTrang, lblDonGia, lblISBN,
			lblTim;
	private JTextField txtMaSach, txtTuaSach, txtTacGia, txtNamXuatBan, txtNhaXuatBan, txtSoTrang, txtDonGia, txtISBN;
	private JButton btnThem, btnXoaRong, btnSua, btnXoa;
	private DefaultTableModel model;
	private JTable table;

	public QuanLySach() {
		super("Quan Ly Sach");
		createQuanLySach();
	}

	public void createQuanLySach() {
		setTitle("Quan Ly Sach");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		quanLySach();
	}

	public void quanLySach() {
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());

		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.NORTH);
		pnCenter.setBorder(BorderFactory.createTitledBorder("Records Editer"));

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();

		JPanel pnInfor = new JPanel();
		pnCenter.add(pnInfor);

		lblMaSach = new JLabel("Mã sách: ");
		txtMaSach = new JTextField(10);

		lblTuaSach = new JLabel("Tựa sách: ");
		txtTuaSach = new JTextField(20);
		lblTacGia = new JLabel("Tác giả: ");
		txtTacGia = new JTextField(10);
		lblTuaSach.setPreferredSize(lblTacGia.getPreferredSize());

		lblNamXuatBan = new JLabel("Năm xuất bản: ");
		txtNamXuatBan = new JTextField(30);
		lblNhaXuatBan = new JLabel("Nhà xuất bản: ");
		txtNhaXuatBan = new JTextField(30);
		lblNamXuatBan.setPreferredSize(lblNhaXuatBan.getPreferredSize());

		lblSoTrang = new JLabel("Số trang: ");
		txtSoTrang = new JTextField(30);
		lblDonGia = new JLabel("Đơn giá: ");
		txtDonGia = new JTextField(30);
		lblSoTrang.setPreferredSize(lblDonGia.getPreferredSize());

		lblISBN = new JLabel("International Standard Book Number (ISBN): ");
		txtISBN = new JTextField(50);

		b1.add(lblMaSach);
		b1.add(txtMaSach);
		b2.add(lblTuaSach);
		b2.add(txtTuaSach);
		b2.add(lblTacGia);
		b2.add(txtTacGia);
		b3.add(lblNamXuatBan);
		b3.add(txtNamXuatBan);
		b3.add(lblNhaXuatBan);
		b3.add(txtNhaXuatBan);
		b4.add(lblSoTrang);
		b4.add(txtSoTrang);
		b4.add(lblDonGia);
		b4.add(txtDonGia);
		b5.add(lblISBN);
		b5.add(txtISBN);

		pnInfor.add(b);
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b5);

		JPanel pnChucNang = new JPanel();
		btnThem = new JButton("Thêm");
		btnXoaRong = new JButton("Xóa rỗng");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		lblTim = new JLabel("Tìm theo mã sách");
		pnChucNang.add(btnThem);
		pnChucNang.add(btnXoaRong);
		pnChucNang.add(btnSua);
		pnChucNang.add(btnXoa);
		pnChucNang.add(lblTim);
		pnContent.add(pnChucNang, BorderLayout.CENTER);

		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách"));

		createTable();
		Container container = getContentPane();
		container.add(pnContent);

	}

	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã sách");
		model.addColumn("Tựa sách");
		model.addColumn("Tác giả");
		model.addColumn("Năm xuất bản");
		model.addColumn("Nhà xuất bản");
		model.addColumn("Số trang");
		model.addColumn("Đơn giá");
		model.addColumn("ISBN");
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTreeCellRenderer.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(780, 310));
		pnSouth.add(scrollPane);
		pnSouth.add(pnTable);
		
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
				
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		new QuanLySach().setVisible(true);
	}
}
