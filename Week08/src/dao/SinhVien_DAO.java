package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LopHoc;
import entity.SinhVien;



public class SinhVien_DAO {
	private static SinhVien_DAO instance = new SinhVien_DAO();
	
	
	public static SinhVien_DAO getInstance() {
		if (instance == null)
            instance = new SinhVien_DAO();
        return instance;
	}
	
	
	public List<SinhVien> getAllSinhVien (String maLop) {
		List<SinhVien> dsSinhVien = new ArrayList<SinhVien>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		try {
			String sql = "select * from SinhVien where maLop = " + maLop;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				dsSinhVien.add(new SinhVien(rs.getString(1), 
											rs.getString(2), 
											rs.getString(3),
											rs.getString(4),
											rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsSinhVien;
	}
}


/*
public List<LopHoc> getAllLopHoc() {
		List<LopHoc> dsLopHoc = new ArrayList<LopHoc>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		try {
			String sql = "select * from LopHoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maLop = rs.getString(1);
				String tenLop = rs.getString(2);
				String gvcn = rs.getString(3);
				LopHoc lh = new LopHoc(maLop, tenLop, gvcn);
				dsLopHoc.add(lh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLopHoc;
	}
*/
