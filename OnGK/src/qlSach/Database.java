package qlSach;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Database {
	public Database() {

	}

	public void saveFile(String fileName, Object o) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "IO Error!");
			return;
		}
	}

	public Object readFile(String fileName) {
		Object o = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			o = ois.readObject();
			ois.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "IO Error!");

		}
		return o;
	}

	// txt
	public DanhSachSach read_Sach(String part) throws Exception {
		DanhSachSach ds = new DanhSachSach();
		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(";");
				if (data.length == 7) { // Kiểm tra số lượng phần tử
					Sach s = new Sach(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]),
							Integer.parseInt(data[6]), Double.parseDouble(data[6]));
					ds.themSach(s);
				}
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}

	public void writeSach(String part, DanhSachSach ds) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part), true)) {
			for (Sach sach : ds.getDsSach()) {
				String data = sach.getMaSach() + ',' + sach.getTuaSach() + ',' + sach.getTacGia() + ','
						+ sach.getNamSX() + ',' + sach.getNhaXB() + ',' + sach.getSoTrang() + ','
						+ new DecimalFormat("#,##0.00").format(sach.getDonGia()) + ',' + sach.getiSBN();
				out.println(data);
			}
		}

	}

}
