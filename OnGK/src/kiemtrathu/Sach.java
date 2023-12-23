package kiemtrathu;

import java.util.Objects;

public class Sach {
	private String maSach;
	private String tenSach;
	private String nhaxuatban;
	private int soTrang;
	private String theloai;
	
	public Sach(String maSach, String tenSach, int soTrang,String theloai,String nhaxuatban) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soTrang= soTrang;
		this.theloai=theloai;
		this.nhaxuatban = nhaxuatban;
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
	public String getNhaxuatban() {
		return nhaxuatban;
	}
	public void setNhaxuatban(String nhaxuatban) {
		this.nhaxuatban = nhaxuatban;
	}
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	
	public String getTheloai() {
		return theloai;
	}
	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}
	@Override
	public String toString() {
		return maSach+","+tenSach+","+soTrang+","+theloai+","+nhaxuatban;
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
