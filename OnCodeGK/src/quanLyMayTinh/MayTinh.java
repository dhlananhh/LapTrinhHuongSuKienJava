package quanLyMayTinh;


import java.io.Serializable;
import java.util.Objects;


public class MayTinh implements Serializable {
	private int maMay;
	private String tenMay;
	private int donGia;
	private String loaiMay;
	private String ghiChu;
	
	
	public MayTinh (int maMay) {
		this.maMay = maMay;
	}
	
	
	public MayTinh(int maMay, String tenMay, int donGia, String loaiMay, String ghiChu) {
		this.maMay = maMay;
		this.tenMay = tenMay;
		this.donGia = donGia;
		this.loaiMay = loaiMay;
		this.ghiChu = ghiChu;
	}
	
	
	public int getMaMay() {
		return maMay;
	}


	public void setMaMay(int maMay) {
		this.maMay = maMay;
	}


	public String getTenMay() {
		return tenMay;
	}


	public void setTenMay(String tenMay) {
		this.tenMay = tenMay;
	}


	public int getDonGia() {
		return donGia;
	}


	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}


	public String getLoaiMay() {
		return loaiMay;
	}


	public void setLoaiMay(String loaiMay) {
		this.loaiMay = loaiMay;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(maMay);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MayTinh other = (MayTinh) obj;
		return maMay == other.maMay;
	}


	@Override
	public String toString() {
		return String.format("May tinh [Ma = %d, Ten may = %s, Gia = %d, Loai may = %s, Ghi chu = %s]", maMay, tenMay, donGia, loaiMay, ghiChu);
	}
}
