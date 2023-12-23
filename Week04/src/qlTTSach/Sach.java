package qlTTSach;

import java.io.Serializable;
import java.util.Objects;

public class Sach implements Serializable {
	private String maSach;
	private String tuaSach;
	private int namXuatBan;
	private int soTrang;
	private String ISBN;
	private String tacGia;
	private String nhaXuatBan;
	private double donGia;
	
	
	public Sach (String maSach) {
		this.maSach = maSach;
	}


//	Sach sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
	public Sach (String maSach, String tuaSach, String tacGia, String nhaXB, String ISBN, int soTrang, int namXB, double donGia) {
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXB;
		this.ISBN = ISBN;
		this.soTrang = soTrang;
		this.namXuatBan = namXB;
		this.donGia = donGia;
	}


	public String getMaSach() {
		return maSach;
	}


	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}


	public String getTuaSach() {
		return tuaSach;
	}


	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}


	public int getNamXuatBan() {
		return namXuatBan;
	}


	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}


	public int getSoTrang() {
		return soTrang;
	}


	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getTacGia() {
		return tacGia;
	}


	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}


	public String getNhaXuatBan() {
		return nhaXuatBan;
	}


	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}


	public double getDonGia() {
		return donGia;
	}


	public void setDonGia(double donGia) {
		this.donGia = donGia;
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
		return maSach == other.maSach;
	}


	@Override
	public String toString() {
		return String.format("Sach [Ma sach: %d, Tua sach: %s, Nam xuat ban: %d, So trang: %d, ISBN: %s, Tac gia: %s, Nha xuat ban: %s, Don gia: %.2f]", maSach, tuaSach, namXuatBan, soTrang, ISBN, tacGia, nhaXuatBan, donGia);
	}
}
