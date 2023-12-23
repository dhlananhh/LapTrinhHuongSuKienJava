package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.ViTriThiDau;

public class ViTriThiDau_DAO {
	public List<ViTriThiDau> getAllvttd(){
		List<ViTriThiDau> ds = new ArrayList<ViTriThiDau>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			String sql = "select * from ViTriThiDau";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new ViTriThiDau(rs.getString(1), rs.getString(2)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
