package entity;

public class CauLacBo {
	private String ma;
	private String ten;
	
	public CauLacBo(String ma, String ten) {
		setMa(ma);
		setTen(ten);
	}
	public CauLacBo(String ma) {
		setMa(ma);
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	
	
}
