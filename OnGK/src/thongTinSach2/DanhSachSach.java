package thongTinSach2;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachSach implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Sach> ds;

	public DanhSachSach() {
		ds = new ArrayList<Sach>();
	}

	public ArrayList<Sach> getDs() {
		return ds;
	}

	public void setDs(ArrayList<Sach> ds) {
		this.ds = ds;
	}

	public Sach getSach(int i) {
		if (i >= 0 && i < ds.size()) {
			return ds.get(i);
		}
		return null;
	}

	public int getSize() {
		return ds.size();
	}

	public boolean themSach(Sach s) {
		if (ds.contains(s))
			return false;
		ds.add(s);
		return true;
	}

	public int timKiemVitri(String maSach) {
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMaSach().equals(maSach))
				return i;
		}
		return -1;
	}

	public Sach timKiemSach(String maSach) {
		for (Sach s : ds) {
			if (s.getMaSach().equals(maSach)) {
				return s;
			}
		}
		return null;
	}

	public boolean xoaSach(Sach s) {
		if (!ds.contains(s))
			return false;
		ds.remove(s);
		return false;

	}

	public boolean xoaTaiViTri(int index) {
		if (index > 0 && index < ds.size()) {
			ds.remove(index);
			return true;
		}
		return false;
	}

	/**
	 * @param index
	 * @param s
	 * @return có nhiệm vụ ghi đè đối tượng Sach ở vị trí index trong danh sách
	 *         dsSach bằng đối tượng Sach được truyền vào phương thức. Phương thức
	 *         trả về đối tượng Sach cũ tại vị trí index.
	 */
	public Sach set(int index, Sach s) {
		return ds.set(index, s);
	}

	/**
	 * @param s
	 * @return
	 * có nhiệm vụ trả về chỉ số đầu tiên của đối tượng Sach trong danh sách dsSach có mã sách trùng với mã sách của đối tượng Sach được truyền vào phương thức. Nếu không tìm thấy, phương thức trả về giá trị -1.
	 */
	public int indexOf(Sach s) {
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMaSach().equals(s.getMaSach()))
				return i;
		}
		return -1;
	}

	public boolean sua(String maSach, String tenSach, String theLoai, String nXB, int soTrang) {
		Sach s = new Sach(maSach, tenSach, theLoai, nXB, soTrang);
		if (ds.contains(s)) {
			s.setTenSach(tenSach);
			s.setSoTrang(soTrang);
			s.setTheLoai(theLoai);
			return true;
		}
		return false;
	}
}
