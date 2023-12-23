package quanLySach_GK;

import java.util.ArrayList;

public class quanly_Sach {
private ArrayList<Sach> list;
	
	
	public quanly_Sach() {
		list = new ArrayList<>();
	}
	
	
	//thêm sách
	public boolean themSach (Sach s) {
		for (int i=0; i<list.size(); i++)
			if (list.get(i).getMaSach().equalsIgnoreCase(s.getMaSach()))
				return false;
		list.add(s);
		return true;
	}
	
	
	//lấy danh mục sách
	public ArrayList<Sach> getList() {
		return list;
	}
	
	
	//lấy số lượng sách
	public int soLuongSach() {
		return list.size();
	}
	
	
	public boolean xoaViTri (int index) {
		if (index >= 0 && index <= list.size()-1) {
			list.remove(index);
			return true;
		} else {
			return false;
		}
	}
	
	
	//sửa sách
	public boolean suaSach (String maSach, String tenSach, int soTrang, String theLoai, String nhaXB) {
		Sach s = new Sach(maSach, tenSach, soTrang, theLoai, nhaXB);
		if (list.contains(s)) {
			s.setMaSach(maSach);
			s.setTenSach(tenSach);
			s.setSoTrang(soTrang);
			s.setTheLoai(theLoai);
			s.setNhaXB(nhaXB);
			return true;
		} else {
			return false;
		}
	}
	
	
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
	public int timKiemSach (String maSach) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaSach().equalsIgnoreCase(maSach))
				return i;
		}
		return -1;
	}
}
