package thongTinNV;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DSNV implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<NhanVien> ds;
	
	public DSNV() {
		ds = new ArrayList<NhanVien>();
	}
	
	public DSNV(List<NhanVien> ds) {
		super();
		this.ds = ds;
	}


	public boolean them(NhanVien nv) {
		if(ds.contains(nv))
			return false;
		return ds.add(nv);
	}

	public NhanVien getNhanVien(int i) {
		if(i>=0 && i< ds.size())
			return ds.get(i);
		return null;
	}
	public int getSize() {
		return ds.size();
	}
	public NhanVien tim(String manv) {
		NhanVien nv = new NhanVien(manv);
		if(ds.contains(nv))
			return ds.get(ds.indexOf(nv));
		return null;
		
	}
	public int timKiemNV(String maNV) {
		for (int i = 0; i < ds.size(); i++)
			if (ds.get(i).getManv().equalsIgnoreCase(maNV))
				return i;
		return -1;
	}
	public boolean xoa(NhanVien nv) {
		if(!ds.contains(nv)) {
			return false;
		}
		return ds.remove(nv);
	}
	public boolean xoaViTri(int index) {
		if(index >= 0 && index < ds.size()) {
			ds.remove(index);
			return true;
		}
		return false;	
	}


	public List<NhanVien> getDs() {
		return ds;
	}

	public void setDs(List<NhanVien> ds) {
		this.ds = ds;
	}
//	public NhanVien getbyIndex(int i) {
//		if(i<0 || i> ds.size()-1)
//			return null;
//		return ds.get(i);
//	}

	@Override
	public String toString() {
		return "DSNV [ds=" + ds + "]";
	}
	public boolean suaNV(String ma, String ho, String ten, int tuoi, String phai, double tienLuong) {
		NhanVien newNV = new NhanVien(ma, ho, ten, phai, tuoi, tienLuong);
		if (ds.contains(newNV)) {
			newNV.setHo(ho);
			newNV.setManv(ma);
			newNV.setTen(ten);
			newNV.setTienLuong(tienLuong);
			newNV.setGioitinh(phai);
			newNV.setTuoi(tuoi);
			return true;
		}
		return false;
	}

	
	public NhanVien set(int index, NhanVien nv) {
	    return ds.set(index, nv);
	}
	public int indexOf(NhanVien nv) {
	    for (int i = 0; i < ds.size(); i++) {
	        if (ds.get(i).getManv().equals(nv.getManv())) {
	            return i;
	        }
	    }
	    return -1;
	}


}
