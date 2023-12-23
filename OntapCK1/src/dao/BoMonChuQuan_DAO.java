package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.BoMonChuQuan;

public class BoMonChuQuan_DAO {
	public List<BoMonChuQuan> getAllBMCQ(){
		List<BoMonChuQuan> ds = new ArrayList<BoMonChuQuan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			String sql = "select * from BoMonChuQuan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				ds.add(new BoMonChuQuan(rs.getString(1)
						, rs.getString(2)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
