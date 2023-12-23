package quanLyMonHoc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MonHoc_Database {
	public MonHoc_Database() {
		super();
	}
	

	//đọc file
	public DanhSachMonHoc readFile (String part) throws Exception {
		DanhSachMonHoc ds = new DanhSachMonHoc();
		File f = new File(part);
		if (f.exists()) {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(";");
				MonHoc mh = new MonHoc(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]));
				ds.themMonHoc(mh);
			}
			scanner.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}
	
	
	//ghi file
	public void writeFile (String part, DanhSachMonHoc ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part), true)) {
			for (MonHoc mh : ds.getList()) {
				String data = mh.getMaMon() + ";" + mh.getTenMon() + ";" + mh.getHocKy() + ";" + mh.getSoTinChi();
				out.println(data);
			}
		}
	}
}
