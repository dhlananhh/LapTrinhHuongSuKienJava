package quanLyMayTinh;


import java.io.Serializable;
import java.util.ArrayList;


public class DanhSachMayTinh implements Serializable {
	private ArrayList<MayTinh> list;
	
	
	public DanhSachMayTinh() {
		list = new ArrayList<>();
	}
	
	
	public ArrayList<MayTinh> getList() {
		return list;
	}
	
	
	public int getSoLuongMayTinh() {
		return list.size();
	}
	
	
	//thêm máy tính
	public boolean themMayTinh (MayTinh mt) {
		for (int i=0; i<list.size(); i++) {
			if (list.contains(mt))
				return false;
		}
		list.add(mt);
		return true;
	}
	
	
	//xóa máy tính
	public boolean xoaMayTinh (int ma) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaMay() == ma) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	//tìm máy tính
	public int timMayTinh (int ma) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getMaMay() == ma) 
				return i;
		}
		return -1;
	}
}
