package qlThongTinNV;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachNhanVien implements Serializable {
	private ArrayList<NhanVien> list;
	
	
	public DanhSachNhanVien() {
		list = new ArrayList<>();
	}
	
	
	//lấy danh sách nhân viên
	public ArrayList<NhanVien> getList() {
		return list;
	}
	
	
	//lấy số lượng nhân viên
	public int getSize() {
		return list.size();
	}
	
	
	//thêm nhân viên
	public boolean themNhanVien (NhanVien nv) {
		for (int i=0; i<list.size(); i++) {
			if (list.contains(nv))
				return false;
		}
		list.add(nv);
		return true;
	}
	
	
	//tìm nhân viên
	//kết quả trả về vị trí đầu tiên được tìm thấy
	public int timNhanVien (String ma) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaNV().equalsIgnoreCase(ma))
				return i;
		}
		return -1;
	}
	
	
	//tìm nhân viên
	//kết quả trả về là 1 nhân viên
	public NhanVien timTheoMaNhanVien (String maNV) {
		NhanVien nv = new NhanVien(maNV);
		if (list.contains(nv))
			return list.get(list.indexOf(nv));
		return null;
	}
	
	
	//xóa nhân viên tại vị trí
	public boolean xoaNhanVienTaiViTri (int index) {
		if (index < 0 || index >= list.size())
			return false;
		list.remove(index);
		return true;
	}
	
	
	//sửa thông tin nhân viên
	public boolean suaThongTinNhanVien (String maNV, String tenNV, String diaChi, int tuoi, String email) {
		NhanVien nv = new NhanVien(maNV, tenNV, diaChi, tuoi, email);
		if (list.contains(nv)) {
			nv.setMaNV(maNV);
			nv.setTenNV(tenNV);
			nv.setDiaChi(diaChi);
			nv.setTuoi(tuoi);
			nv.setEmail(email);
			return true;
		} else {
			return false;
		}
	}
}
