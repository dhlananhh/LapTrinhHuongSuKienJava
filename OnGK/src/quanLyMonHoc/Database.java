package quanLyMonHoc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Database {
	public Database() {
		super();
	}

	// Cach 1
	public DanhSachMonHoc read_MH(String part) throws Exception {
		DanhSachMonHoc ds = new DanhSachMonHoc();
		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				MonHoc mh = new MonHoc(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]));
				ds.themMonHoc(mh);
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}

	public void write_MH(String part, DanhSachMonHoc ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part), true)) {
			for (MonHoc mh : ds.getList()) {
				String data = mh.getMaMon() + "," + mh.getTenMon() + "," + mh.getHocKy() + "," + mh.getSoTinChi();
				out.println(data);
			}
		}
	}
}
