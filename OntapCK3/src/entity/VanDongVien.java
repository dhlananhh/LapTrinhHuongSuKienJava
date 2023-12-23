package entity;

public class VanDongVien {
	private String maVDV;
	private String tenVDV;
	private CauLacBo clb;
	private int tuoi;
	
	public VanDongVien(String maVDV, String tenVDV, CauLacBo clb, int tuoi) {
		setMaVDV(maVDV);
		setTenVDV(tenVDV);
		setClb(clb);
		setTuoi(tuoi);
	}

	public String getMaVDV() {
		return maVDV;
	}

	public void setMaVDV(String maVDV) {
		this.maVDV = maVDV;
	}

	public String getTenVDV() {
		return tenVDV;
	}

	public void setTenVDV(String tenVDV) {
		this.tenVDV = tenVDV;
	}

	public CauLacBo getClb() {
		return clb;
	}

	public void setClb(CauLacBo clb) {
		this.clb = clb;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	
}
