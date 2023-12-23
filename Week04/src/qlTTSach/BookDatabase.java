package qlTTSach;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.PrintWriter;


public class BookDatabase {
	public BookDatabase() {
		super();
	}
	
	
	public DanhMucSach readFile (String part) throws Exception {
		DanhMucSach ds = new DanhMucSach();
		File f = new File(part);
		if (f.exists()) {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] data = line.split(";");
					//Sach sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
					Sach sach = new Sach(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]));
					ds.themSach(sach);
				}
				scanner.close();
			}
		} else {
			f.createNewFile();
		}
		return ds;
	}
	
	
	public void writeFile (String part, DanhMucSach ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part))) {
			for (Sach s : ds.getList()) {
//				Sach sach = new Sach(maSach, tuaSach, tacGia, nhaXB, ISBN, soTrang, namXB, donGia);
				String data = s.getMaSach() + ";" + s.getTuaSach() + ";" + s.getTacGia() + ";" + s.getNhaXuatBan() + ";" + s.getISBN() + ";" + s.getSoTrang() + ";" + s.getNamXuatBan() + ";" + s.getDonGia();  
				out.println(data);
			}
		}
	}
}
