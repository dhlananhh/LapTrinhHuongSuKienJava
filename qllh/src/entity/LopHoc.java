package entity;

public class LopHoc {
	private String maLop;
	private String tenLop;
	private String giaoVienCN;
	
	public LopHoc(String maLop, String tenLop, String giaoVienCN) {
		setMaLop(maLop);
		setTenLop(tenLop);
		setGiaoVienCN(giaoVienCN);
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

	public String getGiaoVienCN() {
		return giaoVienCN;
	}

	public void setGiaoVienCN(String giaoVienCN) {
		this.giaoVienCN = giaoVienCN;
	}
	
}
