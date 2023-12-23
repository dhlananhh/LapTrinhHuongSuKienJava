package qlThongTinSV;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachSinhVien implements Serializable {
	private ArrayList<SinhVien> list;
	
	
	public DanhSachSinhVien() {
		list = new ArrayList<>();
	}
	
	
	//lấy danh sách nhân viên
	public ArrayList<SinhVien> getList() {
		return list;
	}
	
	
	//lấy số lượng nhân viên
	public int getSize() {
		return list.size();
	}
	
	
	//thêm nhân viên
	public boolean themSinhVien (SinhVien nv) {
		for (int i=0; i<list.size(); i++) {
			if (list.contains(nv))
				return false;
		}
		list.add(nv);
		return true;
	}
	
	
	//tìm nhân viên
	//kết quả trả về vị trí đầu tiên được tìm thấy
	public int timSinhVien (String ma) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaSV().equalsIgnoreCase(ma))
				return i;
		}
		return -1;
	}
	
	
	//tìm nhân viên
	//kết quả trả về là 1 nhân viên
	public SinhVien timTheoMaSinhVien (String MaSV) {
		SinhVien nv = new SinhVien(MaSV);
		if (list.contains(nv))
			return list.get(list.indexOf(nv));
		return null;
	}
	
	
	//xóa nhân viên tại vị trí
	public boolean xoaSinhVienTaiViTri (int index) {
		if (index < 0 || index >= list.size())
			return false;
		list.remove(index);
		return true;
	}
	
	
	//sửa thông tin nhân viên
	public boolean suaThongTinSinhVien (String maSV, String tenSV, String diaChi, int tuoi, String email) {
		SinhVien nv = new SinhVien(maSV, tenSV, diaChi, tuoi, email);
		if (list.contains(nv)) {
			nv.setMaSV(maSV);
			nv.setTenSV(tenSV);
			nv.setDiaChi(diaChi);
			nv.setTuoi(tuoi);
			nv.setEmail(email);
			return true;
		} else {
			return false;
		}
	}
}
