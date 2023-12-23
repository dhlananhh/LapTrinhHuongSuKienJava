package quanLySach_GK;

import java.util.Objects;

public class Sach {
	private String maSach;
	private String tenSach;
	private int soTrang;
	private String theLoai;
	private String nhaXB;
	
	
	public Sach (String maSach) {
		this.maSach = maSach;
	}

	
	public Sach(String maSach, String tenSach, int soTrang, String theLoai, String nhaXB) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soTrang = soTrang;
		this.theLoai = theLoai;
		this.nhaXB = nhaXB;
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


	public int getSoTrang() {
		return soTrang;
	}


	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}


	public String getNhaXB() {
		return nhaXB;
	}


	public void setNhaXB(String nhaXB) {
		this.nhaXB = nhaXB;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maSach);
	}

	
	public String getTheLoai() {
		return theLoai;
	}


	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
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
	
	
	@Override
	public String toString() {
		return String.format("Sach [Ma sach: %s, Ten sach: %s, Nam xuat ban: %d, So trang: %d, Nha xuat ban: %s]", maSach, tenSach, soTrang, nhaXB);
	}
}
