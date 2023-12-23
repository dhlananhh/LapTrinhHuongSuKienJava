package qlSach;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachSach implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Sach> dsSach ;
	//Khởi tạo
	public DanhSachSach() {
		dsSach = new ArrayList<Sach>();
	}
	//lấy danh sách các sách hiện tại
	public ArrayList<Sach> getDsSach() {
		return dsSach;
	}
	//lấy đối tượng sách từ 1 vị trí cụ thể
	public Sach getSach(int i) {
		if(i>=0 && i<dsSach.size())
			return dsSach.get(i);
		return null;
	}
	
	
	
//	public void setDsSach(ArrayList<Sach> dsSach) {
//		this.dsSach = dsSach;
//	}
	//trả về kichs thước của sách
	public int getSize() {
		return dsSach.size();
	}


	/**thêm mới 1 đối tượng sách vào danh sách
	 * nếu sách đã tồn tại trả về false
	 * @param maSach
	 * @return
	 */
	public boolean themSach(Sach s) {
		if(dsSach.contains(s))
			return false;
		dsSach.add(s);
		return true;
	}
	
	/**tìm 1 đối tương theo mã sách
	 * @param masach
	 * @return
	 * Trả về vị trí đầu tiên của đối tươngj được tìm thấy
	 * không tìm thấy trả về null
	 */
	public int timKJiem(String masach) {
		for(int i=0; i<dsSach.size(); i++) {
			if(dsSach.get(i).getMaSach().equalsIgnoreCase(masach))
				return i;
		}
		return -1;
	}
	/**cũng tìm kiêms sách dựa trên mac sách
	 * nhưng trả về là 1 sách chứ không phải vị trí
	 * @param ma
	 * @return
	 */
	public Sach timTheoMa(String ma) {
		Sach s = new Sach(ma);
		if(dsSach.contains(s))
			return dsSach.get(dsSach.indexOf(s));
		return null;
	}
	public boolean xoaSach(Sach s) {
		if(!dsSach.contains(s)) {
			return false;
		}
		return dsSach.remove(s);
	}
	/** xóa sách tại 1 vị trí cụ thể trong danh sách
	 * @param index
	 * @return
	 */
	public boolean xoaTaiViTri(int index) {
		if(index >=0 && index <dsSach.size()) {
			dsSach.remove(index);
			return true;
		}
		return false;
	}
	public boolean sua(String maSach, String tuaSach, String tacGia, int namXB, String nhaXB, int soTrang, double donGia,
			String iSBN) {
		Sach s = new Sach(maSach, tuaSach, tacGia, nhaXB, iSBN, soTrang, namXB, donGia);
		if(dsSach.contains(s)) {
			s.setMaSach(maSach);
			s.setTacGia(tacGia);
			s.setTuaSach(tuaSach);
			s.setDonGia(donGia);
			s.setiSBN(iSBN);
			s.setSoTrang(soTrang);
			s.setNamSX(namXB);
			s.setNhaXB(nhaXB);
			return true;
		}
		return false;
	}
	/**
	 * @param index
	 * @param sach
	 * @return
	 * ghỉ đè dối tượng sách ở vị trí index tỏng danh sách bằng đối tượng sách được truyền  vào phương thức. phưng thức trả về đối tượng sách mới đã được sửa đôir
	 */
	public Sach set(int index, Sach sach) {
		return dsSach.set(index, sach);
	}
	/**
	 * @param sach
	 * @return
	 * trả về chỉ số đầu tiên của đối tượng sách trong dsSach có mã trùng với đối tượng sách được truyênf vào phương thức
	 */
	public int indexOf(Sach sach) {
		for(int i =0;i<dsSach.size();i++) {
			if(dsSach.get(i).getMaSach().equals(sach.getMaSach())) {
				return i;
			}
		}
		return -1;
	}
}
