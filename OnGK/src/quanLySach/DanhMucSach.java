package quanLySach;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DanhMucSach implements Serializable{
	private List<Sach> ds;

	public DanhMucSach() {
		super();
		ds = new ArrayList<Sach>();
	}
	public boolean them(Sach o) {
		if(ds.contains(o))
			return false;
		ds.add(o);
		return true;
	}
	public List<Sach> getDS() {
		return ds;
	}
	public void xoaViTri(int i) {
		ds.remove(i);
	}
}
