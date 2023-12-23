package quanLySach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Event implements ActionListener, MouseListener{
	private FrmDanhMucSach gui;
	private DanhMucSach ds = new DanhMucSach();
	private Database db = new Database();
	public Event (FrmDanhMucSach gui) throws Exception{
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(gui.btnThem)) {
			luuSach();	
			db.saveFile("Sach.dat", ds);
		}else if(o.equals(gui.btnXoa)){
			xoa();
		}else if(o.equals(gui.btnXoaRong)) {
			xoaRong();
		}
	}
	private void luuSach() {
		String ma, tua, namXB, nhaXB, soTrang, tacGia, ISBN, donGia;
		ma = gui.txtMaSach.getText();
		tua = gui.txtTuaSach.getText();
		namXB = gui.txtNamXB.getText();
		nhaXB = gui.txtNhaXB.getText();
		soTrang = gui.txtSoTrang.getText();
		tacGia = gui.txtTacGia.getText();
		ISBN = gui.txtISBN.getText();
		donGia = gui.txtDonGia.getText();
		if(ds.them(new Sach(ma, tua, tacGia, Integer.parseInt(namXB), nhaXB, 
				Integer.parseInt(soTrang), Double.parseDouble(donGia), ISBN))) {
			//JOptionPane.showMessageDialog(gui, "Thêm thành công");
			String[] row = {ma, tua, tacGia, namXB, nhaXB, soTrang, donGia, ISBN};
			gui.model.addRow(row);
			gui.comboMaSach.addItem(ma);
			gui.txtThongBao.setText("Thêm thành công");
		}else {
			//JOptionPane.showMessageDialog(gui, "Mã sách này đã tồn tại");
			gui.txtThongBao.setText("Mã sách đã tồn tại");
		}
	}
	public void loadFile() {
		ds = (DanhMucSach) db.readFile("Sach.dat");
		if(ds == null) {
			ds = new DanhMucSach();
		}
		else {
			for (Sach s : ds.getDS()) {
				String[] row = {s.getMa(), s.getTua(), s.getTacGia(), s.getNamXB()+"",
						s.getNhaXB(), s.getSoTrang()+"", s.getDonGia()+"", s.getISBN()};
				gui.model.addRow(row);
				gui.comboMaSach.addItem(s.getMa());
			}
		}
	}
	private void xoa() {
		int r = gui.table.getSelectedRow();
		if(r != -1) {
			int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá dòng này không?",
					"Xoá", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				ds.xoaViTri(r);
				gui.model.removeRow(r);
				db.saveFile("Sach.dat", ds);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xoá");
		}
	}
	private void xoaRong() {
		gui.txtMaSach.setText("");
		gui.txtDonGia.setText("");
		gui.txtISBN.setText("");
		gui.txtNamXB.setText("");
		gui.txtNhaXB.setText("");
		gui.txtTacGia.setText("");
		gui.txtTuaSach.setText("");
		gui.txtSoTrang.setText("");
		gui.txtMaSach.requestFocus();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = gui.table.getSelectedRow();
		gui.txtMaSach.setText(gui.model.getValueAt(row, 0).toString());
		gui.txtTuaSach.setText(gui.model.getValueAt(row, 1).toString());
		gui.txtTacGia.setText(gui.model.getValueAt(row, 2).toString());
		gui.txtNamXB.setText(gui.model.getValueAt(row, 3).toString());
		gui.txtNhaXB.setText(gui.model.getValueAt(row, 4).toString());
		gui.txtSoTrang.setText(gui.model.getValueAt(row, 5).toString());
		gui.txtDonGia.setText(gui.model.getValueAt(row, 6).toString());
		gui.txtISBN.setText(gui.model.getValueAt(row, 7).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
