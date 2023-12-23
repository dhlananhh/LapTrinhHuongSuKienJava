package qlTTSach;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMucSach implements Serializable {
	private ArrayList<Sach> list;
	
	
	public DanhMucSach() {
		list = new ArrayList<>();
	}
	
	
	//thêm sách
	public boolean themSach (Sach sach) {
		for (int i=0; i<list.size(); i++) {
			if (list.contains(sach))
				return false;
		}
		list.add(sach);
		return true;
	}
	
	
	//thêm sách
	public boolean xoaSach (String maSach) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaSach().equalsIgnoreCase(maSach)) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean xoaSachTaiViTri(int index) {
		if (index < 0 || index >= list.size()) {
            return false;
        }
		list.remove(index);
        return true;
	}
	
	
	//lấy danh mục sách
	public ArrayList<Sach> getList() {
		return list;
	}
	
	
	//lấy số lượng sách
	public int getSize() {
		return list.size();
	}
	
	
	//sửa sách
//	public boolean suaSach (String maSach, String tuaSach, int namXuatBan, int soTrang, String ISBN, String tacGia,
//			String nhaXuatBan, double donGia) {
//		Sach s = new Sach(maSach, tuaSach, namXuatBan, soTrang, ISBN, tacGia, nhaXuatBan, donGia);
//		if (list.contains(s)) {
//			s.setMaSach(maSach);
//			s.setTuaSach(tuaSach);
//			s.setNamXuatBan(namXuatBan);
//			s.setSoTrang(soTrang);
//			s.setISBN(ISBN);
//			s.setTacGia(tacGia);
//			s.setNhaXuatBan(nhaXuatBan);
//			s.setDonGia(donGia);
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	
	//kiểm tra có tồn tại mã sách đó không
	public boolean kiemTra (String maSach) {
		Sach s = new Sach(maSach);
		if (list.contains(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//tìm kiếm sách
	public Sach timKiemSach (String maSach) {
		Sach sach = new Sach(maSach);
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaSach().equalsIgnoreCase(maSach))
				return list.get(list.indexOf(sach));
		}
		return null;
	}
}
