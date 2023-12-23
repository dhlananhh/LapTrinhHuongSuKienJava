package entity;


import java.io.Serializable;
import java.util.Objects;


public class SinhVien implements Serializable {
	private String maSV;
	private String tenSV;
	private String diaChi;
	private String email;
	private String maLop;
	
	
	public SinhVien() {
		
	}
	
	
	public SinhVien (String maSV) {
		this.maSV = maSV;
	}


	public SinhVien(String maSV, String tenSV, String diaChi, String email, String maLop) {
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.diaChi = diaChi;
		this.email = email;
		this.maLop = maLop;
	}


	public String getMaSV() {
		return maSV;
	}


	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}


	public String getTenSV() {
		return tenSV;
	}


	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getMaLop() {
		return maLop;
	}


	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maSV);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		return Objects.equals(maSV, other.maSV);
	}
	
	
	
}