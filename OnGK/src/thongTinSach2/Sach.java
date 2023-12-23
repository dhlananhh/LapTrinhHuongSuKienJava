package thongTinSach2;

import java.io.Serializable;
import java.util.Objects;

public class Sach implements Serializable{
	private static final long serialVersionUID = 1L;
	private String maSach, tenSach, theLoai,nXB;
	private int soTrang;
	
	
	public Sach(String maSach) {
		this(maSach, "", "", "", 0);
	}


	public Sach(String maSach, String tenSach, String theLoai, String nXB, int soTrang) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.theLoai = theLoai;
		this.nXB = nXB;
		this.soTrang = soTrang;
	}


	public String getMaSach() {
		return maSach;
	}


	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}


	public String getTenSach() {
		return tenSach;
	}


	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}


	public String getTheLoai() {
		return theLoai;
	}


	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}


	public String getnXB() {
		return nXB;
	}


	public void setnXB(String nXB) {
		this.nXB = nXB;
	}


	public int getSoTrang() {
		return soTrang;
	}


	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maSach);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		return Objects.equals(maSach, other.maSach);
	}
	
}
