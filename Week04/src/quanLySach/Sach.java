package quanLySach;

import java.io.Serializable;
import java.util.Objects;

public class Sach implements Serializable{
	private String ma, tua, nhaXB, tacGia, ISBN;
	private int namXB, soTrang;
	private double donGia;
	public Sach(String ma,String tua,String tacGia,int namXB,
			String nhaXB,int soTrang,double donGia,String iSBN) {
		super();
		this.ma = ma;
		this.tua = tua;
		this.nhaXB = nhaXB;
		this.tacGia = tacGia;
		ISBN = iSBN;
		this.namXB = namXB;
		this.soTrang = soTrang;
		this.donGia = donGia;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTua() {
		return tua;
	}
	public void setTua(String tua) {
		this.tua = tua;
	}
	public String getNhaXB() {
		return nhaXB;
	}
	public void setNhaXB(String nhaXB) {
		this.nhaXB = nhaXB;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getNamXB() {
		return namXB;
	}
	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ma);
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
		return Objects.equals(ma, other.ma);
	}
	
	
}
