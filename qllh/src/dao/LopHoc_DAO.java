package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LopHoc;

public class LopHoc_DAO {
	public List<LopHoc> getAllLopHoc(){
		List<LopHoc> dsLopHoc = new ArrayList<LopHoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LopHoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsLopHoc.add(new LopHoc
						(rs.getString(1),
								rs.getString(2), 
								rs.getString(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dsLopHoc;
	}
	public boolean create(LopHoc p) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LopHoc values(?,?,?)");
			stmt.setString(1, p.getMaLop());
			stmt.setString(2, p.getTenLop());
			stmt.setString(3, p.getGiaoVienCN());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(LopHoc p) {
		Connection con  = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LopHoc \r\n"
					+ "set tenLop = ? ,giaoVienCN =?\r\n"
					+ "where maLop = ?");
			stmt.setString(1, p.getTenLop());
			stmt.setString(2, p.getGiaoVienCN());
			stmt.setString(3, p.getMaLop());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(String maLop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LopHoc "
					+ "where maLop = ?");
			stmt.setString(1, maLop);
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
}
