package kiemtrathu;

import java.util.ArrayList;

public class DanhSachSach {
	private ArrayList<Sach> ds;

	
	public DanhSachSach () {
		ds = new ArrayList<Sach>();
	}
	public ArrayList<Sach> getDs() {
		return ds;
	}
	public int soLuong() {
		return ds.size();
	}
	public boolean themSach(Sach s) {
		if(!ds.contains(s)) {
			ds.add(s);
			return true;
		}else {
			return false;
		}
	}
	public int timTheoMa1(String ma) {
		for(int i=0;i<ds.size();i++) {
			if(ds.get(i).getMaSach().equalsIgnoreCase(ma)) {
				return i;
			}
		}	
			return -1;
	}
	public boolean removeSach(int index) {
		if(index>=0&&index<=ds.size()-1) {
			ds.remove(index);
			return true;
		}else {
			return false;
		}
	}
	
}
