package thongTinNV;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
	private String manv;
	private String ho;
	private String ten;
	private String phai;
	private int tuoi;
	private Double tienLuong;
	private static final long serialVersionUID = 1L;

	public NhanVien(String manv, String ho, String ten, String phai, int tuoi, Double tienLuong) {
		super();
		this.manv = manv;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.tienLuong = tienLuong;
	}
	public NhanVien(String manv) {
		this(manv, "Ho", "ten","", 0, 0.0);
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	
	public String getPhai() {
		return phai;
	}
	public void setGioitinh(String phai) {
		this.phai = phai;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public Double getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(Double tienLuong) {
		this.tienLuong = tienLuong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(manv);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(manv, other.manv);
	}

	
}
