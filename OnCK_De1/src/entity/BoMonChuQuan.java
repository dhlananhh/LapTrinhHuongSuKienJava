package entity;

import java.util.Objects;

public class BoMonChuQuan {
	private String maSo;
	private String tenBoMonChuQuan;
	
	
	public BoMonChuQuan() {
		
	}
	
	
	public BoMonChuQuan(String maSo) {
		this.maSo = maSo;
	}


	public BoMonChuQuan(String maSo, String tenBoMonChuQuan) {
		super();
		this.maSo = maSo;
		this.tenBoMonChuQuan = tenBoMonChuQuan;
	}


	public String getMaSo() {
		return maSo;
	}


	public void setMaSo(String maSo) {
		this.maSo = maSo;
	}


	public String getTenBoMonChuQuan() {
		return tenBoMonChuQuan;
	}


	public void setTenBoMonChuQuan(String tenBoMonChuQuan) {
		this.tenBoMonChuQuan = tenBoMonChuQuan;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maSo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoMonChuQuan other = (BoMonChuQuan) obj;
		return Objects.equals(maSo, other.maSo);
	}
	
}
