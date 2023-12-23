package quanLyMonHoc;


import java.io.Serializable;
import java.util.Objects;


public class MonHoc implements Serializable {
	private int maMon;
	private String tenMon;
	private String hocKy;
	private int soTinChi;
	
	
	public MonHoc (int maMon) {
		this.maMon = maMon;
	}


	public MonHoc(int maMon, String tenMon, String hocKy, int soTinChi) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.hocKy = hocKy;
		this.soTinChi = soTinChi;
	}

	
	public int getMaMon() {
		return maMon;
	}


	public void setMaMon(int maMon) {
		this.maMon = maMon;
	}


	public String getTenMon() {
		return tenMon;
	}


	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}


	public String getHocKy() {
		return hocKy;
	}


	public void setHocKy(String hocKy) {
		this.hocKy = hocKy;
	}


	public int getSoTinChi() {
		return soTinChi;
	}


	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
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
		return maMon == other.maMon;
	}


	@Override
	public String toString() {
		return String.format("Mon hoc [Ma = %d, Ten = %s, Hoc ky = %s, So tin chi = %d]", maMon, tenMon, hocKy, soTinChi);
	}
}
