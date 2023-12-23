package kiemtrathu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.table.TableColumn;

public class Gui extends JFrame implements ActionListener {
	JLabel title,masach,tensach,sotrang,theloai,nXB,masachcantim;
	JTextField ms,ts,st,nxb,masearch;
	JComboBox<String> comboBox;
	JButton bttim, btthem,btxoatrang,btxoa,btluu;
	JTable table;
	DefaultTableModel model;
	DanhSachSach ds;
	String [] theloaisach= {"Ngữ Văn","Toán","Tin Học"} ;
	public Gui() {
		setTitle("KiemTraGiuaKi Tran Thanh Tam 21110341");
		setSize(650, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		gui();
	}
	public void gui() {
		 ds = new DanhSachSach();
		JPanel paneNorth = new JPanel();
	
		add(paneNorth,BorderLayout.NORTH);
		title = new JLabel("THÔNG TIN SÁCH");
		title.setFont(new Font("Aril",Font.BOLD, 20));
		title.setForeground(Color.blue);
		paneNorth.add(title);
		
		JPanel paneCenter = new JPanel();
		add(paneCenter,BorderLayout.CENTER);
		Box b,b1,b2,b3;
		
		b=Box.createVerticalBox();
		b1=Box.createHorizontalBox();
		b2=Box.createHorizontalBox();
		b3=Box.createHorizontalBox();
		
		tensach = new JLabel("Tên Sách:");
		masach = new JLabel("Mã Sách:");
		sotrang = new JLabel("Số Trang: ");
		theloai = new JLabel("Thể Loại:");
		nXB	 = new JLabel("Nhà Xuất Bản:");
		masach.setPreferredSize(new Dimension(100, 20));
		tensach.setPreferredSize(new Dimension(masach.preferredSize()));
		theloai.setPreferredSize(new Dimension(masach.preferredSize()));
		sotrang.setPreferredSize(new Dimension(masach.preferredSize()));
		ms = new JTextField(50);
		ts = new JTextField();
		st = new JTextField();
		nxb = new JTextField();
		
		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(80,20));
		b1.add(masach); b1.add(ms);
		b2.add(tensach); b2.add(ts);b2.add(Box.createHorizontalStrut(20)); b2.add(sotrang); b2.add(st);
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b3.add(theloai); b3.add(comboBox); b3.add(Box.createHorizontalStrut(20));
		b3.add(nXB); b3.add(nxb);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		paneCenter.add(b); 
		//tao bang 
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã Sách");
		model.addColumn("Tên Sách");
		model.addColumn("Số Trang");
		model.addColumn("Thể Loại");
		model.addColumn("Nhà Xuất Bản");
		
		JScrollPane scroll = new JScrollPane(table);
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		scroll.setPreferredSize(new Dimension(640, 300));
		paneCenter.add(scroll);
		
		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		masachcantim = new JLabel("Nhập mã sách cần tìm: ");
		masearch = new JTextField(10);
		bttim= new JButton("Tìm");
		btthem= new JButton("Thêm");
		btxoa= new JButton("Xóa");
		btxoatrang= new JButton("Xóa Trắng");
		btluu= new JButton("Lưu");
		Box b8 = Box.createHorizontalBox();
		b8.add(masachcantim);
		b8.add(masearch);
		b8.add(bttim);
		b8.add(Box.createHorizontalStrut(20));
		b8.add(btthem);
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btxoatrang);
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btxoa);
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btluu);
		paneSouth.add(b8);
		
		
		for(int i=0;i<theloaisach.length;i++) {
			comboBox.addItem(theloaisach[i]);
		}
		
		btxoatrang.addActionListener(this);
		btthem.addActionListener(this);
		bttim.addActionListener(this);
		btxoa.addActionListener(this);
		
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
				Select();
			}
		});
	}
	
	public void Them() {
		String sms= ms.getText();
		String stensach= ts.getText();
		String ssotrang= st.getText();
		String snhaxuatban= nxb.getText();
		String stheloai= (String) comboBox.getSelectedItem();
		Sach sach = new Sach(sms, stensach, Integer.parseInt(ssotrang), stheloai, snhaxuatban);
		if(ds.themSach(sach)) {
			JOptionPane.showMessageDialog(null, "Thêm Thành Công");
			String [] row = {sms, stensach, ssotrang,stheloai,snhaxuatban};
			model.addRow(row);
		}else {
			JOptionPane.showMessageDialog(null, "ID Tồn Tại!");
		}
	}
	
		public void xoatrang() {
			ms.setText("");
			ts.setText("");
			st.setText("");
			nxb.setText("");
			comboBox.setSelectedIndex(-1);
			ms.requestFocus();
		}
		public void Select() {
			int row = table.getSelectedRow();
			ms.setText((String)model.getValueAt(row, 0));
			ts.setText((String)model.getValueAt(row, 1));
			st.setText((String)model.getValueAt(row, 2));
			for(int i=0;i<theloaisach.length;i++) {
				if(comboBox.getItemAt(i).equalsIgnoreCase((String) model.getValueAt(row, 3))){
					comboBox.setSelectedIndex(i);
				}
			}
			nxb.setText((String)model.getValueAt(row, 4));
			
		}
		public void xoaDong() {
			int p = table.getSelectedColumn();
			if(p!=-1) {
				int tb = JOptionPane.showConfirmDialog(null, "Bạn Muỗn Xóa ? ", "Delete", JOptionPane.YES_NO_OPTION);
				if(tb==JOptionPane.YES_OPTION) {
					ds.removeSach(p);
					model.removeRow(p);
					xoatrang();
					JOptionPane.showMessageDialog(null, "Xóa Thành Công");
				}else {
					JOptionPane.showMessageDialog(null, "Bạn Không Xóa");
				}
			}
		}
		public void tim() {
			int p = ds.timTheoMa1(masearch.getText());
			if(p!=-1) {
				table.setRowSelectionInterval(p, p);
				Select();
			}else {
				JOptionPane.showMessageDialog(null, "ID Không Tồn Tại!");
			}
		}
		public static void main(String[] args) {
		new Gui().setVisible(true);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(btxoatrang)) {
				xoatrang();
			}else {
				if(o.equals(btthem)) {
					if(ms.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Điền đầy đủ thông tin!");
						ms.requestFocus();
					}else {
						Them();
					}
				}else {
					if(o.equals(bttim)) {
						tim();
					}else {
						if(o.equals(btxoa))
							xoaDong();
					}
				}
			}	
		}
}
