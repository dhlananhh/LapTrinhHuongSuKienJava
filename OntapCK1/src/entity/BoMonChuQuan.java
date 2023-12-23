package entity;

public class BoMonChuQuan {
	private String ma;
	private String ten;
	
	public BoMonChuQuan(String ma, String ten) {
		setMa(ma);
		setTen(ten);
	}
	public BoMonChuQuan(String ma) {
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
