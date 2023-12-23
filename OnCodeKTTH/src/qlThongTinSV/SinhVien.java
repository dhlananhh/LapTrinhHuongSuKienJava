package qlThongTinSV;


import java.io.Serializable;
import java.util.Objects;


public class SinhVien implements Serializable {
	private String maSV;
	private String tenSV;
	private String diaChi;
	private int tuoi;
	private String email;
	
	
	public SinhVien (String ma) {
		this.maSV = ma;
	}


	public SinhVien(String maSV, String tenSV, String diaChi, int tuoi, String email) {
		super();
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.diaChi = diaChi;
		this.tuoi = tuoi;
		this.email = email;
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


	public int getTuoi() {
		return tuoi;
	}


	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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