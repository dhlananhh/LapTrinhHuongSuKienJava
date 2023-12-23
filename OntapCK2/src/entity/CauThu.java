package entity;

import java.awt.image.VolatileImage;

public class CauThu {
	private String maCT;
	private String tenCT;
	private ViTriThiDau vttd;
	private int tuoi;
	
	public CauThu(String maCT, String tenCT, ViTriThiDau vttd, int tuoi) {
		setMaCT(maCT);
		setTenCT(tenCT);
		setVttd(vttd);
		setTuoi(tuoi);
	}

	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

	public String getTenCT() {
		return tenCT;
	}

	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}

	public ViTriThiDau getVttd() {
		return vttd;
	}

	public void setVttd(ViTriThiDau vttd) {
		this.vttd = vttd;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	
}
