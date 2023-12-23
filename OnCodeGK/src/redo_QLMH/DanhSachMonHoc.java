package redo_QLMH;


import java.util.ArrayList;


public class DanhSachMonHoc {
	private ArrayList<MonHoc> list;
	
	
	public DanhSachMonHoc() {
		list = new ArrayList<>();
	}
	
	
	//lấy danh sách môn học
	public ArrayList<MonHoc> getList() {
		return list;
	}
	
	
	//lấy số lượng môn học
	public int getSoLuongMH() {
		return list.size();
	}
	
	
	//thêm môn học
	public boolean themMonHoc (MonHoc mh) {
		for (int i=0; i<list.size(); i++) {
			if (list.contains(mh))
				return false;
		}
		list.add(mh);
		return true;
	}
	
	
	//xóa môn học
	public boolean xoaMonHoc (int index) {
		if (index >= 0 && index <= list.size()-1) {
			list.remove(index);
			return true;
		} else {
			return false;
		}
	}
	
	
	//tìm môn học
	public int timMonHoc (int maMH) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaMon() == maMH)
				return i;
		}
		return -1;
	}
}
