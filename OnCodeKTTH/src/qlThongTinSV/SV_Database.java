package qlThongTinSV;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class SV_Database {
	public SV_Database() {
		super();
	}
	
	
	//doc file
	public DanhSachSinhVien readFile (String part) throws Exception {
		DanhSachSinhVien ds = new DanhSachSinhVien();
		File f = new File(part);
		if (f.exists()) {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(";");
				SinhVien nv = new SinhVien(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
				ds.themSinhVien(nv);
			}
			scanner.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}
		
	
	//ghi file
	public void writeFile (String part, DanhSachSinhVien ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part))) {
			for (SinhVien nv : ds.getList()) {
				String data = nv.getMaSV() + ";" + nv.getTenSV() + ";" + nv.getDiaChi() + ";" + nv.getTuoi() + ";" + nv.getEmail();
				out.println(data);
			}
		}
	}
}
