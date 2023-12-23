package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.BoMonChuQuan;
import entity.MonHoc;

public class MonHoc_DAO {
	public List<MonHoc> getAllMonHoc(){
		List<MonHoc> ds = new ArrayList<MonHoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			String sql = "select * from MonHoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new MonHoc(rs.getString(1), 
						rs.getString(2), 
						new BoMonChuQuan(rs.getString(4)), 
						rs.getInt(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public List<MonHoc> getAllMonHoctheoMABMCQ(String maBMCQ){
		List<MonHoc> ds = new ArrayList<MonHoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from MonHoc where maBMCQ = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maBMCQ);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new MonHoc(rs.getString(1), 
						rs.getString(2), 
						new BoMonChuQuan(rs.getString(4)), 
						rs.getInt(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean create(MonHoc mh) {
		Connection con = ConnectDB.getInstance().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into MonHoc values(?,?,?,?)");
			stmt.setString(1, mh.getMaMH());
			stmt.setString(2, mh.getTenMH());
			stmt.setInt(3, mh.getSoTiet());
			stmt.setString(4, mh.getBmcq().getMa());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean delete(String maMH) {
		Connection con = ConnectDB.getInstance().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from MonHoc where mamon = ?");
			stmt.setString(1, maMH);
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
