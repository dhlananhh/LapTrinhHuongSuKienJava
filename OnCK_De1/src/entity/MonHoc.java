package entity;

import java.util.Objects;

public class MonHoc {
	private String maMon;
	private String tenMon;
	private int soTiet;
	
	
	public MonHoc() {
		
	}
	
	
	public MonHoc(String maMon) {
		this.maMon = maMon;
	}


	public MonHoc(String maMon, String tenMon, int soTiet) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.soTiet = soTiet;
	}


	public String getMaMon() {
		return maMon;
	}


	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}


	public String getTenMon() {
		return tenMon;
	}


	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}


	public int getSoTiet() {
		return soTiet;
	}


	public void setSoTiet(int soTiet) {
		this.soTiet = soTiet;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maMon);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonHoc other = (MonHoc) obj;
		return Objects.equals(maMon, other.maMon);
	}
	
}
