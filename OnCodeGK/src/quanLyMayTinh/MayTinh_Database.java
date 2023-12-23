package quanLyMayTinh;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class MayTinh_Database {
	public MayTinh_Database() {
		super();
	}
	
	
	//Đọc file
	public DanhSachMayTinh readFile (String part) throws Exception {
		DanhSachMayTinh ds = new DanhSachMayTinh();
		File f = new File(part);
		if (f.exists()) {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(";");
				MayTinh mt = new MayTinh(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3], data[4]);
				ds.themMayTinh(mt);
			}
			scanner.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}
		
	
	//Ghi file
	public void writeFile (String part, DanhSachMayTinh ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part))) {
			for (MayTinh mt : ds.getList()) {
				String data = mt.getMaMay() + ";" + mt.getTenMay() + ";" + mt.getDonGia() + ";" + mt.getLoaiMay() + ";" + mt.getGhiChu();
				out.println(data);
			}
		}
	}
}
