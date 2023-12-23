package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.SinhVien;

public class SinhVien_DAO {
	public List<SinhVien> getAllSinhVien(String maLop){
		List<SinhVien> dsSV = new ArrayList<SinhVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from SinhVien where maLop = '"+maLop+"'";
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsSV.add(new SinhVien(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dsSV;
	}
	public boolean create(SinhVien sv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into SinhVien\r\n"
					+ "values(?,?,?,?,?)");
			stmt.setInt(1, sv.getMaSV());
			stmt.setString(2, sv.getHoTen());
			stmt.setString(3, sv.getDiaChi());
			stmt.setString(4, sv.getEmail());
			stmt.setString(5, sv.getMaLop());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(int masv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from SinhVien where maSV = ?");
			stmt.setInt(1, masv);
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(SinhVien sv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update SinhVien\r\n"
					+ "set hoTen = ?,"
					+ "diaChi = ?,"
					+ "email = ?,"
					+ "maLop = ?"
					+ "where maSV = ?");
			stmt.setString(1, sv.getHoTen());
			stmt.setString(2, sv.getDiaChi());
			stmt.setString(3, sv.getEmail());
			stmt.setString(4, sv.getMaLop());
			stmt.setInt(5, sv.getMaSV());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
}
