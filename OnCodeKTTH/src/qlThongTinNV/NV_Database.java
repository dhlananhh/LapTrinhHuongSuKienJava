package qlThongTinNV;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class NV_Database {
	public NV_Database() {
		super();
	}
	
	
	//doc file
	public DanhSachNhanVien readFile (String part) throws Exception {
		DanhSachNhanVien ds = new DanhSachNhanVien();
		File f = new File(part);
		if (f.exists()) {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(";");
				NhanVien nv = new NhanVien(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
				ds.themNhanVien(nv);
			}
			scanner.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}
		
	
	//ghi file
	public void writeFile (String part, DanhSachNhanVien ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part))) {
			for (NhanVien nv : ds.getList()) {
				String data = nv.getMaNV() + ";" + nv.getTenNV() + ";" + nv.getDiaChi() + ";" + nv.getTuoi() + ";" + nv.getEmail();
				out.println(data);
			}
		}
	}
}
