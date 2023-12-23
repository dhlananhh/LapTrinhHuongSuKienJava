package quanLySach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmDanhMucSach extends JFrame {
	JTextField txtMaSach, txtTuaSach, txtNamXB, txtSoTrang, txtTacGia, txtNhaXB, txtDonGia, txtISBN, txtThongBao;
	JLabel lbMaSach, lbTuaSach, lbNamXB, lbSoTrang, lbTacGia, lbNhaXB, lbDonGia, lbISBN, lbTim;
	JPanel pnEditor, pnBtn, pnTable, pnBorder;
	JButton btnThem, btnXoaRong, btnSua, btnXoa;
	JComboBox comboMaSach;
	DefaultTableModel model;
	JTable table;
	public void createGUI() {
		pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnEditor = new JPanel();
		pnEditor.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		pnEditor.setLayout(new BoxLayout(pnEditor, BoxLayout.Y_AXIS));
		pnBorder.add(pnEditor, BorderLayout.NORTH);
		Box b1,b2,b3,b4,b5,b6;
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();
		
		lbMaSach = new JLabel("Ma sach: ");
		txtMaSach = new JTextField();
		b1.add(lbMaSach);
		b1.add(txtMaSach);
		pnEditor.add(b1);
		pnEditor.add(Box.createVerticalStrut(5));
		
		lbTuaSach = new JLabel("Tua sach: ");
		lbTacGia = new JLabel("Tac gia: ");
		txtTuaSach = new JTextField();
		txtTacGia = new JTextField();
		b2.add(lbTuaSach);
		b2.add(txtTuaSach);
		b2.add(lbTacGia);
		b2.add(txtTacGia);
		pnEditor.add(b2);
		pnEditor.add(Box.createVerticalStrut(5));
		
		lbNamXB = new JLabel("Nam xuat ban: ");
		lbNhaXB = new JLabel("Nha xuat ban: ");
		txtNamXB = new JTextField();
		txtNhaXB = new JTextField();
		b3.add(lbNamXB);
		b3.add(txtNamXB);
		b3.add(lbNhaXB);
		b3.add(txtNhaXB);
		pnEditor.add(b3);
		pnEditor.add(Box.createVerticalStrut(5));
		
		lbSoTrang = new JLabel("So trang: ");
		lbDonGia = new JLabel("Don gia: ");
		txtSoTrang = new JTextField();
		txtDonGia = new JTextField();
		b4.add(lbSoTrang);
		b4.add(txtSoTrang);
		b4.add(lbDonGia);
		b4.add(txtDonGia);
		pnEditor.add(b4);
		pnEditor.add(Box.createVerticalStrut(5));
		
		lbISBN = new JLabel("International Standard Book Number (ISBN): ");
		txtISBN = new JTextField();
		b5.add(lbISBN);
		b5.add(txtISBN);
		pnEditor.add(b5);
		pnEditor.add(Box.createVerticalStrut(5));
		
		txtThongBao = new JTextField();
		txtThongBao.setBorder(null);
		txtThongBao.setEditable(false);
		Font font = new Font("Arial", Font.ITALIC, 15);
		txtThongBao.setFont(font);
		txtThongBao.setForeground(Color.RED);
		b6.add(Box.createHorizontalStrut(50));
		b6.add(txtThongBao);
		pnEditor.add(b6);
		
		pnBtn = new JPanel();
		pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.X_AXIS));
		btnThem = new JButton("Them");
		btnXoaRong = new JButton("Xoa rong");
		btnSua = new JButton("Sua");
		btnXoa = new JButton("Xoa");
		lbTim = new JLabel("Tim theo ma sach");
		comboMaSach = new JComboBox<>();
		pnBtn.add(btnThem);
		pnBtn.add(Box.createHorizontalStrut(5));
		pnBtn.add(btnXoaRong);
		pnBtn.add(Box.createHorizontalStrut(5));
		pnBtn.add(btnSua);
		pnBtn.add(Box.createHorizontalStrut(5));
		pnBtn.add(btnXoa);
		pnBtn.add(Box.createHorizontalStrut(5));
		pnBtn.add(lbTim);
		pnBtn.add(comboMaSach);
		pnBorder.add(pnBtn, BorderLayout.CENTER);
		
		taoBang();
		pnBorder.add(pnTable, BorderLayout.SOUTH);
		
		this.add(pnBorder);
	}
	public void taoBang() {
		String[] column = {"Ma sach", "Tua sach", "Tac gia", "Nam xuat ban", "Nha xuat ban", "So trang", "Don gia", "ISBN"};
		model = new DefaultTableModel(column,0);
		table = new JTable(model);
//		model.addColumn("Ma sach");
//		model.addColumn("Tua sach");
//		model.addColumn("Tac gia");
//		model.addColumn("Nam xuat ban");
//		model.addColumn("Nha xuat ban");
//		model.addColumn("So trang");
//		model.addColumn("Don gia");
//		model.addColumn("ISBN");
		pnTable = new JPanel();
		pnTable.add(table);
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(650, 250));
		pnTable.add(sp);
		
		
	}
	public FrmDanhMucSach() throws Exception{
		this.setTitle("Quan ly sach");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createGUI();
		Event event = new Event(this);
		event.loadFile();
		btnThem.addActionListener(event);
		btnXoa.addActionListener(event);
		btnXoaRong.addActionListener(event);
		table.addMouseListener(event);
	}
	public static void main(String[] args) throws Exception{
		new FrmDanhMucSach().setVisible(true);
	}
}
