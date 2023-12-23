package quanLyMonHoc;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachMonHoc implements Serializable {
	private ArrayList<MonHoc> list;

	public DanhSachMonHoc() {
		list = new ArrayList<>();
	}

	public ArrayList<MonHoc> getList() {
		return list;
	}

	public boolean themMonHoc(MonHoc mh) {
		for (int i = 0; i < list.size(); i++)
			if (list.contains(mh))
				return false;
		list.add(mh);
		return true;
	}

	public boolean xoaMonHoc(int index) {
		if (index <= 0 && index <= list.size() - 1) {
			list.remove(index);
			return true;
		} else
			return false;
	}
//	public boolean xoaMonHoc(int maMH) {
//		return list.removeIf(p -> p.getMaMon() == maMH);
//	}

	public int timMonHoc(int maMH) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaMon() == maMH) {
				return i;
			}
		}
		return -1;
	}
}
