package quanLySach;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMucSach implements Serializable {
	private ArrayList<Sach> list;
	
	
	public DanhMucSach() {
		list = new ArrayList<>();
	}
	
	
	//thêm sách
	public boolean themSach (Sach s) {
		for (int i=0; i<list.size(); i++)
			if (list.contains(s))
				return false;
		list.add(s);
		return true;
	}
	
	
	//thêm sách
	public boolean xoaSach (int ma) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaSach() == ma) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	
	//lấy danh mục sách
	public ArrayList<Sach> getList() {
		return list;
	}
	
	
	//lấy số lượng sách
	public int soLuongSach() {
		return list.size();
	}
	
	
	//sửa sách
	public boolean suaSach (int maSach, String tuaSach, int namXuatBan, int soTrang, String iSBN, String tacGia,
			String nhaXuatBan, double donGia) {
		Sach s = new Sach(maSach, tuaSach, namXuatBan, soTrang, iSBN, tacGia, nhaXuatBan, donGia);
		if (list.contains(s)) {
			s.setMaSach(maSach);
			s.setTuaSach(tuaSach);
			s.setNamXuatBan(namXuatBan);
			s.setSoTrang(soTrang);
			s.setISBN(iSBN);
			s.setTacGia(tacGia);
			s.setNhaXuatBan(nhaXuatBan);
			s.setDonGia(donGia);
			return true;
		} else {
			return false;
		}
	}
	
	
	//kiểm tra có tồn tại mã sách đó không
	public boolean kiemTra (int maSach) {
		Sach s = new Sach(maSach);
		if (list.contains(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//tìm kiếm sách
	public int timKiemSach (int maSach) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaSach() == maSach)
				return i;
		}
		return -1;
	}
}
