package entity;

import java.util.Objects;

public class LopHoc {
	private String maLop;
	private String tenLop;
	private String gvcn;
	
	
	public LopHoc() {
		
	}
	
	
	public LopHoc (String maLop) {
		this.maLop = maLop;
	}


	public LopHoc (String maLop, String tenLop, String gvcn) {
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.gvcn = gvcn;
	}


	public String getMaLop() {
		return maLop;
	}


	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}


	public String getTenLop() {
		return tenLop;
	}


	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}


	public String getGvcn() {
		return gvcn;
	}


	public void setGvcn(String gvcn) {
		this.gvcn = gvcn;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maLop);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LopHoc other = (LopHoc) obj;
		return Objects.equals(maLop, other.maLop);
	}
	
	
	
}
