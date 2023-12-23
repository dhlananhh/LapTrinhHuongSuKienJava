package entity;

public class MonHoc {
	private String maMH;
	private String tenMH;
	private BoMonChuQuan bmcq;
	private int soTiet;
	
	public MonHoc(String maMH, String tenMH, BoMonChuQuan bmcq, int soTiet) {
		setMaMH(maMH);
		setTenMH(tenMH);
		setBmcq(bmcq);
		setSoTiet(soTiet);
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public BoMonChuQuan getBmcq() {
		return bmcq;
	}

	public void setBmcq(BoMonChuQuan bmcq) {
		this.bmcq = bmcq;
	}

	public int getSoTiet() {
		return soTiet;
	}

	public void setSoTiet(int soTiet) {
		this.soTiet = soTiet;
	}
	
}
