package quanLySach_GK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Database {
	public Database() {
		super();
	}
	
	public quanly_Sach readFile (String part) throws Exception {
		quanly_Sach ds = new quanly_Sach();
		File f = new File(part);
		if (f.exists()) {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(";");
				Sach sach = new Sach(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4]);
				ds.themSach(sach);
			}
			scanner.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}
	
	
	public void writeFile (String part, quanly_Sach ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part), true)) {
			for (Sach sach : ds.getList()) {
				String data = sach.getMaSach() + ";" + sach.getTenSach() + ";" + sach.getSoTrang() + ";" + sach.getNhaXB();
				out.println(data);
			}
		}
	}
}
